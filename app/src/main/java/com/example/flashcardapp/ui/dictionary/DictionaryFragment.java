package com.example.flashcardapp.ui.dictionary;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.flashcardapp.R;
import com.example.flashcardapp.adapter.DictionaryWordAdapter;
import com.example.flashcardapp.databinding.FragmentDictionaryBinding;
import com.example.flashcardapp.viewmodel.DictionaryViewModel;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DictionaryFragment extends Fragment {
    private FragmentDictionaryBinding b;
    private DictionaryViewModel vm;
    private DictionaryWordAdapter wordAdapter;
    private TextToSpeech tts;
    private boolean ttsReady = false;
    private String pendingSpeech = null;
    private String activeChipId = "all";

    @Override
    public View onCreateView(@NonNull LayoutInflater i, ViewGroup c, Bundle s) {
        b = FragmentDictionaryBinding.inflate(i, c, false); return b.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(requireActivity()).get(DictionaryViewModel.class);

        tts = new TextToSpeech(requireContext().getApplicationContext(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = tts.setLanguage(Locale.US);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                    tts.setLanguage(Locale.getDefault());
                tts.setSpeechRate(0.85f);
                ttsReady = true;
                if (pendingSpeech != null) {
                    tts.speak(pendingSpeech, TextToSpeech.QUEUE_FLUSH, null, "speak");
                    pendingSpeech = null;
                }
            }
        });

        wordAdapter = new DictionaryWordAdapter(
            id -> vm.toggleWord(id),
            word -> {
                boolean soundOn = requireContext().getSharedPreferences("settings", Context.MODE_PRIVATE)
                    .getBoolean("sound", true);
                if (soundOn) {
                    if (ttsReady) tts.speak(word, TextToSpeech.QUEUE_FLUSH, null, "speak");
                    else pendingSpeech = word;
                }
            },
            card -> showFolderPickerForWord(card.id, card.english)
        );
        b.rvWords.setAdapter(wordAdapter);
        b.rvWords.setLayoutManager(new LinearLayoutManager(getContext()));

        b.etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s2, int st, int c2, int a) {}
            @Override public void onTextChanged(CharSequence s2, int st, int be, int c2) {}
            @Override public void afterTextChanged(Editable s2) { vm.search(s2 != null ? s2.toString() : ""); }
        });

        b.btnSelectAll.setOnClickListener(v -> vm.selectAll());
        b.btnClearSel.setOnClickListener(v -> vm.clearSelection());
        b.btnAddToFolder.setOnClickListener(v -> showFolderPicker());

        vm.allSets.observe(getViewLifecycleOwner(), sets -> {
            List<android.util.Pair<String, String>> items = new ArrayList<>();
            for (com.example.flashcardapp.data.model.CardSetDto s2 : sets) items.add(new android.util.Pair<>(s2.id, s2.title));
            buildFilterChips(items);
        });

        vm.filteredWords.observe(getViewLifecycleOwner(), words -> {
            wordAdapter.submitList(words);
            b.tvWordCount.setText(words.size() + " từ vựng");
            b.tvEmpty.setVisibility(words.isEmpty() ? View.VISIBLE : View.GONE);
        });

        vm.selectedIds.observe(getViewLifecycleOwner(), ids -> {
            wordAdapter.updateSelected(ids);
            int count = ids != null ? ids.size() : 0;
            if (count > 0) {
                b.selectionBar.setVisibility(View.VISIBLE);
                b.floatingBar.setVisibility(View.VISIBLE);
                b.tvSelectedCount.setText(count + " từ đã chọn");
                b.tvFloatingLabel.setText("Thêm " + count + " từ vào thư mục");
            } else {
                b.selectionBar.setVisibility(View.GONE);
                b.floatingBar.setVisibility(View.GONE);
            }
        });
    }

    private void buildFilterChips(List<android.util.Pair<String, String>> sets) {
        b.chipContainer.removeAllViews();
        List<android.util.Pair<String, String>> allChips = new ArrayList<>();
        allChips.add(new android.util.Pair<>("all", "Tất cả"));
        allChips.addAll(sets);
        for (android.util.Pair<String, String> item : allChips) {
            final String id = item.first;
            final String label = item.second;
            TextView chip = new TextView(requireContext());
            chip.setText(label);
            chip.setTextSize(13f);
            chip.setTextColor(Color.WHITE);
            chip.setPadding(36, 18, 36, 18);
            chip.setBackground(id.equals(activeChipId)
                ? requireContext().getDrawable(R.drawable.bg_chip_active)
                : requireContext().getDrawable(R.drawable.bg_chip_inactive));
            MarginLayoutParams lp = new MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMarginEnd(8);
            chip.setLayoutParams(lp);
            chip.setOnClickListener(v -> {
                activeChipId = id;
                vm.setFilter(id);
                buildFilterChips(sets);
            });
            b.chipContainer.addView(chip);
        }
    }

    private void showFolderPicker() {
        List<com.example.flashcardapp.data.model.FolderDto> folders = vm.folders.getValue();
        if (folders == null) folders = new ArrayList<>();
        String[] items = new String[folders.size() + 1];
        for (int i = 0; i < folders.size(); i++) items[i] = folders.get(i).emoji + " " + folders.get(i).name;
        items[folders.size()] = "➕  Tạo thư mục mới";
        final List<com.example.flashcardapp.data.model.FolderDto> finalFolders = folders;
        new AlertDialog.Builder(requireContext())
            .setTitle("Thêm vào thư mục")
            .setItems(items, (d, i) -> {
                if (i < finalFolders.size()) {
                    String fid = finalFolders.get(i).id;
                    if (fid != null) vm.addSelectedToFolder(fid);
                } else showCreateFolderDialog(null);
            })
            .setNegativeButton("Hủy", null).show();
    }

    private void showFolderPickerForWord(String wordId, String wordName) {
        List<com.example.flashcardapp.data.model.FolderDto> folders = vm.folders.getValue();
        if (folders == null) folders = new ArrayList<>();
        String[] items = new String[folders.size() + 1];
        for (int i = 0; i < folders.size(); i++) items[i] = folders.get(i).emoji + " " + folders.get(i).name;
        items[folders.size()] = "➕  Tạo thư mục mới";
        final List<com.example.flashcardapp.data.model.FolderDto> finalFolders = folders;
        new AlertDialog.Builder(requireContext())
            .setTitle("Thêm \"" + wordName + "\" vào")
            .setItems(items, (d, i) -> {
                if (i < finalFolders.size()) {
                    com.example.flashcardapp.data.model.FolderDto folder = finalFolders.get(i);
                    if (folder.id != null) {
                        vm.addWordToFolder(wordId, folder.id);
                        Snackbar.make(b.getRoot(), "Đã thêm vào " + folder.emoji + " " + folder.name, Snackbar.LENGTH_SHORT).show();
                    }
                } else showCreateFolderDialog(wordId);
            })
            .setNegativeButton("Hủy", null).show();
    }

    private void showCreateFolderDialog(String wordId) {
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
                if (name.isEmpty()) return;
                if (wordId != null) {
                    vm.createFolderAndAddWord(name, wordId);
                    Snackbar.make(b.getRoot(), "Đã tạo \"" + name + "\" và thêm từ vào", Snackbar.LENGTH_SHORT).show();
                } else {
                    vm.createFolder(name);
                }
            })
            .setNegativeButton("Hủy", null).show();
    }

    @Override
    public void onResume() { super.onResume(); vm.refreshFolders(); }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (tts != null) { tts.stop(); tts.shutdown(); }
        b = null;
    }
}
