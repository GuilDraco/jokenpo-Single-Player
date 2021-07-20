package br.org.curitiba.ici.avaliacao.model.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import br.org.curitiba.ici.avaliacao.model.Entity.Usuario;

@Dao
public interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void salvarUsuario(Usuario usuario);

    @Query("DELETE FROM Usuario")
    void deleteUsuarios();
}
