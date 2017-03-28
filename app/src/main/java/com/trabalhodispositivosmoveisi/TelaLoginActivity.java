package com.trabalhodispositivosmoveisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TelaLoginActivity extends AppCompatActivity {

    private EditText editTextLoginEmailOuUsuario;
    private EditText editTextLoginSenha;
    private TextView textViewCriarConta;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        editTextLoginEmailOuUsuario = (EditText) findViewById(R.id.editTextLoginEmailOuUsuario);
        editTextLoginSenha = (EditText) findViewById(R.id.editTextLoginSenha);

        textViewCriarConta = (TextView) findViewById(R.id.textViewCriarConta);
        textViewCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentParaTelaCadastroActivity = new Intent(TelaLoginActivity.this, TelaCadastroActivity.class);
                startActivity(intentParaTelaCadastroActivity);
            }
        });

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mIntent = new Intent(TelaLoginActivity.this, TelaPerfilActivity.class);
                startActivity(mIntent);
            }
        });
    }
}