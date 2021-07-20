package br.org.curitiba.ici.avaliacao.componentes;

import android.content.Context;

import java.util.Random;

import br.org.curitiba.ici.avaliacao.R;

public class NomeJogadorDois {
    public String nomeJogadorDois(Context context) {
        int random = new Random().nextInt(10);
        String[] opcoes = context.getResources().getStringArray(R.array.nomes_jogador_dois);
        return opcoes[random];
    }
}
