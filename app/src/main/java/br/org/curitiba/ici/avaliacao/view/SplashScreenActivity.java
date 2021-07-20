package br.org.curitiba.ici.avaliacao.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import br.org.curitiba.ici.avaliacao.R;
import br.org.curitiba.ici.avaliacao.componentes.HundleAcesso;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler = new Handler();

        HundleAcesso hundleAcesso = new HundleAcesso(this, SplashScreenActivity.this);
        hundleAcesso.verificarAcesso(handler);
    }
}