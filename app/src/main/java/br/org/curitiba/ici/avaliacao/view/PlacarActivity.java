package br.org.curitiba.ici.avaliacao.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import br.org.curitiba.ici.avaliacao.R;
import br.org.curitiba.ici.avaliacao.model.Dao.UsuarioDao;
import br.org.curitiba.ici.avaliacao.model.Entity.PlacarUsuario;
import br.org.curitiba.ici.avaliacao.model.repositorio.PlacarUsuarioRepositorio;
import br.org.curitiba.ici.avaliacao.view.adapter.PlacarAdapter;
import br.org.curitiba.ici.avaliacao.view.async.RecuperarPlacarUsuarioTask;

public class PlacarActivity extends AppCompatActivity implements RecuperarPlacarUsuarioTask.RecuperarDadosPlacar {

    private RecyclerView placarRecyclerView;
    private List<PlacarUsuario> getPlacarUsuario;
    private TextView txtMediaVitorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placar_activy);
        view();
    }

    private void view() {
        placarRecyclerView = findViewById(R.id.recycler_view_placar);
        txtMediaVitorias = findViewById(R.id.percentual_vitoria);

        recuperarDadosUsuario();
    }

    public void recuperarDadosUsuario(){
        PlacarUsuarioRepositorio placarUsuarioRepositorio = new PlacarUsuarioRepositorio(this);
        placarUsuarioRepositorio.recuperarPlacarUsuario(this,this);
    }

    @Override
    public void recuperarDadosPlacar(List<PlacarUsuario> placarUsuarios, Float mediaVitorias) {
        if(mediaVitorias != null){
            txtMediaVitorias.setText(String.valueOf(mediaVitorias));
        }
        if(placarUsuarios!=null){
            getPlacarUsuario = placarUsuarios;
            carregarAdapter();
        }
    }

    private void carregarAdapter(){
        PlacarAdapter placarAdapter = new PlacarAdapter(getPlacarUsuario);
        placarRecyclerView.setHasFixedSize(true);
        placarRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        placarRecyclerView.setAdapter(placarAdapter);
    }
}