package com.example.flashcardapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.flashcardapp.R;
import com.example.flashcardapp.adapter.CardSetAdapter;
import com.example.flashcardapp.adapter.FolderAdapter;
import com.example.flashcardapp.databinding.FragmentHomeBinding;
import com.example.flashcardapp.viewmodel.HomeViewModel;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding b;
    private HomeViewModel vm;
    private CardSetAdapter setAdapter;
    private FolderAdapter folderAdapter;

    private NavController getRootNav() {
        NavHostFragment host = (NavHostFragment) requireActivity().getSupportFragmentManager()
            .findFragmentById(R.id.navHostFragment);
        return host != null ? host.getNavController() : null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater i, ViewGroup c, Bundle s) {
        b = FragmentHomeBinding.inflate(i, c, false); return b.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        folderAdapter = new FolderAdapter(
            folder -> { Bundle args = new Bundle(); args.putString("folderId", folder.id); getRootNav().navigate(R.id.studyDest, args); },
            folder -> { Bundle args = new Bundle(); args.putString("folderId", folder.id); getRootNav().navigate(R.id.quizDest, args); },
            folder -> { Bundle args = new Bundle(); args.putString("folderId", folder.id); getRootNav().navigate(R.id.gameDest, args); },
            folder -> { if (folder.id != null) showDeleteFolderDialog(folder.id, folder.name); },
            folder -> { if (folder.id != null) showDeleteFolderDialog(folder.id, folder.name); }
        );
        b.rvFolders.setAdapter(folderAdapter);
        b.rvFolders.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        setAdapter = new CardSetAdapter(
            set -> { Bundle a = new Bundle(); a.putString("setId", set.id); getRootNav().navigate(R.id.setDetailDest, a); },
            set -> { Bundle a = new Bundle(); a.putString("setId", set.id); getRootNav().navigate(R.id.studyDest, a); },
            set -> { Bundle a = new Bundle(); a.putString("setId", set.id); getRootNav().navigate(R.id.quizDest, a); },
            set -> { Bundle a = new Bundle(); a.putString("setId", set.id); getRootNav().navigate(R.id.gameDest, a); }
        );
        b.rvSets.setAdapter(setAdapter);
        b.rvSets.setLayoutManager(new LinearLayoutManager(getContext()));
        b.rvSets.setNestedScrollingEnabled(false);

        b.btnAddFolder.setOnClickListener(v -> showCreateFolderDialog());

        vm.currentUser.observe(getViewLifecycleOwner(), u -> b.tvGreeting.setText("Xin chào, " + (u != null ? u.name : "bạn") + " 👋"));
        vm.stats.observe(getViewLifecycleOwner(), s2 -> {
            b.tvStreak.setText(String.valueOf(s2.streak));
            b.tvSessions.setText(String.valueOf(s2.sessions));
            b.tvWordsLearned.setText(String.valueOf(s2.wordsLearned));
        });
        vm.cardSets.observe(getViewLifecycleOwner(), sets -> setAdapter.submitList(sets));
        vm.cardCounts.observe(getViewLifecycleOwner(), counts -> setAdapter.updateTotals(counts));
        vm.progressMap.observe(getViewLifecycleOwner(), progress -> {
            setAdapter.updateProgress(progress);
            int totalKnown = 0;
            for (int v2 : progress.values()) totalKnown += v2;
            b.tvProgressLabel.setText(totalKnown + " từ đã thuộc");
            b.progressBarOverall.setProgress(totalKnown > 0 ? Math.min(totalKnown * 2, 100) : 0);
        });
        vm.folders.observe(getViewLifecycleOwner(), folders -> {
            folderAdapter.submitList(folders);
            b.tvEmptyFolders.setVisibility(folders.isEmpty() ? View.VISIBLE : View.GONE);
            b.sectionFolders.setVisibility(folders.isEmpty() ? View.GONE : View.VISIBLE);
        });
    }

    @Override
    public void onResume() { super.onResume(); vm.refresh(); }

    private void showCreateFolderDialog() {
        if (getContext() == null) return;
        EditText input = new EditText(getContext());
        input.setHint("Tên thư mục");
        input.setTextColor(getResources().getColor(android.R.color.black, null));
        input.setHintTextColor(0x99000000);
        LinearLayout container = new LinearLayout(getContext());
        container.setOrientation(LinearLayout.VERTICAL);
        container.setPadding(60, 20, 60, 0);
        container.addView(input);
        new AlertDialog.Builder(getContext())
            .setTitle("Tạo thư mục mới")
            .setView(container)
            .setPositiveButton("Tạo", (d, w) -> {
                String name = input.getText().toString().trim();
                if (!name.isEmpty()) vm.createFolder(name);
            })
            .setNegativeButton("Hủy", null)
            .show();
    }

    private void showDeleteFolderDialog(String folderId, String folderName) {
        new AlertDialog.Builder(requireContext())
            .setTitle("Xóa thư mục")
            .setMessage("Bạn có chắc muốn xóa \"" + folderName + "\"?")
            .setPositiveButton("Xóa", (d, w) -> vm.deleteFolder(folderId))
            .setNegativeButton("Hủy", null)
            .show();
    }

    @Override
    public void onDestroyView() { super.onDestroyView(); b = null; }
}
