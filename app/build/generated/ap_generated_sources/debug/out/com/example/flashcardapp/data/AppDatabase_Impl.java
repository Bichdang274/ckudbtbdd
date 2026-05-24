package com.example.flashcardapp.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.flashcardapp.data.dao.CardProgressDao;
import com.example.flashcardapp.data.dao.CardProgressDao_Impl;
import com.example.flashcardapp.data.dao.CardSetDao;
import com.example.flashcardapp.data.dao.CardSetDao_Impl;
import com.example.flashcardapp.data.dao.FlashcardDao;
import com.example.flashcardapp.data.dao.FlashcardDao_Impl;
import com.example.flashcardapp.data.dao.FolderDao;
import com.example.flashcardapp.data.dao.FolderDao_Impl;
import com.example.flashcardapp.data.dao.SavedWordDao;
import com.example.flashcardapp.data.dao.SavedWordDao_Impl;
import com.example.flashcardapp.data.dao.SetProgressDao;
import com.example.flashcardapp.data.dao.SetProgressDao_Impl;
import com.example.flashcardapp.data.dao.StudySessionDao;
import com.example.flashcardapp.data.dao.StudySessionDao_Impl;
import com.example.flashcardapp.data.dao.UserDao;
import com.example.flashcardapp.data.dao.UserDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile UserDao _userDao;

  private volatile CardSetDao _cardSetDao;

  private volatile FlashcardDao _flashcardDao;

  private volatile FolderDao _folderDao;

  private volatile CardProgressDao _cardProgressDao;

  private volatile SetProgressDao _setProgressDao;

  private volatile StudySessionDao _studySessionDao;

  private volatile SavedWordDao _savedWordDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` TEXT NOT NULL, `name` TEXT, `email` TEXT, `password` TEXT, `createdAt` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `card_sets` (`id` TEXT NOT NULL, `title` TEXT, `titleVi` TEXT, `emoji` TEXT, `gradient` TEXT, `accentColor` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `flashcards` (`id` TEXT NOT NULL, `setId` TEXT, `english` TEXT, `vietnamese` TEXT, `phonetic` TEXT, `example` TEXT, `exampleVi` TEXT, `emoji` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `folders` (`id` TEXT NOT NULL, `name` TEXT, `emoji` TEXT, `color` TEXT, `wordIds` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `card_progress` (`cardId` TEXT NOT NULL, `setId` TEXT, `known` INTEGER NOT NULL, `repetitions` INTEGER NOT NULL, `easeFactor` REAL NOT NULL, `interval` INTEGER NOT NULL, `nextReviewDate` INTEGER NOT NULL, PRIMARY KEY(`cardId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `set_progress` (`setId` TEXT NOT NULL, `knownCards` INTEGER NOT NULL, `totalCards` INTEGER NOT NULL, `lastStudied` INTEGER NOT NULL, PRIMARY KEY(`setId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `study_sessions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `setId` TEXT, `date` INTEGER NOT NULL, `cardsStudied` INTEGER NOT NULL, `knownCount` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `saved_words` (`id` TEXT NOT NULL, `word` TEXT, `phonetic` TEXT, `partOfSpeech` TEXT, `definition` TEXT, `example` TEXT, `savedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '998b308bf9c49c7431da44bffaf1112a')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `users`");
        db.execSQL("DROP TABLE IF EXISTS `card_sets`");
        db.execSQL("DROP TABLE IF EXISTS `flashcards`");
        db.execSQL("DROP TABLE IF EXISTS `folders`");
        db.execSQL("DROP TABLE IF EXISTS `card_progress`");
        db.execSQL("DROP TABLE IF EXISTS `set_progress`");
        db.execSQL("DROP TABLE IF EXISTS `study_sessions`");
        db.execSQL("DROP TABLE IF EXISTS `saved_words`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUsers = new HashMap<String, TableInfo.Column>(5);
        _columnsUsers.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("email", new TableInfo.Column("email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("password", new TableInfo.Column("password", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("createdAt", new TableInfo.Column("createdAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsers = new TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers);
        final TableInfo _existingUsers = TableInfo.read(db, "users");
        if (!_infoUsers.equals(_existingUsers)) {
          return new RoomOpenHelper.ValidationResult(false, "users(com.example.flashcardapp.data.entity.User).\n"
                  + " Expected:\n" + _infoUsers + "\n"
                  + " Found:\n" + _existingUsers);
        }
        final HashMap<String, TableInfo.Column> _columnsCardSets = new HashMap<String, TableInfo.Column>(6);
        _columnsCardSets.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCardSets.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCardSets.put("titleVi", new TableInfo.Column("titleVi", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCardSets.put("emoji", new TableInfo.Column("emoji", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCardSets.put("gradient", new TableInfo.Column("gradient", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCardSets.put("accentColor", new TableInfo.Column("accentColor", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCardSets = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCardSets = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCardSets = new TableInfo("card_sets", _columnsCardSets, _foreignKeysCardSets, _indicesCardSets);
        final TableInfo _existingCardSets = TableInfo.read(db, "card_sets");
        if (!_infoCardSets.equals(_existingCardSets)) {
          return new RoomOpenHelper.ValidationResult(false, "card_sets(com.example.flashcardapp.data.entity.CardSet).\n"
                  + " Expected:\n" + _infoCardSets + "\n"
                  + " Found:\n" + _existingCardSets);
        }
        final HashMap<String, TableInfo.Column> _columnsFlashcards = new HashMap<String, TableInfo.Column>(8);
        _columnsFlashcards.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlashcards.put("setId", new TableInfo.Column("setId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlashcards.put("english", new TableInfo.Column("english", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlashcards.put("vietnamese", new TableInfo.Column("vietnamese", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlashcards.put("phonetic", new TableInfo.Column("phonetic", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlashcards.put("example", new TableInfo.Column("example", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlashcards.put("exampleVi", new TableInfo.Column("exampleVi", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlashcards.put("emoji", new TableInfo.Column("emoji", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFlashcards = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFlashcards = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFlashcards = new TableInfo("flashcards", _columnsFlashcards, _foreignKeysFlashcards, _indicesFlashcards);
        final TableInfo _existingFlashcards = TableInfo.read(db, "flashcards");
        if (!_infoFlashcards.equals(_existingFlashcards)) {
          return new RoomOpenHelper.ValidationResult(false, "flashcards(com.example.flashcardapp.data.entity.Flashcard).\n"
                  + " Expected:\n" + _infoFlashcards + "\n"
                  + " Found:\n" + _existingFlashcards);
        }
        final HashMap<String, TableInfo.Column> _columnsFolders = new HashMap<String, TableInfo.Column>(5);
        _columnsFolders.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFolders.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFolders.put("emoji", new TableInfo.Column("emoji", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFolders.put("color", new TableInfo.Column("color", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFolders.put("wordIds", new TableInfo.Column("wordIds", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFolders = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFolders = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFolders = new TableInfo("folders", _columnsFolders, _foreignKeysFolders, _indicesFolders);
        final TableInfo _existingFolders = TableInfo.read(db, "folders");
        if (!_infoFolders.equals(_existingFolders)) {
          return new RoomOpenHelper.ValidationResult(false, "folders(com.example.flashcardapp.data.entity.Folder).\n"
                  + " Expected:\n" + _infoFolders + "\n"
                  + " Found:\n" + _existingFolders);
        }
        final HashMap<String, TableInfo.Column> _columnsCardProgress = new HashMap<String, TableInfo.Column>(7);
        _columnsCardProgress.put("cardId", new TableInfo.Column("cardId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCardProgress.put("setId", new TableInfo.Column("setId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCardProgress.put("known", new TableInfo.Column("known", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCardProgress.put("repetitions", new TableInfo.Column("repetitions", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCardProgress.put("easeFactor", new TableInfo.Column("easeFactor", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCardProgress.put("interval", new TableInfo.Column("interval", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCardProgress.put("nextReviewDate", new TableInfo.Column("nextReviewDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCardProgress = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCardProgress = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCardProgress = new TableInfo("card_progress", _columnsCardProgress, _foreignKeysCardProgress, _indicesCardProgress);
        final TableInfo _existingCardProgress = TableInfo.read(db, "card_progress");
        if (!_infoCardProgress.equals(_existingCardProgress)) {
          return new RoomOpenHelper.ValidationResult(false, "card_progress(com.example.flashcardapp.data.entity.CardProgress).\n"
                  + " Expected:\n" + _infoCardProgress + "\n"
                  + " Found:\n" + _existingCardProgress);
        }
        final HashMap<String, TableInfo.Column> _columnsSetProgress = new HashMap<String, TableInfo.Column>(4);
        _columnsSetProgress.put("setId", new TableInfo.Column("setId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSetProgress.put("knownCards", new TableInfo.Column("knownCards", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSetProgress.put("totalCards", new TableInfo.Column("totalCards", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSetProgress.put("lastStudied", new TableInfo.Column("lastStudied", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSetProgress = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSetProgress = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSetProgress = new TableInfo("set_progress", _columnsSetProgress, _foreignKeysSetProgress, _indicesSetProgress);
        final TableInfo _existingSetProgress = TableInfo.read(db, "set_progress");
        if (!_infoSetProgress.equals(_existingSetProgress)) {
          return new RoomOpenHelper.ValidationResult(false, "set_progress(com.example.flashcardapp.data.entity.SetProgress).\n"
                  + " Expected:\n" + _infoSetProgress + "\n"
                  + " Found:\n" + _existingSetProgress);
        }
        final HashMap<String, TableInfo.Column> _columnsStudySessions = new HashMap<String, TableInfo.Column>(5);
        _columnsStudySessions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudySessions.put("setId", new TableInfo.Column("setId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudySessions.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudySessions.put("cardsStudied", new TableInfo.Column("cardsStudied", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudySessions.put("knownCount", new TableInfo.Column("knownCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStudySessions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStudySessions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStudySessions = new TableInfo("study_sessions", _columnsStudySessions, _foreignKeysStudySessions, _indicesStudySessions);
        final TableInfo _existingStudySessions = TableInfo.read(db, "study_sessions");
        if (!_infoStudySessions.equals(_existingStudySessions)) {
          return new RoomOpenHelper.ValidationResult(false, "study_sessions(com.example.flashcardapp.data.entity.StudySession).\n"
                  + " Expected:\n" + _infoStudySessions + "\n"
                  + " Found:\n" + _existingStudySessions);
        }
        final HashMap<String, TableInfo.Column> _columnsSavedWords = new HashMap<String, TableInfo.Column>(7);
        _columnsSavedWords.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSavedWords.put("word", new TableInfo.Column("word", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSavedWords.put("phonetic", new TableInfo.Column("phonetic", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSavedWords.put("partOfSpeech", new TableInfo.Column("partOfSpeech", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSavedWords.put("definition", new TableInfo.Column("definition", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSavedWords.put("example", new TableInfo.Column("example", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSavedWords.put("savedAt", new TableInfo.Column("savedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSavedWords = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSavedWords = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSavedWords = new TableInfo("saved_words", _columnsSavedWords, _foreignKeysSavedWords, _indicesSavedWords);
        final TableInfo _existingSavedWords = TableInfo.read(db, "saved_words");
        if (!_infoSavedWords.equals(_existingSavedWords)) {
          return new RoomOpenHelper.ValidationResult(false, "saved_words(com.example.flashcardapp.data.entity.SavedWord).\n"
                  + " Expected:\n" + _infoSavedWords + "\n"
                  + " Found:\n" + _existingSavedWords);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "998b308bf9c49c7431da44bffaf1112a", "a045f56d5cf997fb3919d21cb0c2a4f1");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "users","card_sets","flashcards","folders","card_progress","set_progress","study_sessions","saved_words");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `users`");
      _db.execSQL("DELETE FROM `card_sets`");
      _db.execSQL("DELETE FROM `flashcards`");
      _db.execSQL("DELETE FROM `folders`");
      _db.execSQL("DELETE FROM `card_progress`");
      _db.execSQL("DELETE FROM `set_progress`");
      _db.execSQL("DELETE FROM `study_sessions`");
      _db.execSQL("DELETE FROM `saved_words`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CardSetDao.class, CardSetDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FlashcardDao.class, FlashcardDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FolderDao.class, FolderDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CardProgressDao.class, CardProgressDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SetProgressDao.class, SetProgressDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(StudySessionDao.class, StudySessionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SavedWordDao.class, SavedWordDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public CardSetDao cardSetDao() {
    if (_cardSetDao != null) {
      return _cardSetDao;
    } else {
      synchronized(this) {
        if(_cardSetDao == null) {
          _cardSetDao = new CardSetDao_Impl(this);
        }
        return _cardSetDao;
      }
    }
  }

  @Override
  public FlashcardDao flashcardDao() {
    if (_flashcardDao != null) {
      return _flashcardDao;
    } else {
      synchronized(this) {
        if(_flashcardDao == null) {
          _flashcardDao = new FlashcardDao_Impl(this);
        }
        return _flashcardDao;
      }
    }
  }

  @Override
  public FolderDao folderDao() {
    if (_folderDao != null) {
      return _folderDao;
    } else {
      synchronized(this) {
        if(_folderDao == null) {
          _folderDao = new FolderDao_Impl(this);
        }
        return _folderDao;
      }
    }
  }

  @Override
  public CardProgressDao cardProgressDao() {
    if (_cardProgressDao != null) {
      return _cardProgressDao;
    } else {
      synchronized(this) {
        if(_cardProgressDao == null) {
          _cardProgressDao = new CardProgressDao_Impl(this);
        }
        return _cardProgressDao;
      }
    }
  }

  @Override
  public SetProgressDao setProgressDao() {
    if (_setProgressDao != null) {
      return _setProgressDao;
    } else {
      synchronized(this) {
        if(_setProgressDao == null) {
          _setProgressDao = new SetProgressDao_Impl(this);
        }
        return _setProgressDao;
      }
    }
  }

  @Override
  public StudySessionDao studySessionDao() {
    if (_studySessionDao != null) {
      return _studySessionDao;
    } else {
      synchronized(this) {
        if(_studySessionDao == null) {
          _studySessionDao = new StudySessionDao_Impl(this);
        }
        return _studySessionDao;
      }
    }
  }

  @Override
  public SavedWordDao savedWordDao() {
    if (_savedWordDao != null) {
      return _savedWordDao;
    } else {
      synchronized(this) {
        if(_savedWordDao == null) {
          _savedWordDao = new SavedWordDao_Impl(this);
        }
        return _savedWordDao;
      }
    }
  }
}
