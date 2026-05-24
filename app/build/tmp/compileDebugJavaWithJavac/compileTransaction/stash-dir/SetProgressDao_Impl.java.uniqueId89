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
import com.example.flashcardapp.data.entity.SetProgress;
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
public final class SetProgressDao_Impl implements SetProgressDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SetProgress> __insertionAdapterOfSetProgress;

  public SetProgressDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSetProgress = new EntityInsertionAdapter<SetProgress>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `set_progress` (`setId`,`knownCards`,`totalCards`,`lastStudied`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final SetProgress entity) {
        if (entity.setId == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.setId);
        }
        statement.bindLong(2, entity.knownCards);
        statement.bindLong(3, entity.totalCards);
        statement.bindLong(4, entity.lastStudied);
      }
    };
  }

  @Override
  public void insert(final SetProgress progress) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSetProgress.insert(progress);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public SetProgress getProgressForSet(final String setId) {
    final String _sql = "SELECT * FROM set_progress WHERE setId = ?";
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
      final int _cursorIndexOfSetId = CursorUtil.getColumnIndexOrThrow(_cursor, "setId");
      final int _cursorIndexOfKnownCards = CursorUtil.getColumnIndexOrThrow(_cursor, "knownCards");
      final int _cursorIndexOfTotalCards = CursorUtil.getColumnIndexOrThrow(_cursor, "totalCards");
      final int _cursorIndexOfLastStudied = CursorUtil.getColumnIndexOrThrow(_cursor, "lastStudied");
      final SetProgress _result;
      if (_cursor.moveToFirst()) {
        final String _tmpSetId;
        if (_cursor.isNull(_cursorIndexOfSetId)) {
          _tmpSetId = null;
        } else {
          _tmpSetId = _cursor.getString(_cursorIndexOfSetId);
        }
        final int _tmpKnownCards;
        _tmpKnownCards = _cursor.getInt(_cursorIndexOfKnownCards);
        final int _tmpTotalCards;
        _tmpTotalCards = _cursor.getInt(_cursorIndexOfTotalCards);
        final long _tmpLastStudied;
        _tmpLastStudied = _cursor.getLong(_cursorIndexOfLastStudied);
        _result = new SetProgress(_tmpSetId,_tmpKnownCards,_tmpTotalCards,_tmpLastStudied);
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
  public LiveData<List<SetProgress>> getAllProgress() {
    final String _sql = "SELECT * FROM set_progress";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"set_progress"}, false, new Callable<List<SetProgress>>() {
      @Override
      @Nullable
      public List<SetProgress> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSetId = CursorUtil.getColumnIndexOrThrow(_cursor, "setId");
          final int _cursorIndexOfKnownCards = CursorUtil.getColumnIndexOrThrow(_cursor, "knownCards");
          final int _cursorIndexOfTotalCards = CursorUtil.getColumnIndexOrThrow(_cursor, "totalCards");
          final int _cursorIndexOfLastStudied = CursorUtil.getColumnIndexOrThrow(_cursor, "lastStudied");
          final List<SetProgress> _result = new ArrayList<SetProgress>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SetProgress _item;
            final String _tmpSetId;
            if (_cursor.isNull(_cursorIndexOfSetId)) {
              _tmpSetId = null;
            } else {
              _tmpSetId = _cursor.getString(_cursorIndexOfSetId);
            }
            final int _tmpKnownCards;
            _tmpKnownCards = _cursor.getInt(_cursorIndexOfKnownCards);
            final int _tmpTotalCards;
            _tmpTotalCards = _cursor.getInt(_cursorIndexOfTotalCards);
            final long _tmpLastStudied;
            _tmpLastStudied = _cursor.getLong(_cursorIndexOfLastStudied);
            _item = new SetProgress(_tmpSetId,_tmpKnownCards,_tmpTotalCards,_tmpLastStudied);
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
  public List<SetProgress> getAllProgressSync() {
    final String _sql = "SELECT * FROM set_progress";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSetId = CursorUtil.getColumnIndexOrThrow(_cursor, "setId");
      final int _cursorIndexOfKnownCards = CursorUtil.getColumnIndexOrThrow(_cursor, "knownCards");
      final int _cursorIndexOfTotalCards = CursorUtil.getColumnIndexOrThrow(_cursor, "totalCards");
      final int _cursorIndexOfLastStudied = CursorUtil.getColumnIndexOrThrow(_cursor, "lastStudied");
      final List<SetProgress> _result = new ArrayList<SetProgress>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final SetProgress _item;
        final String _tmpSetId;
        if (_cursor.isNull(_cursorIndexOfSetId)) {
          _tmpSetId = null;
        } else {
          _tmpSetId = _cursor.getString(_cursorIndexOfSetId);
        }
        final int _tmpKnownCards;
        _tmpKnownCards = _cursor.getInt(_cursorIndexOfKnownCards);
        final int _tmpTotalCards;
        _tmpTotalCards = _cursor.getInt(_cursorIndexOfTotalCards);
        final long _tmpLastStudied;
        _tmpLastStudied = _cursor.getLong(_cursorIndexOfLastStudied);
        _item = new SetProgress(_tmpSetId,_tmpKnownCards,_tmpTotalCards,_tmpLastStudied);
        _result.add(_item);
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
