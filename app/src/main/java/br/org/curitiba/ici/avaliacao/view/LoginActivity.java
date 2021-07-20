package br.org.curitiba.ici.avaliacao.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import br.org.curitiba.ici.avaliacao.R;
import br.org.curitiba.ici.avaliacao.helper.GerenciadorDialogos;
import br.org.curitiba.ici.avaliacao.helper.GerenciadorSharedPreferences;
import br.org.curitiba.ici.avaliacao.model.Entity.Usuario;
import br.org.curitiba.ici.avaliacao.model.repositorio.PlacarUsuarioRepositorio;
import br.org.curitiba.ici.avaliacao.model.repositorio.UsuarioRepositorio;
import br.org.curitiba.ici.avaliacao.view.async.SalvarUsuarioTask;

public class LoginActivity extends AppCompatActivity implements SalvarUsuarioTask.SalvarDadosUsuario{

    private TextInputLayout inputLayout;
    private TextInputEditText campoUsuario;
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        view();
    }

    private void view() {
        inputLayout = findViewById(R.id.inputLayout);
        campoUsuario = findViewById(R.id.editUser);
    }

    public void login(View view) {
        inputLayout.setError("");
        nomeUsuario = Objects.requireNonNull(campoUsuario.getText()).toString().trim();
        verificaPreenchimentoNomeUsuario();
    }

    private void verificaPreenchimentoNomeUsuario() {
        boolean estaPreenchido = true;
        if(nomeUsuario.isEmpty()){
            inputLayout.setError(getString(R.string.txt_erro_preencha_campo_usuario));
            estaPreenchido = false;
        }
        if(estaPreenchido){
            salvarNomeUsuario();
        }
    }

    private void salvarNomeUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(nomeUsuario);
        salvarUsuario(usuario);
    }

    public void salvarUsuario(Usuario usuario){
        UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio(this);
        usuarioRepositorio.salvaDadosUsuario(usuario, this);
    }

    @Override
    public void salvarDadosUsuario(Usuario dadosUsuario) {
        GerenciadorSharedPreferences.setNomeUsuario(this, dadosUsuario.getNomeUsuario());
        GerenciadorSharedPreferences.setIdUsuario(this, dadosUsuario.getIdUsuario());

        Intent intent = new Intent(this, JogoActivity.class);
        startActivity(intent);
        finish();
    }

    public void sair_app(View view) {
        finishAffinity();
    }
}