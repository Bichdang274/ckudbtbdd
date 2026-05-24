package com.example.flashcardapp.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.flashcardapp.data.entity.CardProgress;
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
public final class CardProgressDao_Impl implements CardProgressDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CardProgress> __insertionAdapterOfCardProgress;

  public CardProgressDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCardProgress = new EntityInsertionAdapter<CardProgress>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `card_progress` (`cardId`,`setId`,`known`,`repetitions`,`easeFactor`,`interval`,`nextReviewDate`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final CardProgress entity) {
        if (entity.cardId == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.cardId);
        }
        if (entity.setId == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.setId);
        }
        final int _tmp = entity.known ? 1 : 0;
        statement.bindLong(3, _tmp);
        statement.bindLong(4, entity.repetitions);
        statement.bindDouble(5, entity.easeFactor);
        statement.bindLong(6, entity.interval);
        statement.bindLong(7, entity.nextReviewDate);
      }
    };
  }

  @Override
  public void insert(final CardProgress progress) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCardProgress.insert(progress);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<CardProgress> progressList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCardProgress.insert(progressList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<CardProgress> getProgressForSet(final String setId) {
    final String _sql = "SELECT * FROM card_progress WHERE setId = ?";
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
      final int _cursorIndexOfCardId = CursorUtil.getColumnIndexOrThrow(_cursor, "cardId");
      final int _cursorIndexOfSetId = CursorUtil.getColumnIndexOrThrow(_cursor, "setId");
      final int _cursorIndexOfKnown = CursorUtil.getColumnIndexOrThrow(_cursor, "known");
      final int _cursorIndexOfRepetitions = CursorUtil.getColumnIndexOrThrow(_cursor, "repetitions");
      final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
      final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
      final int _cursorIndexOfNextReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewDate");
      final List<CardProgress> _result = new ArrayList<CardProgress>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final CardProgress _item;
        final String _tmpCardId;
        if (_cursor.isNull(_cursorIndexOfCardId)) {
          _tmpCardId = null;
        } else {
          _tmpCardId = _cursor.getString(_cursorIndexOfCardId);
        }
        final String _tmpSetId;
        if (_cursor.isNull(_cursorIndexOfSetId)) {
          _tmpSetId = null;
        } else {
          _tmpSetId = _cursor.getString(_cursorIndexOfSetId);
        }
        final boolean _tmpKnown;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfKnown);
        _tmpKnown = _tmp != 0;
        final int _tmpRepetitions;
        _tmpRepetitions = _cursor.getInt(_cursorIndexOfRepetitions);
        final float _tmpEaseFactor;
        _tmpEaseFactor = _cursor.getFloat(_cursorIndexOfEaseFactor);
        final int _tmpInterval;
        _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
        final long _tmpNextReviewDate;
        _tmpNextReviewDate = _cursor.getLong(_cursorIndexOfNextReviewDate);
        _item = new CardProgress(_tmpCardId,_tmpSetId,_tmpKnown,_tmpRepetitions,_tmpEaseFactor,_tmpInterval,_tmpNextReviewDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getKnownCountForSet(final String setId) {
    final String _sql = "SELECT COUNT(*) FROM card_progress WHERE setId = ? AND known = 1";
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
