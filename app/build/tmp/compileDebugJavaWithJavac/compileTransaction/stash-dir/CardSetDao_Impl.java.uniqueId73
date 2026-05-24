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
import com.example.flashcardapp.data.entity.CardSet;
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
public final class CardSetDao_Impl implements CardSetDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CardSet> __insertionAdapterOfCardSet;

  public CardSetDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCardSet = new EntityInsertionAdapter<CardSet>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `card_sets` (`id`,`title`,`titleVi`,`emoji`,`gradient`,`accentColor`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final CardSet entity) {
        if (entity.id == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.id);
        }
        if (entity.title == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.title);
        }
        if (entity.titleVi == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.titleVi);
        }
        if (entity.emoji == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.emoji);
        }
        if (entity.gradient == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.gradient);
        }
        if (entity.accentColor == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.accentColor);
        }
      }
    };
  }

  @Override
  public void insertAll(final List<CardSet> sets) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCardSet.insert(sets);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<CardSet>> getAllSets() {
    final String _sql = "SELECT * FROM card_sets";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"card_sets"}, false, new Callable<List<CardSet>>() {
      @Override
      @Nullable
      public List<CardSet> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfTitleVi = CursorUtil.getColumnIndexOrThrow(_cursor, "titleVi");
          final int _cursorIndexOfEmoji = CursorUtil.getColumnIndexOrThrow(_cursor, "emoji");
          final int _cursorIndexOfGradient = CursorUtil.getColumnIndexOrThrow(_cursor, "gradient");
          final int _cursorIndexOfAccentColor = CursorUtil.getColumnIndexOrThrow(_cursor, "accentColor");
          final List<CardSet> _result = new ArrayList<CardSet>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CardSet _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpTitleVi;
            if (_cursor.isNull(_cursorIndexOfTitleVi)) {
              _tmpTitleVi = null;
            } else {
              _tmpTitleVi = _cursor.getString(_cursorIndexOfTitleVi);
            }
            final String _tmpEmoji;
            if (_cursor.isNull(_cursorIndexOfEmoji)) {
              _tmpEmoji = null;
            } else {
              _tmpEmoji = _cursor.getString(_cursorIndexOfEmoji);
            }
            final String _tmpGradient;
            if (_cursor.isNull(_cursorIndexOfGradient)) {
              _tmpGradient = null;
            } else {
              _tmpGradient = _cursor.getString(_cursorIndexOfGradient);
            }
            final String _tmpAccentColor;
            if (_cursor.isNull(_cursorIndexOfAccentColor)) {
              _tmpAccentColor = null;
            } else {
              _tmpAccentColor = _cursor.getString(_cursorIndexOfAccentColor);
            }
            _item = new CardSet(_tmpId,_tmpTitle,_tmpTitleVi,_tmpEmoji,_tmpGradient,_tmpAccentColor);
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
  public List<CardSet> getAllSetsSync() {
    final String _sql = "SELECT * FROM card_sets";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfTitleVi = CursorUtil.getColumnIndexOrThrow(_cursor, "titleVi");
      final int _cursorIndexOfEmoji = CursorUtil.getColumnIndexOrThrow(_cursor, "emoji");
      final int _cursorIndexOfGradient = CursorUtil.getColumnIndexOrThrow(_cursor, "gradient");
      final int _cursorIndexOfAccentColor = CursorUtil.getColumnIndexOrThrow(_cursor, "accentColor");
      final List<CardSet> _result = new ArrayList<CardSet>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final CardSet _item;
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpTitleVi;
        if (_cursor.isNull(_cursorIndexOfTitleVi)) {
          _tmpTitleVi = null;
        } else {
          _tmpTitleVi = _cursor.getString(_cursorIndexOfTitleVi);
        }
        final String _tmpEmoji;
        if (_cursor.isNull(_cursorIndexOfEmoji)) {
          _tmpEmoji = null;
        } else {
          _tmpEmoji = _cursor.getString(_cursorIndexOfEmoji);
        }
        final String _tmpGradient;
        if (_cursor.isNull(_cursorIndexOfGradient)) {
          _tmpGradient = null;
        } else {
          _tmpGradient = _cursor.getString(_cursorIndexOfGradient);
        }
        final String _tmpAccentColor;
        if (_cursor.isNull(_cursorIndexOfAccentColor)) {
          _tmpAccentColor = null;
        } else {
          _tmpAccentColor = _cursor.getString(_cursorIndexOfAccentColor);
        }
        _item = new CardSet(_tmpId,_tmpTitle,_tmpTitleVi,_tmpEmoji,_tmpGradient,_tmpAccentColor);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public CardSet getSetById(final String id) {
    final String _sql = "SELECT * FROM card_sets WHERE id = ?";
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
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfTitleVi = CursorUtil.getColumnIndexOrThrow(_cursor, "titleVi");
      final int _cursorIndexOfEmoji = CursorUtil.getColumnIndexOrThrow(_cursor, "emoji");
      final int _cursorIndexOfGradient = CursorUtil.getColumnIndexOrThrow(_cursor, "gradient");
      final int _cursorIndexOfAccentColor = CursorUtil.getColumnIndexOrThrow(_cursor, "accentColor");
      final CardSet _result;
      if (_cursor.moveToFirst()) {
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpTitleVi;
        if (_cursor.isNull(_cursorIndexOfTitleVi)) {
          _tmpTitleVi = null;
        } else {
          _tmpTitleVi = _cursor.getString(_cursorIndexOfTitleVi);
        }
        final String _tmpEmoji;
        if (_cursor.isNull(_cursorIndexOfEmoji)) {
          _tmpEmoji = null;
        } else {
          _tmpEmoji = _cursor.getString(_cursorIndexOfEmoji);
        }
        final String _tmpGradient;
        if (_cursor.isNull(_cursorIndexOfGradient)) {
          _tmpGradient = null;
        } else {
          _tmpGradient = _cursor.getString(_cursorIndexOfGradient);
        }
        final String _tmpAccentColor;
        if (_cursor.isNull(_cursorIndexOfAccentColor)) {
          _tmpAccentColor = null;
        } else {
          _tmpAccentColor = _cursor.getString(_cursorIndexOfAccentColor);
        }
        _result = new CardSet(_tmpId,_tmpTitle,_tmpTitleVi,_tmpEmoji,_tmpGradient,_tmpAccentColor);
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
