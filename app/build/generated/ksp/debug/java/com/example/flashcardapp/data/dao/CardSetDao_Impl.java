package com.example.flashcardapp.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.flashcardapp.data.entity.CardSet;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

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
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CardSet entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getTitleVi());
        statement.bindString(4, entity.getEmoji());
        statement.bindString(5, entity.getGradient());
        statement.bindString(6, entity.getAccentColor());
      }
    };
  }

  @Override
  public Object insertAll(final List<CardSet> sets, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCardSet.insert(sets);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
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
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpTitleVi;
            _tmpTitleVi = _cursor.getString(_cursorIndexOfTitleVi);
            final String _tmpEmoji;
            _tmpEmoji = _cursor.getString(_cursorIndexOfEmoji);
            final String _tmpGradient;
            _tmpGradient = _cursor.getString(_cursorIndexOfGradient);
            final String _tmpAccentColor;
            _tmpAccentColor = _cursor.getString(_cursorIndexOfAccentColor);
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
  public Object getAllSetsSync(final Continuation<? super List<CardSet>> $completion) {
    final String _sql = "SELECT * FROM card_sets";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CardSet>>() {
      @Override
      @NonNull
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
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpTitleVi;
            _tmpTitleVi = _cursor.getString(_cursorIndexOfTitleVi);
            final String _tmpEmoji;
            _tmpEmoji = _cursor.getString(_cursorIndexOfEmoji);
            final String _tmpGradient;
            _tmpGradient = _cursor.getString(_cursorIndexOfGradient);
            final String _tmpAccentColor;
            _tmpAccentColor = _cursor.getString(_cursorIndexOfAccentColor);
            _item = new CardSet(_tmpId,_tmpTitle,_tmpTitleVi,_tmpEmoji,_tmpGradient,_tmpAccentColor);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getSetById(final String id, final Continuation<? super CardSet> $completion) {
    final String _sql = "SELECT * FROM card_sets WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CardSet>() {
      @Override
      @Nullable
      public CardSet call() throws Exception {
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
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpTitleVi;
            _tmpTitleVi = _cursor.getString(_cursorIndexOfTitleVi);
            final String _tmpEmoji;
            _tmpEmoji = _cursor.getString(_cursorIndexOfEmoji);
            final String _tmpGradient;
            _tmpGradient = _cursor.getString(_cursorIndexOfGradient);
            final String _tmpAccentColor;
            _tmpAccentColor = _cursor.getString(_cursorIndexOfAccentColor);
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
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
