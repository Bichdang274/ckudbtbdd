package com.example.flashcardapp.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import com.example.flashcardapp.databinding.FragmentEditProfileBinding;
import com.example.flashcardapp.viewmodel.AccountViewModel;

public class EditProfileFragment extends Fragment {
    private FragmentEditProfileBinding b;
    private AccountViewModel vm;

    @Override
    public View onCreateView(@NonNull LayoutInflater i, ViewGroup c, Bundle s) {
        b = FragmentEditProfileBinding.inflate(i, c, false); return b.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(requireActivity()).get(AccountViewModel.class);

        vm.user.observe(getViewLifecycleOwner(), user -> {
            if (user == null) return;
            if (b.etName.getText() == null || b.etName.getText().toString().isEmpty()) {
                b.etName.setText(user.name);
            }
            b.tvEmail.setText(user.email);
            String initial = (user.name != null && !user.name.isEmpty())
                ? String.valueOf(user.name.charAt(0)).toUpperCase() : "U";
            b.tvAvatar.setText(initial);
        });

        vm.updateState.observe(getViewLifecycleOwner(), state -> {
            switch (state) {
                case LOADING:
                    b.btnSave.setEnabled(false);
                    b.progressBar.setVisibility(View.VISIBLE);
                    b.tvBtnText.setVisibility(View.INVISIBLE);
                    break;
                case SUCCESS:
                    vm.resetUpdateState();
                    Toast.makeText(requireContext(), "Đã cập nhật hồ sơ", Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(this).popBackStack();
                    break;
                case ERROR:
                    b.btnSave.setEnabled(true);
                    b.progressBar.setVisibility(View.GONE);
                    b.tvBtnText.setVisibility(View.VISIBLE);
                    vm.updateError.observe(getViewLifecycleOwner(), msg ->
                        Toast.makeText(requireContext(), msg != null ? msg : "Có lỗi xảy ra", Toast.LENGTH_LONG).show());
                    vm.resetUpdateState();
                    break;
                default:
                    b.btnSave.setEnabled(true);
                    b.progressBar.setVisibility(View.GONE);
                    b.tvBtnText.setVisibility(View.VISIBLE);
                    break;
            }
        });

        b.btnSave.setOnClickListener(v -> {
            String name = b.etName.getText() != null ? b.etName.getText().toString().trim() : "";
            if (name.isEmpty()) {
                b.etName.setError("Vui lòng nhập tên");
                return;
            }
            vm.updateProfile(name);
        });

        b.btnBack.setOnClickListener(v -> NavHostFragment.findNavController(this).popBackStack());
    }

    @Override
    public void onDestroyView() { super.onDestroyView(); b = null; }
}
