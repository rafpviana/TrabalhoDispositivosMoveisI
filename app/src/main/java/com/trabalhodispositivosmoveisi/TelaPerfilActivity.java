package com.trabalhodispositivosmoveisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;

public class TelaPerfilActivity extends AppCompatActivity {

    private ImageView imageViewFotoPerfil;
    private TextView textViewPerfilNome, textViewPerfilPeso, textViewPerfilAltura, textViewPerfilNivelAtividade;
    private Button buttonMinhasAtividades, buttonEditarPerfil, buttonLogout;
    private Usuario usuario;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        encontrarViews();

        Intent intentDaTelaAnterior = getIntent();
        int idUsuario = intentDaTelaAnterior.getIntExtra("idUsuario", 0);

        if(idUsuario != 0){
            usuario = obterUsuarioDoRealmPeloId(idUsuario);
            if(usuario != null){
//                Toast.makeText(this, "Id do usuário: "+usuario.getId(), Toast.LENGTH_SHORT).show();
                preencherDadosDoPerfilDoUsuario();
                configurarButtonMinhasAtividades();
                configurarButtonEditarPerfil();
                configurarButtonLogout();
            }
        }
        else{
            Toast.makeText(this, "Usuário inválido!", Toast.LENGTH_SHORT).show();
        }
    }

    private Usuario obterUsuarioDoRealmPeloId(int idUsuario){
        Usuario usuarioTeste = realm.where(Usuario.class).equalTo("id", idUsuario).findFirst();
        return usuarioTeste;
    }

    private void encontrarViews(){
        imageViewFotoPerfil = (ImageView)findViewById(R.id.imageview_foto_perfil);
        textViewPerfilNome = (TextView) findViewById(R.id.textview_perfil_nome);
        textViewPerfilPeso = (TextView) findViewById(R.id.textview_perfil_peso);
        textViewPerfilAltura = (TextView) findViewById(R.id.textview_perfil_altura);
        textViewPerfilNivelAtividade = (TextView) findViewById(R.id.textview_perfil_nivel_atividade);
        buttonMinhasAtividades = (Button) findViewById(R.id.button_minhas_atividades);
        buttonEditarPerfil = (Button) findViewById(R.id.button_editar_perfil);
        buttonLogout = (Button) findViewById(R.id.button_logout);
    }

    private void preencherDadosDoPerfilDoUsuario(){
        textViewPerfilNome.setText(usuario.getNome());
        textViewPerfilPeso.setText(Float.toString(usuario.getPeso())+" kg");
        textViewPerfilAltura.setText(Float.toString(usuario.getAltura())+" m");

        if(usuario.getNivelAtividade() == 1){
            textViewPerfilNivelAtividade.setText("Atividade Leve");
        }
        else if(usuario.getNivelAtividade() == 2){
            textViewPerfilNivelAtividade.setText("Atividade Moderada");
        }
        else if(usuario.getNivelAtividade() == 3){
            textViewPerfilNivelAtividade.setText("Atividade Intensa");
        }
    }

    private void configurarButtonMinhasAtividades(){
        buttonMinhasAtividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(TelaPerfilActivity.this, TelaMinhasAtividadesActivity.class);
                startActivity(i1);
            }
        });
    }

    private void configurarButtonEditarPerfil(){
        buttonEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentParaTelaEditarPerfil = new Intent(TelaPerfilActivity.this, TelaEditarPerfilActivity.class);
                intentParaTelaEditarPerfil.putExtra("idUsuario", usuario.getId());
                startActivity(intentParaTelaEditarPerfil);
            }
        });
    }

    private void configurarButtonLogout(){
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarAtributoLogadoDoUsuarioSalvoNoRealm();
                Toast.makeText(TelaPerfilActivity.this, "Logout realizado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intentParaTelaLoginActivity = new Intent(TelaPerfilActivity.this, TelaLoginActivity.class);
                intentParaTelaLoginActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(intentParaTelaLoginActivity);
            }
        });
    }

    private void alterarAtributoLogadoDoUsuarioSalvoNoRealm(){
        realm.beginTransaction();
        usuario.setLogado(false);
        realm.commitTransaction();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Usuario usuarioTeste = realm.where(Usuario.class).equalTo("id", usuario.getId()).findFirst();
        //if(usuarioTeste != null){
        //usuario = usuarioTeste;
        preencherDadosDoPerfilDoUsuario();
        //}
    }
}