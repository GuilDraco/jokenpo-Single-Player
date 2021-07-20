package br.org.curitiba.ici.avaliacao.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.org.curitiba.ici.avaliacao.BuildConfig;
import br.org.curitiba.ici.avaliacao.model.Dao.PlacarUsuarioDao;
import br.org.curitiba.ici.avaliacao.model.Dao.UsuarioDao;
import br.org.curitiba.ici.avaliacao.model.Entity.PlacarUsuario;
import br.org.curitiba.ici.avaliacao.model.Entity.Usuario;

import static br.org.curitiba.ici.avaliacao.model.HistoricoMigracao.TODAS_MIGRACOES;

@Database(entities = {PlacarUsuario.class, Usuario.class}, version = 1, exportSchema = false)

public abstract class IciDataBase extends RoomDatabase {
    public abstract PlacarUsuarioDao getPlacarUsuarioDao();
    public abstract UsuarioDao getUsuarioDao();

    public static synchronized IciDataBase getInstance(Context context) {
        return Room
                .databaseBuilder(context, IciDataBase.class, BuildConfig.APPLICATION_ID+".bd")
                .addMigrations(TODAS_MIGRACOES)
                .build();
    }
}
