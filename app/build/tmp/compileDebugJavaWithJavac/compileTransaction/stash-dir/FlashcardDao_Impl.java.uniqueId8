package com.example.flashcardapp.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.flashcardapp.data.entity.Flashcard;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class FlashcardDao_Impl implements FlashcardDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Flashcard> __insertionAdapterOfFlashcard;

  public FlashcardDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFlashcard = new EntityInsertionAdapter<Flashcard>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `flashcards` (`id`,`setId`,`english`,`vietnamese`,`phonetic`,`example`,`exampleVi`,`emoji`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Flashcard entity) {
        if (entity.id == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.id);
        }
        if (entity.setId == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.setId);
        }
        if (entity.english == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.english);
        }
        if (entity.vietnamese == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.vietnamese);
        }
        if (entity.phonetic == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.phonetic);
        }
        if (entity.example == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.example);
        }
        if (entity.exampleVi == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.exampleVi);
        }
        if (entity.emoji == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.emoji);
        }
      }
    };
  }

  @Override
  public void insertAll(final List<Flashcard> cards) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfFlashcard.insert(cards);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Flashcard>> getCardsForSet(final String setId) {
    final String _sql = "SELECT * FROM flashcards WHERE setId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (setId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, setId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"flashcards"}, false, new Callable<List<Flashcard>>() {
      @Override
      @Nullable
      public List<Flashcard> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSetId = CursorUtil.getColumnIndexOrThrow(_cursor, "setId");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfVietnamese = CursorUtil.getColumnIndexOrThrow(_cursor, "vietnamese");
          final int _cursorIndexOfPhonetic = CursorUtil.getColumnIndexOrThrow(_cursor, "phonetic");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfExampleVi = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleVi");
          final int _cursorIndexOfEmoji = CursorUtil.getColumnIndexOrThrow(_cursor, "emoji");
          final List<Flashcard> _result = new ArrayList<Flashcard>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Flashcard _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpSetId;
            if (_cursor.isNull(_cursorIndexOfSetId)) {
              _tmpSetId = null;
            } else {
              _tmpSetId = _cursor.getString(_cursorIndexOfSetId);
            }
            final String _tmpEnglish;
            if (_cursor.isNull(_cursorIndexOfEnglish)) {
              _tmpEnglish = null;
            } else {
              _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            }
            final String _tmpVietnamese;
            if (_cursor.isNull(_cursorIndexOfVietnamese)) {
              _tmpVietnamese = null;
            } else {
              _tmpVietnamese = _cursor.getString(_cursorIndexOfVietnamese);
            }
            final String _tmpPhonetic;
            if (_cursor.isNull(_cursorIndexOfPhonetic)) {
              _tmpPhonetic = null;
            } else {
              _tmpPhonetic = _cursor.getString(_cursorIndexOfPhonetic);
            }
            final String _tmpExample;
            if (_cursor.isNull(_cursorIndexOfExample)) {
              _tmpExample = null;
            } else {
              _tmpExample = _cursor.getString(_cursorIndexOfExample);
            }
            final String _tmpExampleVi;
            if (_cursor.isNull(_cursorIndexOfExampleVi)) {
              _tmpExampleVi = null;
            } else {
              _tmpExampleVi = _cursor.getString(_cursorIndexOfExampleVi);
            }
            final String _tmpEmoji;
            if (_cursor.isNull(_cursorIndexOfEmoji)) {
              _tmpEmoji = null;
            } else {
              _tmpEmoji = _cursor.getString(_cursorIndexOfEmoji);
            }
            _item = new Flashcard(_tmpId,_tmpSetId,_tmpEnglish,_tmpVietnamese,_tmpPhonetic,_tmpExample,_tmpExampleVi,_tmpEmoji);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public List<Flashcard> getCardsForSetSync(final String setId) {
    final String _sql = "SELECT * FROM flashcards WHERE setId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (setId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, setId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfSetId = CursorUtil.getColumnIndexOrThrow(_cursor, "setId");
      final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
      final int _cursorIndexOfVietnamese = CursorUtil.getColumnIndexOrThrow(_cursor, "vietnamese");
      final int _cursorIndexOfPhonetic = CursorUtil.getColumnIndexOrThrow(_cursor, "phonetic");
      final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
      final int _cursorIndexOfExampleVi = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleVi");
      final int _cursorIndexOfEmoji = CursorUtil.getColumnIndexOrThrow(_cursor, "emoji");
      final List<Flashcard> _result = new ArrayList<Flashcard>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Flashcard _item;
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        final String _tmpSetId;
        if (_cursor.isNull(_cursorIndexOfSetId)) {
          _tmpSetId = null;
        } else {
          _tmpSetId = _cursor.getString(_cursorIndexOfSetId);
        }
        final String _tmpEnglish;
        if (_cursor.isNull(_cursorIndexOfEnglish)) {
          _tmpEnglish = null;
        } else {
          _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
        }
        final String _tmpVietnamese;
        if (_cursor.isNull(_cursorIndexOfVietnamese)) {
          _tmpVietnamese = null;
        } else {
          _tmpVietnamese = _cursor.getString(_cursorIndexOfVietnamese);
        }
        final String _tmpPhonetic;
        if (_cursor.isNull(_cursorIndexOfPhonetic)) {
          _tmpPhonetic = null;
        } else {
          _tmpPhonetic = _cursor.getString(_cursorIndexOfPhonetic);
        }
        final String _tmpExample;
        if (_cursor.isNull(_cursorIndexOfExample)) {
          _tmpExample = null;
        } else {
          _tmpExample = _cursor.getString(_cursorIndexOfExample);
        }
        final String _tmpExampleVi;
        if (_cursor.isNull(_cursorIndexOfExampleVi)) {
          _tmpExampleVi = null;
        } else {
          _tmpExampleVi = _cursor.getString(_cursorIndexOfExampleVi);
        }
        final String _tmpEmoji;
        if (_cursor.isNull(_cursorIndexOfEmoji)) {
          _tmpEmoji = null;
        } else {
          _tmpEmoji = _cursor.getString(_cursorIndexOfEmoji);
        }
        _item = new Flashcard(_tmpId,_tmpSetId,_tmpEnglish,_tmpVietnamese,_tmpPhonetic,_tmpExample,_tmpExampleVi,_tmpEmoji);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<Flashcard>> searchCards(final String query) {
    final String _sql = "SELECT * FROM flashcards WHERE english LIKE '%' || ? || '%' OR vietnamese LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    _argIndex = 2;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"flashcards"}, false, new Callable<List<Flashcard>>() {
      @Override
      @Nullable
      public List<Flashcard> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSetId = CursorUtil.getColumnIndexOrThrow(_cursor, "setId");
          final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "english");
          final int _cursorIndexOfVietnamese = CursorUtil.getColumnIndexOrThrow(_cursor, "vietnamese");
          final int _cursorIndexOfPhonetic = CursorUtil.getColumnIndexOrThrow(_cursor, "phonetic");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfExampleVi = CursorUtil.getColumnIndexOrThrow(_cursor, "exampleVi");
          final int _cursorIndexOfEmoji = CursorUtil.getColumnIndexOrThrow(_cursor, "emoji");
          final List<Flashcard> _result = new ArrayList<Flashcard>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Flashcard _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpSetId;
            if (_cursor.isNull(_cursorIndexOfSetId)) {
              _tmpSetId = null;
            } else {
              _tmpSetId = _cursor.getString(_cursorIndexOfSetId);
            }
            final String _tmpEnglish;
            if (_cursor.isNull(_cursorIndexOfEnglish)) {
              _tmpEnglish = null;
            } else {
              _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            }
            final String _tmpVietnamese;
            if (_cursor.isNull(_cursorIndexOfVietnamese)) {
              _tmpVietnamese = null;
            } else {
              _tmpVietnamese = _cursor.getString(_cursorIndexOfVietnamese);
            }
            final String _tmpPhonetic;
            if (_cursor.isNull(_cursorIndexOfPhonetic)) {
              _tmpPhonetic = null;
            } else {
              _tmpPhonetic = _cursor.getString(_cursorIndexOfPhonetic);
            }
            final String _tmpExample;
            if (_cursor.isNull(_cursorIndexOfExample)) {
              _tmpExample = null;
            } else {
              _tmpExample = _cursor.getString(_cursorIndexOfExample);
            }
            final String _tmpExampleVi;
            if (_cursor.isNull(_cursorIndexOfExampleVi)) {
              _tmpExampleVi = null;
            } else {
              _tmpExampleVi = _cursor.getString(_cursorIndexOfExampleVi);
            }
            final String _tmpEmoji;
            if (_cursor.isNull(_cursorIndexOfEmoji)) {
              _tmpEmoji = null;
            } else {
              _tmpEmoji = _cursor.getString(_cursorIndexOfEmoji);
            }
            _item = new Flashcard(_tmpId,_tmpSetId,_tmpEnglish,_tmpVietnamese,_tmpPhonetic,_tmpExample,_tmpExampleVi,_tmpEmoji);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
