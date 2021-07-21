package br.org.curitiba.ici.avaliacao.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import br.org.curitiba.ici.avaliacao.componentes.NomeJogadorDois;
import br.org.curitiba.ici.avaliacao.componentes.OpcaoSelecionada;
import br.org.curitiba.ici.avaliacao.R;
import br.org.curitiba.ici.avaliacao.helper.GerenciadorDialogos;
import br.org.curitiba.ici.avaliacao.helper.GerenciadorSharedPreferences;
import br.org.curitiba.ici.avaliacao.model.Entity.PlacarUsuario;
import br.org.curitiba.ici.avaliacao.model.repositorio.PlacarUsuarioRepositorio;
import br.org.curitiba.ici.avaliacao.model.repositorio.UsuarioRepositorio;

import static br.org.curitiba.ici.avaliacao.helper.Constantes.LAGARTO;
import static br.org.curitiba.ici.avaliacao.helper.Constantes.PAPEL;
import static br.org.curitiba.ici.avaliacao.helper.Constantes.PEDRA;
import static br.org.curitiba.ici.avaliacao.helper.Constantes.SPOCK;
import static br.org.curitiba.ici.avaliacao.helper.Constantes.TESOURA;

public class JogoActivity extends AppCompatActivity {

    private final OpcaoSelecionada opcaoSelecionada = new OpcaoSelecionada();
    private ImageView getImageViewJogadorDois, getImageViewJogadorUm;
    private TextView getTextView;
    private RadioGroup radioGroup;
    private RadioGroup radioGroupNerd;
    private Button btnJokenpo;
    private String escolhaUsuario;
    private final PlacarUsuario placarUsuario = new PlacarUsuario();
    private ConstraintLayout constraintLayoutNerd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jogo_activity);
        verificarDadosUsuario();
    }

    private void verificarDadosUsuario() {
        String nomeUsuario = GerenciadorSharedPreferences.getNomeUsuario(this);
        NomeJogadorDois nomeJogadorDois = new NomeJogadorDois();
        String nomeSegundoJogador = nomeJogadorDois.nomeJogadorDois(this);

        view(nomeUsuario, nomeSegundoJogador);
    }

    private void view(String nomeUsuario, String nomeSegundoJogador) {
        getImageViewJogadorUm = findViewById(R.id.img_jogador_um);
        TextView nomeJogarUm = findViewById(R.id.txt_jogador_1);
        nomeJogarUm.setText(nomeUsuario);

        getImageViewJogadorDois = findViewById(R.id.img_jogador_dois);
        TextView nomeJogadorDois = findViewById(R.id.txt_jogador_2);
        nomeJogadorDois.setText(nomeSegundoJogador);

        getTextView = findViewById(R.id.txt_resultado);
        radioGroup = findViewById(R.id.areaRadioGroup);
        radioGroupNerd = findViewById(R.id.areaRadioGroupNerd);
        constraintLayoutNerd = findViewById(R.id.constraintLayoutNerd);

        btnJokenpo = findViewById(R.id.btn_jokenpo);
        setOpcaoSelecionada();

        verificaDificuldade();
        jokenpo();
    }

    private void verificaDificuldade() {
        if(GerenciadorSharedPreferences.getIsNerd(this)) {
            constraintLayoutNerd.setVisibility(View.VISIBLE);
            setOpcaoSelecionadaNerd();
        }else {
            constraintLayoutNerd.setVisibility(View.GONE);
        }
    }

    private void setOpcaoSelecionadaNerd() {
        radioGroupNerd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radioButtonLagarto){
                    escolhaUsuario = LAGARTO;
                }else {
                    escolhaUsuario = SPOCK;
                }
            }
        });
    }

    public void setOpcaoSelecionada(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radioButtonPedra){
                    escolhaUsuario = PEDRA;
                }else if(checkedId==R.id.radioButtonPapel){
                    escolhaUsuario = PAPEL;
                }else if(checkedId==R.id.radioButtonTesoura){
                    escolhaUsuario = TESOURA;
                }else if(checkedId==R.id.radioButtonLagarto){
                    escolhaUsuario = LAGARTO;
                }else if(checkedId==R.id.radioButtonSpock){
                    escolhaUsuario = SPOCK;
                }
            }
        });
    }

    public void jokenpo(){
        btnJokenpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getImageViewJogadorUm !=null && getImageViewJogadorDois !=null && escolhaUsuario !=null) {
                    opcaoSelecionada.opcaoSelecionada(JogoActivity.this, placarUsuario, escolhaUsuario, getImageViewJogadorUm, getImageViewJogadorDois, getTextView,
                            radioGroup, radioGroupNerd);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null) {

            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.menu_placar, menu);

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(R.string.app_name);

            ColorDrawable corFundo = new ColorDrawable(this.getColor(R.color.colorPrimary));
            actionBar.setBackgroundDrawable(corFundo);
            actionBar.setElevation(0);

        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.acao_nerd);
        if(!GerenciadorSharedPreferences.getIsNerd(this)){
            item.setTitle(R.string.txt_habilitar_nerd);
        }else {
            item.setTitle(R.string.txt_desabilitar_nerd);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finishAffinity();
        }
        return true;
    }

    public void mostrarPlacar(MenuItem item) {
        Intent intent = new Intent(this, PlacarActivity.class);
        startActivity(intent);
    }

    public void sairAplicativo(MenuItem item) {
        AlertDialog.OnClickListener ficarAplicativo = new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
        AlertDialog.OnClickListener sairAplicativo = new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                apagarDadosBanco();
            }
        };
        AlertDialog alertDialog = GerenciadorDialogos.buildAlertDialog(this,
                R.string.aviso_sair_apk,
                R.string.txt_sair_apk,
                R.string.txt_sair_sim,
                sairAplicativo,
                R.string.txt_nao_sair,
                ficarAplicativo);
        alertDialog.show();
    }

    public void habilitarVersaoNerd(MenuItem item) {
        GerenciadorSharedPreferences.setIsNerd(this, !GerenciadorSharedPreferences.getIsNerd(this));
        Intent intent = new Intent(this, JogoActivity.class);
        startActivity(intent);
        finish();
    }

    public void apagarDadosBanco(){
        GerenciadorSharedPreferences.setNomeUsuario(this, null);
        GerenciadorSharedPreferences.setIsNerd(this, false);

        UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio(this);
        usuarioRepositorio.deletarUsuarios();

        PlacarUsuarioRepositorio placarUsuarioRepositorio = new PlacarUsuarioRepositorio(this);
        placarUsuarioRepositorio.deletarPlacaresUsuario();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}