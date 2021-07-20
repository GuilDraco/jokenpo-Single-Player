package br.org.curitiba.ici.avaliacao.view.async;

import android.os.AsyncTask;

import br.org.curitiba.ici.avaliacao.model.Dao.UsuarioDao;

public class DeletarUsuariosTask extends AsyncTask<Void, Void, Void> {

    private final UsuarioDao usuarioDao;

    public DeletarUsuariosTask(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        usuarioDao.deleteUsuarios();
        return null;
    }
}
