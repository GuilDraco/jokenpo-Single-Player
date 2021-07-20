package br.org.curitiba.ici.avaliacao.model.repositorio;

import android.content.Context;

import br.org.curitiba.ici.avaliacao.model.Dao.UsuarioDao;
import br.org.curitiba.ici.avaliacao.model.Entity.Usuario;
import br.org.curitiba.ici.avaliacao.model.IciDataBase;
import br.org.curitiba.ici.avaliacao.view.async.DeletarUsuariosTask;
import br.org.curitiba.ici.avaliacao.view.async.SalvarUsuarioTask;

public class UsuarioRepositorio {
    private final UsuarioDao usuarioDao;

    public UsuarioRepositorio(Context context) {
        usuarioDao = IciDataBase.getInstance(context)
                .getUsuarioDao();
    }

    public void salvaDadosUsuario(Usuario usuario, SalvarUsuarioTask.SalvarDadosUsuario salvarDadosUsuario){
        if(usuario != null) {
            new SalvarUsuarioTask(usuario, usuarioDao, salvarDadosUsuario)
                    .execute();
        }
    }

    public void deletarUsuarios(){
        if(usuarioDao!=null){
            new DeletarUsuariosTask(usuarioDao).execute();
        }
    }
}
