package com.trabalhodispositivosmoveisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;

public class TelaLoginActivity extends AppCompatActivity {

    private EditText editTextLoginEmail;
    private EditText editTextLoginSenha;
    private TextView textViewCriarConta;
    private Button buttonLogin;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        Realm.init(this);

        realm = Realm.getDefaultInstance();

        findComponentViews();

        configureTextViewCriarConta();

        configureButtonLogin();

    }

    private void findComponentViews() {
        editTextLoginEmail = (EditText) findViewById(R.id.edittext_login_email);
        editTextLoginSenha = (EditText) findViewById(R.id.edittext_login_senha);
        textViewCriarConta = (TextView) findViewById(R.id.textview_criar_conta);
        buttonLogin = (Button) findViewById(R.id.button_login);
    }

    private void configureTextViewCriarConta() {
        String myString = new String("Não tem conta? Crie uma!");
        SpannableString content = new SpannableString(myString);
        content.setSpan(new UnderlineSpan(), 0, myString.length(), 0);
        textViewCriarConta.setText(content);

        textViewCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentParaTelaCadastroActivity = new Intent(TelaLoginActivity.this, TelaCadastroActivity.class);
                startActivity(intentParaTelaCadastroActivity);
            }
        });
    }

    private void configureButtonLogin() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateLogin()){
                    Intent mIntent = new Intent(TelaLoginActivity.this, TelaPerfilActivity.class);
                    mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    finish();
                    startActivity(mIntent);
                }
            }
        });
    }

    private boolean validateLogin(){

        if(( editTextLoginEmail.getText().toString() != null && !editTextLoginEmail.getText().toString().isEmpty() ) && ( editTextLoginSenha.getText().toString() != null && !editTextLoginSenha.getText().toString().isEmpty() )){

            String userEmail = editTextLoginEmail.getText().toString();
            String userPassword = editTextLoginSenha.getText().toString();

            Usuario usuario = realm.where(Usuario.class).equalTo("email", userEmail).equalTo("password", userPassword).findFirst();

            if(usuario == null){
                Toast.makeText(this, "Usuário não cadastrado!", Toast.LENGTH_SHORT).show();
                return false;
            }
            else{
                Toast.makeText(this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        else{
            Toast.makeText(this, "Alguns campos estão inválidos ou não preenchidos", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        realm.close();
    }
}