package br.org.curitiba.ici.avaliacao.componentes;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

import br.org.curitiba.ici.avaliacao.R;
import br.org.curitiba.ici.avaliacao.helper.GerenciadorSharedPreferences;
import br.org.curitiba.ici.avaliacao.model.Entity.PlacarUsuario;

import static br.org.curitiba.ici.avaliacao.helper.Constantes.LAGARTO;
import static br.org.curitiba.ici.avaliacao.helper.Constantes.PAPEL;
import static br.org.curitiba.ici.avaliacao.helper.Constantes.PEDRA;
import static br.org.curitiba.ici.avaliacao.helper.Constantes.SPOCK;
import static br.org.curitiba.ici.avaliacao.helper.Constantes.TESOURA;

public class OpcaoSelecionada {

    public void opcaoSelecionada(Context context, PlacarUsuario placarUsuario, String escolhaUsuario, ImageView getImageViewJogadorUm, ImageView getImageViewJogadorDois,
                                 TextView getTextResultado, RadioGroup clearRadioGroup, RadioGroup clearRadioGroupNerd){

        String opcoesJogadorDois = getOpcaoJogadorDois(context);

        escolhaJogadorUm(getImageViewJogadorUm, escolhaUsuario);
        escolhaJogadorDois(getImageViewJogadorDois, opcoesJogadorDois);

        new RegraGanhador(context, placarUsuario, escolhaUsuario, opcoesJogadorDois, getTextResultado, clearRadioGroup, clearRadioGroupNerd);
    }

    private String getOpcaoJogadorDois(Context context) {
        String opcoesJogadorDois;
        if(!GerenciadorSharedPreferences.getIsNerd(context)){
            opcoesJogadorDois = brainJogadorDois(context);
        }else {
            opcoesJogadorDois = brainJogadorDoisNerd(context);
        }
        return opcoesJogadorDois;
    }

    private void escolhaJogadorUm(ImageView getImage, String escolhaUsuario) {
        switch (escolhaUsuario){
            case PEDRA:
                getImage.setImageResource(R.drawable.img_pedra);
                break;
            case PAPEL:
                getImage.setImageResource(R.drawable.img_papel);
                break;
            case TESOURA:
                getImage.setImageResource(R.drawable.img_tesoura);
                break;
            case LAGARTO:
                getImage.setImageResource(R.drawable.img_lagarto);
                break;
            case SPOCK:
                getImage.setImageResource(R.drawable.img_spock);
                break;
        }
    }

    private void escolhaJogadorDois(ImageView getImage, String escolhaJogadorDois) {
        switch (escolhaJogadorDois){
            case PEDRA:
                getImage.setImageResource(R.drawable.img_pedra);
                break;
            case PAPEL:
                getImage.setImageResource(R.drawable.img_papel);
                break;
            case TESOURA:
                getImage.setImageResource(R.drawable.img_tesoura);
                break;
            case LAGARTO:
                getImage.setImageResource(R.drawable.img_lagarto);
                break;
            case SPOCK:
                getImage.setImageResource(R.drawable.img_spock);
                break;
        }
    }

    private String brainJogadorDois(Context context) {
        int random = new Random().nextInt(3);
        String[] opcoes = context.getResources().getStringArray(R.array.opcoes);
        return opcoes[random];
    }

    private String brainJogadorDoisNerd(Context context) {
        int random = new Random().nextInt(5);
        String[] opcoes = context.getResources().getStringArray(R.array.opcoesNerd);
        return opcoes[random];
    }
}
