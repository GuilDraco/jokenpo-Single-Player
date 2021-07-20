package br.org.curitiba.ici.avaliacao.view.async;

import android.os.AsyncTask;

import br.org.curitiba.ici.avaliacao.model.Dao.PlacarUsuarioDao;
import br.org.curitiba.ici.avaliacao.model.Entity.PlacarUsuario;

public class SalvarPlacarTask extends AsyncTask<Void, Void, PlacarUsuario> {

    private final PlacarUsuario placarUsuario;
    private final PlacarUsuarioDao placarUsuarioDao;
    private final SalvarDadosPlacar salvarDadosPlacar;

    public SalvarPlacarTask(PlacarUsuario placarUsuario, PlacarUsuarioDao placarUsuarioDao, SalvarDadosPlacar salvarDadosPlacar) {
        this.placarUsuario = placarUsuario;
        this.placarUsuarioDao = placarUsuarioDao;
        this.salvarDadosPlacar = salvarDadosPlacar;
    }

    @Override
    protected PlacarUsuario doInBackground(Void... voids) {
        placarUsuarioDao.salvarPlacarUsuario(placarUsuario);
        salvarDadosPlacar.salvarDadosPlacar(placarUsuario);
        return new PlacarUsuario();
    }

    @Override
    protected void onPostExecute(PlacarUsuario placarUsuario) {
        super.onPostExecute(placarUsuario);
        salvarDadosPlacar.salvarDadosPlacar(placarUsuario);
    }

    public interface SalvarDadosPlacar{
        void salvarDadosPlacar(PlacarUsuario dadosPlacar);
    }
}
