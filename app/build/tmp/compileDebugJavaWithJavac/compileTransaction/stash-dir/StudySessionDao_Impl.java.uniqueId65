package com.example.flashcardapp.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.flashcardapp.data.entity.StudySession;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class StudySessionDao_Impl implements StudySessionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<StudySession> __insertionAdapterOfStudySession;

  public StudySessionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStudySession = new EntityInsertionAdapter<StudySession>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `study_sessions` (`id`,`setId`,`date`,`cardsStudied`,`knownCount`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final StudySession entity) {
        statement.bindLong(1, entity.id);
        if (entity.setId == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.setId);
        }
        statement.bindLong(3, entity.date);
        statement.bindLong(4, entity.cardsStudied);
        statement.bindLong(5, entity.knownCount);
      }
    };
  }

  @Override
  public void insert(final StudySession session) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfStudySession.insert(session);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int getTotalSessions() {
    final String _sql = "SELECT COUNT(*) FROM study_sessions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<StudySession> getRecentSessions() {
    final String _sql = "SELECT * FROM study_sessions ORDER BY date DESC LIMIT 30";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfSetId = CursorUtil.getColumnIndexOrThrow(_cursor, "setId");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfCardsStudied = CursorUtil.getColumnIndexOrThrow(_cursor, "cardsStudied");
      final int _cursorIndexOfKnownCount = CursorUtil.getColumnIndexOrThrow(_cursor, "knownCount");
      final List<StudySession> _result = new ArrayList<StudySession>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final StudySession _item;
        final String _tmpSetId;
        if (_cursor.isNull(_cursorIndexOfSetId)) {
          _tmpSetId = null;
        } else {
          _tmpSetId = _cursor.getString(_cursorIndexOfSetId);
        }
        final long _tmpDate;
        _tmpDate = _cursor.getLong(_cursorIndexOfDate);
        final int _tmpCardsStudied;
        _tmpCardsStudied = _cursor.getInt(_cursorIndexOfCardsStudied);
        final int _tmpKnownCount;
        _tmpKnownCount = _cursor.getInt(_cursorIndexOfKnownCount);
        _item = new StudySession(_tmpSetId,_tmpDate,_tmpCardsStudied,_tmpKnownCount);
        _item.id = _cursor.getLong(_cursorIndexOfId);
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
