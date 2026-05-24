package com.example.flashcardapp.ui.account;

import android.Manifest;
import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import com.example.flashcardapp.NotificationReceiver;
import com.example.flashcardapp.R;
import com.example.flashcardapp.databinding.FragmentAccountBinding;
import com.example.flashcardapp.viewmodel.AccountViewModel;
import com.example.flashcardapp.viewmodel.AuthViewModel;

public class AccountFragment extends Fragment {
    private FragmentAccountBinding b;
    private AccountViewModel vm;
    private AuthViewModel authVM;
    private SharedPreferences prefs;

    private NavController getRootNav() {
        NavHostFragment host = (NavHostFragment) requireActivity().getSupportFragmentManager()
            .findFragmentById(R.id.navHostFragment);
        return host != null ? host.getNavController() : null;
    }

    private final ActivityResultLauncher<String> notifPermLauncher = registerForActivityResult(
        new ActivityResultContracts.RequestPermission(), granted -> {
            if (granted) {
                enableNotification();
            } else {
                b.toggleNotification.setChecked(false);
                prefs.edit().putBoolean("notif", false).apply();
                Toast.makeText(requireContext(), "Cần cấp quyền thông báo để sử dụng tính năng này", Toast.LENGTH_LONG).show();
            }
        });

    private final ActivityResultLauncher<Intent> exactAlarmLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(), result -> enableNotification());

    @Override
    public View onCreateView(@NonNull LayoutInflater i, ViewGroup c, Bundle s) {
        b = FragmentAccountBinding.inflate(i, c, false); return b.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(requireActivity()).get(AccountViewModel.class);
        authVM = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        prefs = requireContext().getSharedPreferences("settings", Context.MODE_PRIVATE);

        vm.loadData();

        vm.user.observe(getViewLifecycleOwner(), user -> {
            b.tvName.setText(user != null ? user.name : "Người dùng");
            b.tvEmail.setText(user != null ? user.email : "");
            String avatar = (user != null && user.name != null && !user.name.isEmpty())
                ? String.valueOf(user.name.charAt(0)).toUpperCase() : "U";
            b.tvAvatar.setText(avatar);
        });
        vm.stats.observe(getViewLifecycleOwner(), stats -> {
            b.tvWordsLearned.setText(String.valueOf(stats.wordsLearned));
            b.tvStreak.setText(String.valueOf(stats.streak));
            b.tvSessions.setText(String.valueOf(stats.sessions));
        });
        vm.folders.observe(getViewLifecycleOwner(), folders -> b.tvSetsCount.setText(String.valueOf(folders.size())));

        setupToggles();
        b.btnProfile.setOnClickListener(v -> {
            NavController nav = getRootNav();
            if (nav != null) nav.navigate(R.id.action_main_to_editProfile);
        });
        b.btnLogout.setOnClickListener(v -> authVM.logout());
        b.tvDeleteAccount.setOnClickListener(v -> showDeleteAccountDialog());

        authVM.authState.observe(getViewLifecycleOwner(), state -> {
            if (state instanceof AuthViewModel.AuthState.Error) {
                Toast.makeText(requireContext(),
                    ((AuthViewModel.AuthState.Error) state).message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupToggles() {
        b.toggleNotification.setChecked(prefs.getBoolean("notif", false));
        b.toggleSound.setChecked(prefs.getBoolean("sound", true));
        boolean isDark = prefs.getBoolean("dark", false);
        b.toggleDark.setChecked(isDark);
        b.tvDarkModeSubtitle.setText(isDark ? "Đang bật chế độ tối" : "Đang bật chế độ sáng");

        b.toggleNotification.setOnCheckedChangeListener((btn, on) -> {
            if (on) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                    ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {
                    notifPermLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    AlarmManager am = (AlarmManager) requireContext().getSystemService(Context.ALARM_SERVICE);
                    if (!am.canScheduleExactAlarms()) {
                        Toast.makeText(requireContext(), "Cần cấp quyền 'Báo thức & nhắc nhở' để nhắc đúng giờ", Toast.LENGTH_LONG).show();
                        exactAlarmLauncher.launch(new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM));
                    } else {
                        enableNotification();
                    }
                } else {
                    enableNotification();
                }
            } else {
                NotificationReceiver.cancel(requireContext());
                prefs.edit().putBoolean("notif", false).apply();
                Toast.makeText(requireContext(), "Đã tắt nhắc nhở học tập", Toast.LENGTH_SHORT).show();
            }
        });

        b.toggleSound.setOnCheckedChangeListener((btn, on) -> prefs.edit().putBoolean("sound", on).apply());

        b.toggleDark.setOnCheckedChangeListener((btn, on) -> {
            prefs.edit().putBoolean("dark", on).apply();
            b.tvDarkModeSubtitle.setText(on ? "Đang bật chế độ tối" : "Đang bật chế độ sáng");
            AppCompatDelegate.setDefaultNightMode(on ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
            requireActivity().recreate();
        });
    }

    private void showDeleteAccountDialog() {
        new AlertDialog.Builder(requireContext())
            .setTitle("Xóa tài khoản")
            .setMessage("Tất cả dữ liệu học tập, thư mục và tiến trình của bạn sẽ bị xóa vĩnh viễn. Hành động này không thể hoàn tác.")
            .setPositiveButton("Xóa tài khoản", (d, w) -> {
                new AlertDialog.Builder(requireContext())
                    .setTitle("Xác nhận lần cuối")
                    .setMessage("Bạn có chắc chắn muốn xóa tài khoản không?")
                    .setPositiveButton("Có, xóa ngay", (d2, w2) -> authVM.deleteAccount())
                    .setNegativeButton("Hủy", null)
                    .show();
            })
            .setNegativeButton("Hủy", null)
            .show();
    }

    private void enableNotification() {
        NotificationReceiver.schedule(requireContext());
        prefs.edit().putBoolean("notif", true).apply();
        Toast.makeText(requireContext(),
            "Đã bật nhắc nhở — sẽ thông báo lúc " + NotificationReceiver.NOTIF_HOUR + ":00 mỗi ngày",
            Toast.LENGTH_LONG).show();
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (isAdded()) NotificationReceiver.showNotification(requireContext());
        }, 3000);
        suggestDisableBatteryOptimization();
    }

    private void suggestDisableBatteryOptimization() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return;
        PowerManager pm = (PowerManager) requireContext().getSystemService(Context.POWER_SERVICE);
        String pkg = requireContext().getPackageName();
        if (!pm.isIgnoringBatteryOptimizations(pkg)) {
            new AlertDialog.Builder(requireContext())
                .setTitle("Để nhắc nhở đúng giờ")
                .setMessage("Tắt tối ưu pin cho app này để thông báo 20:00 không bị trễ hoặc mất.")
                .setPositiveButton("Tắt tối ưu pin", (d, w) -> startActivity(
                    new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                        .setData(Uri.parse("package:" + pkg))))
                .setNegativeButton("Để sau", null)
                .show();
        }
    }

    @Override
    public void onDestroyView() { super.onDestroyView(); b = null; }
}
