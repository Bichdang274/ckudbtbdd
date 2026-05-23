package com.example.flashcardapp.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.flashcardapp.data.model.*;
import com.example.flashcardapp.data.repository.SupabaseRepository;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DictionaryViewModel extends AndroidViewModel {
    private final SupabaseRepository repo;
    private final MutableLiveData<List<FlashcardDto>> _allWords = new MutableLiveData<>();
    private final MutableLiveData<List<CardSetDto>> _allSets = new MutableLiveData<>();
    public final LiveData<List<CardSetDto>> allSets = _allSets;
    private final MutableLiveData<List<FlashcardDto>> _filteredWords = new MutableLiveData<>();
    public final LiveData<List<FlashcardDto>> filteredWords = _filteredWords;
    private final MutableLiveData<Set<String>> _selectedIds = new MutableLiveData<>(new HashSet<>());
    public final LiveData<Set<String>> selectedIds = _selectedIds;
    private final MutableLiveData<List<FolderDto>> _folders = new MutableLiveData<>();
    public final LiveData<List<FolderDto>> folders = _folders;
    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(false);
    public final LiveData<Boolean> isLoading = _isLoading;
    private String searchQuery = "";
    private String activeFilter = "all";
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public DictionaryViewModel(@NonNull Application app) {
        super(app);
        repo = new SupabaseRepository(app);
        loadAll();
    }

    public void loadAll() {
        _isLoading.setValue(true);
        executor.execute(() -> {
            List<CardSetDto> sets = repo.getCardSets();
            List<FlashcardDto> words = repo.getAllFlashcards();
            List<FolderDto> folds = repo.getFolders();
            _allSets.postValue(sets);
            _allWords.postValue(words);
            _folders.postValue(folds);
            applyFilterWith(words);
            _isLoading.postValue(false);
        });
    }

    public void search(String query) { searchQuery = query; applyFilter(); }
    public void setFilter(String setId) { activeFilter = setId; applyFilter(); }

    private void applyFilter() {
        applyFilterWith(_allWords.getValue());
    }

    private void applyFilterWith(List<FlashcardDto> allWords) {
        if (allWords == null) allWords = Collections.emptyList();
        List<FlashcardDto> result = new ArrayList<>();
        for (FlashcardDto w : allWords) {
            if ("all".equals(activeFilter) || activeFilter.equals(w.setId)) result.add(w);
        }
        if (!searchQuery.isEmpty()) {
            List<FlashcardDto> filtered = new ArrayList<>();
            for (FlashcardDto w : result) {
                if (w.english.toLowerCase().contains(searchQuery.toLowerCase()) ||
                    w.vietnamese.toLowerCase().contains(searchQuery.toLowerCase())) {
                    filtered.add(w);
                }
            }
            result = filtered;
        }
        _filteredWords.postValue(result);
    }

    public void toggleWord(String cardId) {
        Set<String> current = _selectedIds.getValue();
        Set<String> next = current != null ? new HashSet<>(current) : new HashSet<>();
        if (next.contains(cardId)) next.remove(cardId); else next.add(cardId);
        _selectedIds.setValue(next);
    }

    public void selectAll() {
        List<FlashcardDto> filtered = _filteredWords.getValue();
        if (filtered == null) return;
        Set<String> ids = new HashSet<>();
        for (FlashcardDto f : filtered) ids.add(f.id);
        _selectedIds.setValue(ids);
    }

    public void clearSelection() { _selectedIds.setValue(new HashSet<>()); }

    public void addSelectedToFolder(String folderId) {
        executor.execute(() -> {
            List<FolderDto> foldList = _folders.getValue();
            if (foldList == null) return;
            FolderDto folder = null;
            for (FolderDto f : foldList) if (folderId.equals(f.id)) { folder = f; break; }
            if (folder == null) return;
            List<String> newWordIds = new ArrayList<>(folder.wordIds);
            Set<String> sel = _selectedIds.getValue();
            if (sel != null) for (String id : sel) if (!newWordIds.contains(id)) newWordIds.add(id);
            repo.updateFolderWords(folderId, newWordIds);
            _folders.postValue(repo.getFolders());
            _selectedIds.postValue(new HashSet<>());
        });
    }

    public void addWordToFolder(String wordId, String folderId) {
        executor.execute(() -> {
            List<FolderDto> foldList = _folders.getValue();
            if (foldList == null) return;
            FolderDto folder = null;
            for (FolderDto f : foldList) if (folderId.equals(f.id)) { folder = f; break; }
            if (folder == null) return;
            List<String> newWordIds = new ArrayList<>(folder.wordIds);
            if (!newWordIds.contains(wordId)) newWordIds.add(wordId);
            repo.updateFolderWords(folderId, newWordIds);
            _folders.postValue(repo.getFolders());
        });
    }

    public void createFolder(String name) {
        executor.execute(() -> {
            repo.createFolder(name, "📁", "#A855F7,#EC4899");
            _folders.postValue(repo.getFolders());
        });
    }

    public void createFolderAndAddWord(String name, String wordId) {
        executor.execute(() -> {
            FolderDto folder = repo.createFolder(name, "📁", "#A855F7,#EC4899");
            if (folder != null && folder.id != null) {
                repo.updateFolderWords(folder.id, Collections.singletonList(wordId));
            }
            _folders.postValue(repo.getFolders());
        });
    }

    public void refreshFolders() {
        executor.execute(() -> _folders.postValue(repo.getFolders()));
    }

    @Override
    protected void onCleared() { super.onCleared(); executor.shutdown(); }
}
