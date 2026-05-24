package com.example.flashcardapp.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.flashcardapp.data.entity.SavedWord;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class SavedWordDao_Impl implements SavedWordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SavedWord> __insertionAdapterOfSavedWord;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public SavedWordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSavedWord = new EntityInsertionAdapter<SavedWord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `saved_words` (`id`,`word`,`phonetic`,`partOfSpeech`,`definition`,`example`,`savedAt`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final SavedWord entity) {
        if (entity.id == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.id);
        }
        if (entity.word == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.word);
        }
        if (entity.phonetic == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.phonetic);
        }
        if (entity.partOfSpeech == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.partOfSpeech);
        }
        if (entity.definition == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.definition);
        }
        if (entity.example == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.example);
        }
        statement.bindLong(7, entity.savedAt);
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM saved_words WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final SavedWord word) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSavedWord.insert(word);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteById(final String id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
    int _argIndex = 1;
    if (id == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, id);
    }
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteById.release(_stmt);
    }
  }

  @Override
  public List<SavedWord> getAll() {
    final String _sql = "SELECT * FROM saved_words ORDER BY savedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
      final int _cursorIndexOfPhonetic = CursorUtil.getColumnIndexOrThrow(_cursor, "phonetic");
      final int _cursorIndexOfPartOfSpeech = CursorUtil.getColumnIndexOrThrow(_cursor, "partOfSpeech");
      final int _cursorIndexOfDefinition = CursorUtil.getColumnIndexOrThrow(_cursor, "definition");
      final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
      final int _cursorIndexOfSavedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "savedAt");
      final List<SavedWord> _result = new ArrayList<SavedWord>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final SavedWord _item;
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        final String _tmpWord;
        if (_cursor.isNull(_cursorIndexOfWord)) {
          _tmpWord = null;
        } else {
          _tmpWord = _cursor.getString(_cursorIndexOfWord);
        }
        final String _tmpPhonetic;
        if (_cursor.isNull(_cursorIndexOfPhonetic)) {
          _tmpPhonetic = null;
        } else {
          _tmpPhonetic = _cursor.getString(_cursorIndexOfPhonetic);
        }
        final String _tmpPartOfSpeech;
        if (_cursor.isNull(_cursorIndexOfPartOfSpeech)) {
          _tmpPartOfSpeech = null;
        } else {
          _tmpPartOfSpeech = _cursor.getString(_cursorIndexOfPartOfSpeech);
        }
        final String _tmpDefinition;
        if (_cursor.isNull(_cursorIndexOfDefinition)) {
          _tmpDefinition = null;
        } else {
          _tmpDefinition = _cursor.getString(_cursorIndexOfDefinition);
        }
        final String _tmpExample;
        if (_cursor.isNull(_cursorIndexOfExample)) {
          _tmpExample = null;
        } else {
          _tmpExample = _cursor.getString(_cursorIndexOfExample);
        }
        final long _tmpSavedAt;
        _tmpSavedAt = _cursor.getLong(_cursorIndexOfSavedAt);
        _item = new SavedWord(_tmpId,_tmpWord,_tmpPhonetic,_tmpPartOfSpeech,_tmpDefinition,_tmpExample,_tmpSavedAt);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<SavedWord> getByIds(final List<String> ids) {
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM saved_words WHERE id IN (");
    final int _inputSize = ids == null ? 1 : ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    if (ids == null) {
      _statement.bindNull(_argIndex);
    } else {
      for (String _item : ids) {
        if (_item == null) {
          _statement.bindNull(_argIndex);
        } else {
          _statement.bindString(_argIndex, _item);
        }
        _argIndex++;
      }
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
      final int _cursorIndexOfPhonetic = CursorUtil.getColumnIndexOrThrow(_cursor, "phonetic");
      final int _cursorIndexOfPartOfSpeech = CursorUtil.getColumnIndexOrThrow(_cursor, "partOfSpeech");
      final int _cursorIndexOfDefinition = CursorUtil.getColumnIndexOrThrow(_cursor, "definition");
      final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
      final int _cursorIndexOfSavedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "savedAt");
      final List<SavedWord> _result = new ArrayList<SavedWord>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final SavedWord _item_1;
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        final String _tmpWord;
        if (_cursor.isNull(_cursorIndexOfWord)) {
          _tmpWord = null;
        } else {
          _tmpWord = _cursor.getString(_cursorIndexOfWord);
        }
        final String _tmpPhonetic;
        if (_cursor.isNull(_cursorIndexOfPhonetic)) {
          _tmpPhonetic = null;
        } else {
          _tmpPhonetic = _cursor.getString(_cursorIndexOfPhonetic);
        }
        final String _tmpPartOfSpeech;
        if (_cursor.isNull(_cursorIndexOfPartOfSpeech)) {
          _tmpPartOfSpeech = null;
        } else {
          _tmpPartOfSpeech = _cursor.getString(_cursorIndexOfPartOfSpeech);
        }
        final String _tmpDefinition;
        if (_cursor.isNull(_cursorIndexOfDefinition)) {
          _tmpDefinition = null;
        } else {
          _tmpDefinition = _cursor.getString(_cursorIndexOfDefinition);
        }
        final String _tmpExample;
        if (_cursor.isNull(_cursorIndexOfExample)) {
          _tmpExample = null;
        } else {
          _tmpExample = _cursor.getString(_cursorIndexOfExample);
        }
        final long _tmpSavedAt;
        _tmpSavedAt = _cursor.getLong(_cursorIndexOfSavedAt);
        _item_1 = new SavedWord(_tmpId,_tmpWord,_tmpPhonetic,_tmpPartOfSpeech,_tmpDefinition,_tmpExample,_tmpSavedAt);
        _result.add(_item_1);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public SavedWord getById(final String id) {
    final String _sql = "SELECT * FROM saved_words WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
      final int _cursorIndexOfPhonetic = CursorUtil.getColumnIndexOrThrow(_cursor, "phonetic");
      final int _cursorIndexOfPartOfSpeech = CursorUtil.getColumnIndexOrThrow(_cursor, "partOfSpeech");
      final int _cursorIndexOfDefinition = CursorUtil.getColumnIndexOrThrow(_cursor, "definition");
      final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
      final int _cursorIndexOfSavedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "savedAt");
      final SavedWord _result;
      if (_cursor.moveToFirst()) {
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        final String _tmpWord;
        if (_cursor.isNull(_cursorIndexOfWord)) {
          _tmpWord = null;
        } else {
          _tmpWord = _cursor.getString(_cursorIndexOfWord);
        }
        final String _tmpPhonetic;
        if (_cursor.isNull(_cursorIndexOfPhonetic)) {
          _tmpPhonetic = null;
        } else {
          _tmpPhonetic = _cursor.getString(_cursorIndexOfPhonetic);
        }
        final String _tmpPartOfSpeech;
        if (_cursor.isNull(_cursorIndexOfPartOfSpeech)) {
          _tmpPartOfSpeech = null;
        } else {
          _tmpPartOfSpeech = _cursor.getString(_cursorIndexOfPartOfSpeech);
        }
        final String _tmpDefinition;
        if (_cursor.isNull(_cursorIndexOfDefinition)) {
          _tmpDefinition = null;
        } else {
          _tmpDefinition = _cursor.getString(_cursorIndexOfDefinition);
        }
        final String _tmpExample;
        if (_cursor.isNull(_cursorIndexOfExample)) {
          _tmpExample = null;
        } else {
          _tmpExample = _cursor.getString(_cursorIndexOfExample);
        }
        final long _tmpSavedAt;
        _tmpSavedAt = _cursor.getLong(_cursorIndexOfSavedAt);
        _result = new SavedWord(_tmpId,_tmpWord,_tmpPhonetic,_tmpPartOfSpeech,_tmpDefinition,_tmpExample,_tmpSavedAt);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public SavedWord getByWord(final String word) {
    final String _sql = "SELECT * FROM saved_words WHERE word = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (word == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, word);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
      final int _cursorIndexOfPhonetic = CursorUtil.getColumnIndexOrThrow(_cursor, "phonetic");
      final int _cursorIndexOfPartOfSpeech = CursorUtil.getColumnIndexOrThrow(_cursor, "partOfSpeech");
      final int _cursorIndexOfDefinition = CursorUtil.getColumnIndexOrThrow(_cursor, "definition");
      final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
      final int _cursorIndexOfSavedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "savedAt");
      final SavedWord _result;
      if (_cursor.moveToFirst()) {
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        final String _tmpWord;
        if (_cursor.isNull(_cursorIndexOfWord)) {
          _tmpWord = null;
        } else {
          _tmpWord = _cursor.getString(_cursorIndexOfWord);
        }
        final String _tmpPhonetic;
        if (_cursor.isNull(_cursorIndexOfPhonetic)) {
          _tmpPhonetic = null;
        } else {
          _tmpPhonetic = _cursor.getString(_cursorIndexOfPhonetic);
        }
        final String _tmpPartOfSpeech;
        if (_cursor.isNull(_cursorIndexOfPartOfSpeech)) {
          _tmpPartOfSpeech = null;
        } else {
          _tmpPartOfSpeech = _cursor.getString(_cursorIndexOfPartOfSpeech);
        }
        final String _tmpDefinition;
        if (_cursor.isNull(_cursorIndexOfDefinition)) {
          _tmpDefinition = null;
        } else {
          _tmpDefinition = _cursor.getString(_cursorIndexOfDefinition);
        }
        final String _tmpExample;
        if (_cursor.isNull(_cursorIndexOfExample)) {
          _tmpExample = null;
        } else {
          _tmpExample = _cursor.getString(_cursorIndexOfExample);
        }
        final long _tmpSavedAt;
        _tmpSavedAt = _cursor.getLong(_cursorIndexOfSavedAt);
        _result = new SavedWord(_tmpId,_tmpWord,_tmpPhonetic,_tmpPartOfSpeech,_tmpDefinition,_tmpExample,_tmpSavedAt);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
