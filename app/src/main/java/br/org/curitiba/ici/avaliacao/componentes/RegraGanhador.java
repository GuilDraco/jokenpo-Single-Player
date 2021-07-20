package br.org.curitiba.ici.avaliacao.componentes;

import android.content.Context;
import android.widget.RadioGroup;
import android.widget.TextView;

import br.org.curitiba.ici.avaliacao.R;
import br.org.curitiba.ici.avaliacao.helper.GerenciadorSharedPreferences;
import br.org.curitiba.ici.avaliacao.model.Entity.PlacarUsuario;
import br.org.curitiba.ici.avaliacao.model.repositorio.PlacarUsuarioRepositorio;
import br.org.curitiba.ici.avaliacao.view.async.SalvarPlacarTask;

import static br.org.curitiba.ici.avaliacao.helper.Constantes.GANHOU;
import static br.org.curitiba.ici.avaliacao.helper.Constantes.LAGARTO;
import static br.org.curitiba.ici.avaliacao.helper.Constantes.PAPEL;
import static br.org.curitiba.ici.avaliacao.helper.Constantes.PEDRA;
import static br.org.curitiba.ici.avaliacao.helper.Constantes.PERDEU;
import static br.org.curitiba.ici.avaliacao.helper.Constantes.SPOCK;
import static br.org.curitiba.ici.avaliacao.helper.Constantes.TESOURA;

public class RegraGanhador implements SalvarPlacarTask.SalvarDadosPlacar {

    protected RegraGanhador(Context context, PlacarUsuario placarUsuario, String escolhaUsuario, String escolhaJogadorDois, TextView getTextResultado, RadioGroup clearRadioGroup, RadioGroup clearRadioGroupNerd){

        //Player dois ganhador
        if((escolhaJogadorDois.equals(PEDRA) && (escolhaUsuario.equals(TESOURA) || escolhaUsuario.equals(LAGARTO)) ||
                (escolhaJogadorDois.equals(PAPEL) && (escolhaUsuario.equals(PEDRA) || escolhaUsuario.equals(SPOCK)) ||
                (escolhaJogadorDois.equals(TESOURA) && (escolhaUsuario.equals(PAPEL) || escolhaUsuario.equals(LAGARTO)) ||
                (escolhaJogadorDois.equals(LAGARTO) && (escolhaUsuario.equals(SPOCK) || escolhaUsuario.equals(PAPEL)) ||
                (escolhaJogadorDois.equals(SPOCK) && (escolhaUsuario.equals(TESOURA) || escolhaUsuario.equals(PEDRA)))))))){

            getTextResultado.setText(R.string.txt_voce_perdeu);
            placarUsuario.setPlacarUsuario(PERDEU);
            placarUsuario.setId_usuario(GerenciadorSharedPreferences.getIdUsuario(context));
            placarUsuario.setNome_usuario(GerenciadorSharedPreferences.getNomeUsuario(context));
            salvarPlacar(context, placarUsuario);

            clearRadioGroup.clearCheck();
            clearRadioGroupNerd.clearCheck();

        //Usuário ganhador
        }else if((escolhaUsuario.equals(PEDRA) && (escolhaJogadorDois.equals(TESOURA) || escolhaJogadorDois.equals(LAGARTO)) ||
                (escolhaUsuario.equals(PAPEL) && (escolhaJogadorDois.equals(PEDRA) || escolhaJogadorDois.equals(SPOCK)) ||
                (escolhaUsuario.equals(TESOURA) && (escolhaJogadorDois.equals(PAPEL) || escolhaJogadorDois.equals(LAGARTO)) ||
                (escolhaUsuario.equals(LAGARTO) && (escolhaJogadorDois.equals(SPOCK) || escolhaJogadorDois.equals(PAPEL)) ||
                (escolhaUsuario.equals(SPOCK) && (escolhaJogadorDois.equals(TESOURA) || escolhaJogadorDois.equals(PEDRA)))))))){

            getTextResultado.setText(R.string.txt_voce_ganhou);
            placarUsuario.setPlacarUsuario(GANHOU);
            placarUsuario.setId_usuario(GerenciadorSharedPreferences.getIdUsuario(context));
            placarUsuario.setNome_usuario(GerenciadorSharedPreferences.getNomeUsuario(context));
            salvarPlacar(context, placarUsuario);

            clearRadioGroup.clearCheck();
            clearRadioGroupNerd.clearCheck();

        //Empate
        }else {
            getTextResultado.setText(R.string.txt_empatou);
            clearRadioGroup.clearCheck();
        }
    }

    public void salvarPlacar(Context context, PlacarUsuario placarUsuario){
        PlacarUsuarioRepositorio placarUsuarioRepositorio = new PlacarUsuarioRepositorio(context);
        placarUsuarioRepositorio.salvaPlacarUsuario(placarUsuario, this);
    }

    @Override
    public void salvarDadosPlacar(PlacarUsuario dadosPlacar) {

    }
}
