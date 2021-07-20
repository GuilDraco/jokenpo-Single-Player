package br.org.curitiba.ici.avaliacao.helper;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class GerenciadorSharedPreferences {
    private final static String PREFS_SESSAO = "sessao";
    private static final String NOME_USUARIO = "nome_usuario";
    private static final String ID_USUARIO = "id_usuario";
    private static final String DIFICULDADE_NERD = "is_nerd";

    public static void setNomeUsuario(Context context, String nome_usuario) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_SESSAO, MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(NOME_USUARIO, nome_usuario);
        editor.apply();
    }

    public static String getNomeUsuario(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_SESSAO, MODE_PRIVATE);
        return prefs.getString(NOME_USUARIO, null);
    }

    public static void setIdUsuario(Context context, int id_usuario) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_SESSAO, MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(ID_USUARIO, id_usuario);
        editor.apply();
    }

    public static int getIdUsuario(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_SESSAO, MODE_PRIVATE);
        return prefs.getInt(ID_USUARIO, -1);
    }

    public static void setIsNerd(Context context, boolean is_nerd) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_SESSAO, MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(DIFICULDADE_NERD, is_nerd);
        editor.apply();
    }

    public static boolean getIsNerd(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_SESSAO, MODE_PRIVATE);
        return prefs.getBoolean(DIFICULDADE_NERD, false);
    }
}
