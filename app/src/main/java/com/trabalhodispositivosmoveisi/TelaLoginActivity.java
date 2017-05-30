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
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class TelaLoginActivity extends AppCompatActivity {

    private EditText editTextLoginEmail;
    private EditText editTextLoginSenha;
    private TextView textViewCriarConta;
    private Button buttonLogin;
    private Usuario usuario;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        //Usado somente se for necessário resetar o BD, para isso descomente esta linha e comente a linha "realm = Realm.getDefaultInstance()" acima
//        resetarBancoDeDadosRealm();

        encontrarViews();

        usuario = obterUsuarioLogado();

        if(usuario == null){
            configurarTextViewCriarConta();
            configurarButtonLogin();
        }
        else{
            irParaTelaPerfil();
        }
    }

    private void encontrarViews() {
        editTextLoginEmail = (EditText) findViewById(R.id.edittext_login_email);
        editTextLoginSenha = (EditText) findViewById(R.id.edittext_login_senha);
        textViewCriarConta = (TextView) findViewById(R.id.textview_criar_conta);
        buttonLogin = (Button) findViewById(R.id.button_login);
    }

    private Usuario obterUsuarioLogado(){
        RealmResults<Usuario> listaDeUsuariosNoRealm = realm.where(Usuario.class).findAll();
        for(Usuario usuarioTeste : listaDeUsuariosNoRealm){
            if(usuarioTeste.isLogado()){
                return usuarioTeste;
            }
        }
        return null;
    }

    private void configurarTextViewCriarConta() {
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

    private void configurarButtonLogin() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarLogin()){
                    irParaTelaPerfil();
                }
            }
        });
    }

    private boolean validarLogin(){

        if(editTextLoginEmail.getText().toString() != null && !editTextLoginEmail.getText().toString().isEmpty()
                && editTextLoginSenha.getText().toString() != null && !editTextLoginSenha.getText().toString().isEmpty() ){

            String emailUsuario = editTextLoginEmail.getText().toString();
            String senhaUsuario = editTextLoginSenha.getText().toString();
            usuario = realm.where(Usuario.class).equalTo("email", emailUsuario).findFirst();
            if(usuario != null) {
                if (usuario.getSenha().equals(senhaUsuario)) {
                    alterarAtributoLogadoDoUsuarioSalvoNoRealm();
                    Toast.makeText(this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    Toast.makeText(this, "Senha inválida!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            else{
                Toast.makeText(this, "Usuário não cadastrado!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        else{
            Toast.makeText(this, "Alguns campos estão inválidos ou não preenchidos!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void irParaTelaPerfil() {
        Intent intentIrParaTelaPerfilActivity = new Intent(TelaLoginActivity.this, TelaPerfilActivity.class);
        intentIrParaTelaPerfilActivity.putExtra("idUsuario", usuario.getId());
        intentIrParaTelaPerfilActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
        startActivity(intentIrParaTelaPerfilActivity);
    }

    private void alterarAtributoLogadoDoUsuarioSalvoNoRealm(){
        realm.beginTransaction();
        usuario.setLogado(true);
        realm.commitTransaction();

    }

    private void resetarBancoDeDadosRealm() {
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        realm = Realm.getInstance(config);

        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //realm.deleteAll();
        realm.close();
    }
}