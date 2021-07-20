package br.org.curitiba.ici.avaliacao.model;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import br.org.curitiba.ici.avaliacao.model.Dao.PlacarUsuarioDao;
import br.org.curitiba.ici.avaliacao.model.Dao.PlacarUsuarioDao_Impl;
import br.org.curitiba.ici.avaliacao.model.Dao.UsuarioDao;
import br.org.curitiba.ici.avaliacao.model.Dao.UsuarioDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class IciDataBase_Impl extends IciDataBase {
  private volatile PlacarUsuarioDao _placarUsuarioDao;

  private volatile UsuarioDao _usuarioDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `PlacarUsuario` (`id_banco` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `id_usuario` INTEGER NOT NULL, `nome_usuario` TEXT, `placar_usuario` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Usuario` (`id_usuario` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome_usuario` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '2b8fbbcec32eec3f7663422ddae498d5')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `PlacarUsuario`");
        _db.execSQL("DROP TABLE IF EXISTS `Usuario`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsPlacarUsuario = new HashMap<String, TableInfo.Column>(4);
        _columnsPlacarUsuario.put("id_banco", new TableInfo.Column("id_banco", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlacarUsuario.put("id_usuario", new TableInfo.Column("id_usuario", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlacarUsuario.put("nome_usuario", new TableInfo.Column("nome_usuario", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlacarUsuario.put("placar_usuario", new TableInfo.Column("placar_usuario", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlacarUsuario = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlacarUsuario = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlacarUsuario = new TableInfo("PlacarUsuario", _columnsPlacarUsuario, _foreignKeysPlacarUsuario, _indicesPlacarUsuario);
        final TableInfo _existingPlacarUsuario = TableInfo.read(_db, "PlacarUsuario");
        if (! _infoPlacarUsuario.equals(_existingPlacarUsuario)) {
          return new RoomOpenHelper.ValidationResult(false, "PlacarUsuario(br.org.curitiba.ici.avaliacao.model.Entity.PlacarUsuario).\n"
                  + " Expected:\n" + _infoPlacarUsuario + "\n"
                  + " Found:\n" + _existingPlacarUsuario);
        }
        final HashMap<String, TableInfo.Column> _columnsUsuario = new HashMap<String, TableInfo.Column>(2);
        _columnsUsuario.put("id_usuario", new TableInfo.Column("id_usuario", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("nome_usuario", new TableInfo.Column("nome_usuario", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsuario = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsuario = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsuario = new TableInfo("Usuario", _columnsUsuario, _foreignKeysUsuario, _indicesUsuario);
        final TableInfo _existingUsuario = TableInfo.read(_db, "Usuario");
        if (! _infoUsuario.equals(_existingUsuario)) {
          return new RoomOpenHelper.ValidationResult(false, "Usuario(br.org.curitiba.ici.avaliacao.model.Entity.Usuario).\n"
                  + " Expected:\n" + _infoUsuario + "\n"
                  + " Found:\n" + _existingUsuario);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "2b8fbbcec32eec3f7663422ddae498d5", "0478785a967551b841312b6732dfc89b");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "PlacarUsuario","Usuario");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `PlacarUsuario`");
      _db.execSQL("DELETE FROM `Usuario`");
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
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(PlacarUsuarioDao.class, PlacarUsuarioDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UsuarioDao.class, UsuarioDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public PlacarUsuarioDao getPlacarUsuarioDao() {
    if (_placarUsuarioDao != null) {
      return _placarUsuarioDao;
    } else {
      synchronized(this) {
        if(_placarUsuarioDao == null) {
          _placarUsuarioDao = new PlacarUsuarioDao_Impl(this);
        }
        return _placarUsuarioDao;
      }
    }
  }

  @Override
  public UsuarioDao getUsuarioDao() {
    if (_usuarioDao != null) {
      return _usuarioDao;
    } else {
      synchronized(this) {
        if(_usuarioDao == null) {
          _usuarioDao = new UsuarioDao_Impl(this);
        }
        return _usuarioDao;
      }
    }
  }
}
