package com.trabalhodispositivosmoveisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class TelaCadastroActivity extends AppCompatActivity {

    private EditText editTextCadastroEmail;
    private EditText editTextCadastroSenha;
    private EditText editTextCadastroConfirmacaoSenha;
    private Button buttonCadastrar;
    private Button buttonCancelarCadastro;
    private Usuario usuario;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        encontrarViews();

        configurarButtonCancelarCadastro();

        configurarButtonCadastrar();
    }

    private void encontrarViews() {
        editTextCadastroEmail = (EditText) findViewById(R.id.edittext_cadastro_email);
        editTextCadastroSenha = (EditText)findViewById(R.id.edittext_cadastro_senha);
        editTextCadastroConfirmacaoSenha = (EditText) findViewById(R.id.editext_cadastro_confirmacao_senha);
        buttonCadastrar = (Button)findViewById(R.id.button_cadastrar);
        buttonCancelarCadastro = (Button)findViewById(R.id.button_cancelar_cadastro);
    }

    private void configurarButtonCancelarCadastro() {
        buttonCancelarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void configurarButtonCadastrar() {
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTextCadastroEmail.getText().toString() != null && !editTextCadastroEmail.getText().toString().isEmpty()
                        && editTextCadastroSenha.getText().toString() != null && !editTextCadastroSenha.getText().toString().isEmpty()
                        && editTextCadastroConfirmacaoSenha.getText().toString() != null && !editTextCadastroConfirmacaoSenha.getText().toString().isEmpty()){

                    if(editTextCadastroSenha.getText().toString().equals(editTextCadastroConfirmacaoSenha.getText().toString())){
                        if(editTextCadastroSenha.getText().toString().length() >= 6) {

                            usuario = new Usuario();
                            usuario.setEmail(editTextCadastroEmail.getText().toString());
                            usuario.setSenha(editTextCadastroSenha.getText().toString());
                            usuario.setId(obterNovoIdDeUsuario());

                            if (!usuarioJaExiste()) {
                                irParaTelaPerfilInformacoesPessoais();
                            } else {
                                Toast.makeText(TelaCadastroActivity.this, "O usuário já existe!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(TelaCadastroActivity.this, "A senha deve ter no mínimo 6 digitos!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(TelaCadastroActivity.this, "As senhas não correspondem!", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(TelaCadastroActivity.this, "Alguns campos estão inválidos ou não preenchidos!", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private boolean usuarioJaExiste(){
        Usuario usuarioTeste = realm.where(Usuario.class).equalTo("email", usuario.getEmail()).findFirst();
        if(usuarioTeste  == null){
            return false;
        }
        else{
            return true;
        }
    }

    private void irParaTelaPerfilInformacoesPessoais(){
        Intent intentIrParaTelaPerfilInformacoesPessoaisActivity = new Intent(TelaCadastroActivity.this, TelaPerfilInformacoesPessoaisActivity.class);
        intentIrParaTelaPerfilInformacoesPessoaisActivity.putExtra("usuario",usuario);
//        intentIrParaTelaPerfilInformacoesPessoaisActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        finish();
        startActivity(intentIrParaTelaPerfilInformacoesPessoaisActivity);
    }

    private int obterNovoIdDeUsuario(){
        int maiorIdDeUsuario = 0;
        RealmResults<Usuario> listaDeUsuariosNoRealm = realm.where(Usuario.class).findAll();
        if(listaDeUsuariosNoRealm != null && !listaDeUsuariosNoRealm.isEmpty()){
            for(Usuario usuarioTexte : listaDeUsuariosNoRealm){
                if(usuarioTexte.getId() > maiorIdDeUsuario){
                    maiorIdDeUsuario = usuarioTexte.getId();
                }
            }
        }

        int novoIdDeUsuario = maiorIdDeUsuario+1;

        return novoIdDeUsuario;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //realm.deleteAll();
        realm.close();
    }
}

