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
import com.example.flashcardapp.data.entity.Flashcard;
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
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Flashcard entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getSetId());
        statement.bindString(3, entity.getEnglish());
        statement.bindString(4, entity.getVietnamese());
        statement.bindString(5, entity.getPhonetic());
        statement.bindString(6, entity.getExample());
        statement.bindString(7, entity.getExampleVi());
        statement.bindString(8, entity.getEmoji());
      }
    };
  }

  @Override
  public Object insertAll(final List<Flashcard> cards,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFlashcard.insert(cards);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Flashcard>> getCardsForSet(final String setId) {
    final String _sql = "SELECT * FROM flashcards WHERE setId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, setId);
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
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpSetId;
            _tmpSetId = _cursor.getString(_cursorIndexOfSetId);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpVietnamese;
            _tmpVietnamese = _cursor.getString(_cursorIndexOfVietnamese);
            final String _tmpPhonetic;
            _tmpPhonetic = _cursor.getString(_cursorIndexOfPhonetic);
            final String _tmpExample;
            _tmpExample = _cursor.getString(_cursorIndexOfExample);
            final String _tmpExampleVi;
            _tmpExampleVi = _cursor.getString(_cursorIndexOfExampleVi);
            final String _tmpEmoji;
            _tmpEmoji = _cursor.getString(_cursorIndexOfEmoji);
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
  public Object getCardsForSetSync(final String setId,
      final Continuation<? super List<Flashcard>> $completion) {
    final String _sql = "SELECT * FROM flashcards WHERE setId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, setId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Flashcard>>() {
      @Override
      @NonNull
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
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpSetId;
            _tmpSetId = _cursor.getString(_cursorIndexOfSetId);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpVietnamese;
            _tmpVietnamese = _cursor.getString(_cursorIndexOfVietnamese);
            final String _tmpPhonetic;
            _tmpPhonetic = _cursor.getString(_cursorIndexOfPhonetic);
            final String _tmpExample;
            _tmpExample = _cursor.getString(_cursorIndexOfExample);
            final String _tmpExampleVi;
            _tmpExampleVi = _cursor.getString(_cursorIndexOfExampleVi);
            final String _tmpEmoji;
            _tmpEmoji = _cursor.getString(_cursorIndexOfEmoji);
            _item = new Flashcard(_tmpId,_tmpSetId,_tmpEnglish,_tmpVietnamese,_tmpPhonetic,_tmpExample,_tmpExampleVi,_tmpEmoji);
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
  public LiveData<List<Flashcard>> searchCards(final String query) {
    final String _sql = "SELECT * FROM flashcards WHERE english LIKE '%' || ? || '%' OR vietnamese LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
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
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpSetId;
            _tmpSetId = _cursor.getString(_cursorIndexOfSetId);
            final String _tmpEnglish;
            _tmpEnglish = _cursor.getString(_cursorIndexOfEnglish);
            final String _tmpVietnamese;
            _tmpVietnamese = _cursor.getString(_cursorIndexOfVietnamese);
            final String _tmpPhonetic;
            _tmpPhonetic = _cursor.getString(_cursorIndexOfPhonetic);
            final String _tmpExample;
            _tmpExample = _cursor.getString(_cursorIndexOfExample);
            final String _tmpExampleVi;
            _tmpExampleVi = _cursor.getString(_cursorIndexOfExampleVi);
            final String _tmpEmoji;
            _tmpEmoji = _cursor.getString(_cursorIndexOfEmoji);
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
