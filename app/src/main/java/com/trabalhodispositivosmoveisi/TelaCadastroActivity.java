package com.trabalhodispositivosmoveisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TelaCadastroActivity extends AppCompatActivity {
    private EditText idCadastroEmailOuUsuario;
    private EditText idCadastroSenha;
    private Button idCadastro;
    private Button idCancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        idCadastroEmailOuUsuario = (EditText) findViewById(R.id.editTextCadastroEmailOuUsuario);
        idCadastroSenha = (EditText)findViewById(R.id.editTextCdastroSenha);
        idCadastro = (Button)findViewById(R.id.idCadastro);
        idCancelar = (Button)findViewById(R.id.buttonCancelar);
        idCadastro = (Button) findViewById(R.id.idCadastro);

        idCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        idCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent irParaTelaPerfilInformacoesPessoaisActivity = new Intent(TelaCadastroActivity.this, TelaPerfilInformacoesPessoaisActivity.class);
                startActivity(irParaTelaPerfilInformacoesPessoaisActivity);
            }
        });
    }
}

