package br.org.curitiba.ici.avaliacao.model.Dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import br.org.curitiba.ici.avaliacao.model.Entity.PlacarUsuario;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PlacarUsuarioDao_Impl implements PlacarUsuarioDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PlacarUsuario> __insertionAdapterOfPlacarUsuario;

  private final SharedSQLiteStatement __preparedStmtOfDeletePlacarUsuario;

  public PlacarUsuarioDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPlacarUsuario = new EntityInsertionAdapter<PlacarUsuario>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `PlacarUsuario` (`id_banco`,`id_usuario`,`nome_usuario`,`placar_usuario`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PlacarUsuario value) {
        stmt.bindLong(1, value.getId_banco());
        stmt.bindLong(2, value.getId_usuario());
        if (value.getNome_usuario() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getNome_usuario());
        }
        if (value.getPlacarUsuario() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPlacarUsuario());
        }
      }
    };
    this.__preparedStmtOfDeletePlacarUsuario = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM PlacarUsuario";
        return _query;
      }
    };
  }

  @Override
  public void salvarPlacarUsuario(final PlacarUsuario placarUsuario) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPlacarUsuario.insert(placarUsuario);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deletePlacarUsuario() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePlacarUsuario.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeletePlacarUsuario.release(_stmt);
    }
  }

  @Override
  public List<PlacarUsuario> getPlacarUsuario() {
    final String _sql = "Select * from PlacarUsuario";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIdBanco = CursorUtil.getColumnIndexOrThrow(_cursor, "id_banco");
      final int _cursorIndexOfIdUsuario = CursorUtil.getColumnIndexOrThrow(_cursor, "id_usuario");
      final int _cursorIndexOfNomeUsuario = CursorUtil.getColumnIndexOrThrow(_cursor, "nome_usuario");
      final int _cursorIndexOfPlacarUsuario = CursorUtil.getColumnIndexOrThrow(_cursor, "placar_usuario");
      final List<PlacarUsuario> _result = new ArrayList<PlacarUsuario>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final PlacarUsuario _item;
        _item = new PlacarUsuario();
        final int _tmpId_banco;
        _tmpId_banco = _cursor.getInt(_cursorIndexOfIdBanco);
        _item.setId_banco(_tmpId_banco);
        final int _tmpId_usuario;
        _tmpId_usuario = _cursor.getInt(_cursorIndexOfIdUsuario);
        _item.setId_usuario(_tmpId_usuario);
        final String _tmpNome_usuario;
        if (_cursor.isNull(_cursorIndexOfNomeUsuario)) {
          _tmpNome_usuario = null;
        } else {
          _tmpNome_usuario = _cursor.getString(_cursorIndexOfNomeUsuario);
        }
        _item.setNome_usuario(_tmpNome_usuario);
        final String _tmpPlacarUsuario;
        if (_cursor.isNull(_cursorIndexOfPlacarUsuario)) {
          _tmpPlacarUsuario = null;
        } else {
          _tmpPlacarUsuario = _cursor.getString(_cursorIndexOfPlacarUsuario);
        }
        _item.setPlacarUsuario(_tmpPlacarUsuario);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
