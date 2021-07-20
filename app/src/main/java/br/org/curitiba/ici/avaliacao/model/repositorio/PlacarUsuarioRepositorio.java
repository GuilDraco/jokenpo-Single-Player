package br.org.curitiba.ici.avaliacao.model.repositorio;

import android.content.Context;

import br.org.curitiba.ici.avaliacao.model.Dao.PlacarUsuarioDao;
import br.org.curitiba.ici.avaliacao.model.Entity.PlacarUsuario;
import br.org.curitiba.ici.avaliacao.model.IciDataBase;
import br.org.curitiba.ici.avaliacao.view.async.DeletarPlacaresUsuarioTask;
import br.org.curitiba.ici.avaliacao.view.async.RecuperarPlacarUsuarioTask;
import br.org.curitiba.ici.avaliacao.view.async.SalvarPlacarTask;

public class PlacarUsuarioRepositorio {
    private final PlacarUsuarioDao placarUsuarioDao;

    public PlacarUsuarioRepositorio(Context context) {
        placarUsuarioDao = IciDataBase.getInstance(context)
                .getPlacarUsuarioDao();
    }

    public void salvaPlacarUsuario(PlacarUsuario placarUsuario, SalvarPlacarTask.SalvarDadosPlacar salvarDadosPlacar){
        if(placarUsuario != null) {
            new SalvarPlacarTask(placarUsuario, placarUsuarioDao, salvarDadosPlacar)
                    .execute();
        }
    }

    public void recuperarPlacarUsuario(Context context, RecuperarPlacarUsuarioTask.RecuperarDadosPlacar recuperarDadosPlacar){
        if(placarUsuarioDao!=null){
            new RecuperarPlacarUsuarioTask(context, placarUsuarioDao, recuperarDadosPlacar).execute();
        }
    }

    public void deletarPlacaresUsuario(){
        if(placarUsuarioDao!=null){
            new DeletarPlacaresUsuarioTask(placarUsuarioDao).execute();
        }
    }
}
