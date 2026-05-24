package com.example.flashcardapp.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.flashcardapp.data.entity.SavedWord;
import java.util.List;

@Dao
public interface SavedWordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SavedWord word);

    @Query("SELECT * FROM saved_words ORDER BY savedAt DESC")
    List<SavedWord> getAll();

    @Query("SELECT * FROM saved_words WHERE id IN (:ids)")
    List<SavedWord> getByIds(List<String> ids);

    @Query("SELECT * FROM saved_words WHERE id = :id LIMIT 1")
    SavedWord getById(String id);

    @Query("SELECT * FROM saved_words WHERE word = :word LIMIT 1")
    SavedWord getByWord(String word);

    @Query("DELETE FROM saved_words WHERE id = :id")
    void deleteById(String id);
}
