package com.trabalhodispositivosmoveisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class TelaPerfilInformacoesPessoaisActivity extends AppCompatActivity {

    private ImageView imagemFoto;
    private EditText textoEditavelNome,  textoEditavelGenero, textEditavelDataNascimento;
    private Button botaoAvancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil_informacoes_pessoais);

        imagemFoto = (ImageView) findViewById(R.id.imagemFoto);
        textoEditavelNome = (EditText) findViewById(R.id.textoEditavelNome);
        textoEditavelGenero = (EditText) findViewById(R.id.textoEditavelGenero);
        textEditavelDataNascimento = (EditText) findViewById(R.id.textoEditavelDataNascimento);
        botaoAvancar = (Button) findViewById(R.id.botaoAvancar);

        botaoAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaPerfilInformacoesPessoaisActivity.this, TelaPerfilInformacoesFisicasActivity.class);
                startActivity(intent);
            }
        });
    }
}
