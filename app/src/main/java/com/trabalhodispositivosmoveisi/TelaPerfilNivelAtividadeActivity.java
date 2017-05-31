package com.trabalhodispositivosmoveisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class TelaPerfilNivelAtividadeActivity extends AppCompatActivity {


    //private ImageView imageViewCdastroFotoPerfil;
    private TextView textViewCadastroNome, textViewCadastroGenero, textViewCadastroIdade;
    private Button buttonCadastroAvancar;
    private RadioButton radioButtonOpcao1, radioButtonOpcao2, radioButtonOpcao3;
    private Usuario usuario;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil_nivel_atividade);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        encontrarView();

        Intent intentDaTelaPerfilInformacoesFisicas = getIntent();
        usuario = (Usuario) intentDaTelaPerfilInformacoesFisicas.getSerializableExtra("usuario");

        if(usuario != null){
//            Toast.makeText(this, "Id do usuário: "+usuario.getId(), Toast.LENGTH_SHORT).show();
            preencherCabecalho();
            configurarButtonCadastroAvancar();
        }


    }

    private void encontrarView(){
        //imageViewCdastroFotoPerfil = (ImageView) findViewById(R.id.imageview_cadastro_foto_perfil_3);
        textViewCadastroNome = (TextView) findViewById(R.id.textview_cadastro_nome_2);
        textViewCadastroGenero = (TextView) findViewById(R.id.textview_cadastro_genero_2);
        textViewCadastroIdade = (TextView) findViewById(R.id.textview_cadastro_idade_2);
        radioButtonOpcao1 = (RadioButton) findViewById(R.id.radiobutton_opcao_1);
        radioButtonOpcao2 = (RadioButton) findViewById(R.id.radiobutton_opcao_2);
        radioButtonOpcao3 = (RadioButton) findViewById(R.id.radiobutton_opcao_3);
        buttonCadastroAvancar = (Button) findViewById(R.id.button_cadastro_avancar_3);
    }

    private void preencherCabecalho(){

        textViewCadastroNome.setText(usuario.getNome());
        textViewCadastroGenero.setText(usuario.getGenero());
        textViewCadastroIdade.setText(Integer.toString(usuario.getIdade())+" anos");

    }

    private void configurarButtonCadastroAvancar(){
        buttonCadastroAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int nivelAtividadeSelecionado = 0;

                if(radioButtonOpcao1.isChecked()){
                    nivelAtividadeSelecionado = 1;
                }
                else if(radioButtonOpcao2.isChecked()){
                    nivelAtividadeSelecionado = 2;
                }
                else if(radioButtonOpcao3.isChecked()){
                    nivelAtividadeSelecionado = 3;
                }

                if(nivelAtividadeSelecionado != 0){
                    usuario.setNivelAtividade(nivelAtividadeSelecionado);
                    usuario.setLogado(true);
                    salvarUsuarioNoRealm();
                    Toast.makeText(TelaPerfilNivelAtividadeActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    irParaTelaPerfil();
                }
                else{
                    Toast.makeText(TelaPerfilNivelAtividadeActivity.this, "Nenhum nível de atividade selecionado!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void salvarUsuarioNoRealm(){
        realm.beginTransaction();
        realm.insert(usuario);
        realm.commitTransaction();
    }

    private void irParaTelaPerfil(){
        Intent intentParaTelaPerfilActivity = new Intent(TelaPerfilNivelAtividadeActivity.this, TelaPerfilActivity.class);
        intentParaTelaPerfilActivity.putExtra("idUsuario", usuario.getId());
        intentParaTelaPerfilActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
        startActivity(intentParaTelaPerfilActivity);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
