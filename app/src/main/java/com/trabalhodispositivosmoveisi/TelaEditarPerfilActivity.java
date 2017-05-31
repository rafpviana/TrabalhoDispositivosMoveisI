package com.trabalhodispositivosmoveisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import io.realm.Realm;

public class TelaEditarPerfilActivity extends AppCompatActivity {

    //private ImageView imageViewEditarFotoPerfil;
    private EditText editTextEditarNome, editTextEditarGenero, editTextEditarIdade, editTextEditarPeso, editTextEditarAltura, editTextEditarEmail, editTextEditarAntigaSenha, editTextEditarNovaSenha, editTextEditarConfirmarNovaSenha;
    private Button buttonEditarSalvar, buttonEditarCancelar;
    private Spinner spinnerNivelAtividade;
    private Usuario usuario;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar_perfil);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        encontrarViewa();

        Intent intentDaTelaPerfil = getIntent();
        int idUsuario = intentDaTelaPerfil.getIntExtra("idUsuario", 0);

        if(idUsuario != 0){
            usuario = obterUsuarioDoRealmPeloId(idUsuario);

            if(usuario != null){
//                Toast.makeText(this, "Id do usuário: "+usuario.getId(), Toast.LENGTH_SHORT).show();
                preencherDadosDoPerfilDoUsuario();
                configurarButtonEditarCancelar();
                configurarButtonEditarSalvar();
            }
            else{
                Toast.makeText(this, "Usuário inválido!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void encontrarViewa(){
        //imageViewEditarFotoPerfil = (ImageView) findViewById(R.id.imageview_editar_foto_perfil);
        editTextEditarNome = (EditText) findViewById(R.id.edittext_editar_nome);
        editTextEditarGenero = (EditText) findViewById(R.id.edittext_editar_genero);
        editTextEditarIdade = (EditText) findViewById(R.id.edittext_editar_idade);
        editTextEditarPeso = (EditText) findViewById(R.id.edittext_editar_peso);
        editTextEditarAltura = (EditText) findViewById(R.id.edittext_editar_altura);
        editTextEditarEmail = (EditText) findViewById(R.id.edittext_editar_email);
        editTextEditarAntigaSenha = (EditText) findViewById(R.id.edittext_editar_antiga_senha);
        editTextEditarNovaSenha = (EditText) findViewById(R.id.edittext_editar_nova_senha);
        editTextEditarConfirmarNovaSenha = (EditText) findViewById(R.id.edittext_editar_confirmar_nova_senha);
        buttonEditarCancelar = (Button) findViewById(R.id.button_editar_cancelar);
        buttonEditarSalvar = (Button) findViewById(R.id.button_editar_salvar);
        spinnerNivelAtividade = (Spinner) findViewById(R.id.spinner_nivel_atividade);
    }

    private Usuario obterUsuarioDoRealmPeloId(int idUsuario){
        Usuario usuarioTeste = realm.where(Usuario.class).equalTo("id", idUsuario).findFirst();
        return usuarioTeste;

    }

    private void preencherDadosDoPerfilDoUsuario(){
        editTextEditarNome.setText(usuario.getNome());
        editTextEditarGenero.setText(usuario.getGenero());
        editTextEditarIdade.setText(Integer.toString(usuario.getIdade()));
        editTextEditarPeso.setText(Float.toString(usuario.getPeso()));
        editTextEditarAltura.setText(Float.toString(usuario.getAltura()));
        editTextEditarEmail.setText(usuario.getEmail());
        spinnerNivelAtividade.setSelection(usuario.getNivelAtividade()-1);
    }

    private void configurarButtonEditarCancelar(){
        buttonEditarCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void configurarButtonEditarSalvar(){
        buttonEditarSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean atualizarUsuario = false, atualizarNome = false, atualizarGenero = false, atualizarIdade = false,
                        atualizarPeso = false, atualizarAltura = false, atualizarEmail = false, atualizarSenha = false,
                        atualizarNivelAtividade = false;


                String textoNome = editTextEditarNome.getText().toString();
                if(textoNome != null && !textoNome.isEmpty()){
                    if(!textoNome.equals(usuario.getNome())){
                        atualizarUsuario = true;
                        atualizarNome = true;
                    }
                }
                else{
                    Toast.makeText(TelaEditarPerfilActivity.this, "Nome inválido!", Toast.LENGTH_SHORT).show();
                    atualizarUsuario = false;
                }

                String textoGenero = editTextEditarGenero.getText().toString();
                if(textoGenero != null && !textoGenero.isEmpty()){
                    if(textoGenero.equals("masculino") || textoGenero.equals("Masculino") || textoGenero.equals("MASCULINO")
                            || textoGenero.equals("feminino") || textoGenero.equals("Feminino") || textoGenero.equals("FEMININO")){
                        if(!textoGenero.equals(usuario.getGenero())){
                            atualizarUsuario = true;
                            atualizarGenero = true;
                        }
                    }
                    else{
                        Toast.makeText(TelaEditarPerfilActivity.this, "Gênero inválido!", Toast.LENGTH_SHORT).show();
                        atualizarUsuario = false;
                    }
                }
                else{
                    Toast.makeText(TelaEditarPerfilActivity.this, "Gênero inválido!", Toast.LENGTH_SHORT).show();
                    atualizarUsuario = false;
                }

                String textoIdade = editTextEditarIdade.getText().toString();
                if(textoIdade != null && !textoIdade.isEmpty() && Integer.parseInt(textoIdade) > 0){
                    if(Integer.parseInt(textoIdade) != usuario.getIdade()){
                        atualizarUsuario = true;
                        atualizarIdade = true;
                    }
                }
                else{
                    Toast.makeText(TelaEditarPerfilActivity.this, "Idade inválida!", Toast.LENGTH_SHORT).show();
                    atualizarUsuario = false;
                }


                int opcaoNivelAtividade = spinnerNivelAtividade.getSelectedItemPosition()+1;
                if(opcaoNivelAtividade != usuario.getNivelAtividade()){
                    atualizarUsuario = true;
                    atualizarNivelAtividade = true;
                }

                String textoPeso = editTextEditarPeso.getText().toString();
                if(textoPeso != null && !textoPeso.isEmpty() && Float.parseFloat(textoPeso)>0){
                    if(Float.parseFloat(textoPeso) != usuario.getPeso()){
                        atualizarUsuario = true;
                        atualizarPeso = true;
                    }
                }
                else{
                    Toast.makeText(TelaEditarPerfilActivity.this, "Peso invválido!", Toast.LENGTH_SHORT).show();
                    atualizarUsuario = false;
                }

                String textoAltura = editTextEditarAltura.getText().toString();
                if(textoAltura != null && !textoAltura.isEmpty() && Float.parseFloat(textoAltura) > 0){
                    if(Float.parseFloat(textoAltura) != usuario.getAltura()){
                        atualizarUsuario = true;
                        atualizarAltura = true;
                    }
                }
                else{
                    Toast.makeText(TelaEditarPerfilActivity.this, "Altura inválida!", Toast.LENGTH_SHORT).show();
                    atualizarUsuario = false;
                }

                String textoEmail = editTextEditarEmail.getText().toString();
                if(textoEmail != null && !textoEmail.isEmpty()){
                    if(!textoEmail.equals(usuario.getEmail())){
                        atualizarUsuario = true;
                        atualizarEmail = true;
                    }
                }
                else{
                    Toast.makeText(TelaEditarPerfilActivity.this, "Email inválido!", Toast.LENGTH_SHORT).show();
                    atualizarUsuario = false;
                }

                String textoAntigaSenha = editTextEditarAntigaSenha.getText().toString();
                String textoNovaSenha = editTextEditarNovaSenha.getText().toString();
                String textoConfirmarNovaSenha = editTextEditarConfirmarNovaSenha.getText().toString();

                if (textoNovaSenha != null && !textoNovaSenha.isEmpty()) {

                    if (textoNovaSenha.length() >= 6) {
                        if (textoConfirmarNovaSenha != null && !textoConfirmarNovaSenha.isEmpty()) {
                            if (textoConfirmarNovaSenha.equals(textoNovaSenha)) {
                                if (textoAntigaSenha != null && !textoAntigaSenha.isEmpty() && textoAntigaSenha.equals(usuario.getSenha())) {
                                    if (!textoNovaSenha.equals(usuario.getSenha())) {
                                        atualizarUsuario = true;
                                        atualizarSenha = true;
                                    }
                                    else{
                                        Toast.makeText(TelaEditarPerfilActivity.this, "Nova senha igual a antiga senha!", Toast.LENGTH_SHORT).show();
                                        atualizarUsuario = false;
                                    }
                                } else {
                                    Toast.makeText(TelaEditarPerfilActivity.this, "Antiga senha inválida!", Toast.LENGTH_SHORT).show();
                                    atualizarUsuario = false;
                                }
                            } else {
                                Toast.makeText(TelaEditarPerfilActivity.this, "Confirmação da nova senha não corresponde!", Toast.LENGTH_SHORT).show();
                                atualizarUsuario = false;
                            }
                        } else {
                            Toast.makeText(TelaEditarPerfilActivity.this, "Confirmação da nova senha inválida!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(TelaEditarPerfilActivity.this, "A nova senha deve ter no mínimo 6 dígitos!", Toast.LENGTH_SHORT).show();
                        atualizarUsuario = false;
                    }
                }

                if(atualizarUsuario){

                    realm.beginTransaction();

                    if(atualizarNome){
                        usuario.setNome(textoNome);
                    }

                    if(atualizarGenero){
                        usuario.setGenero(textoGenero);
                    }

                    if(atualizarIdade){
                        usuario.setIdade(Integer.parseInt(textoIdade));
                    }

                    if(atualizarNivelAtividade){
                        usuario.setNivelAtividade(opcaoNivelAtividade);
                    }

                    if(atualizarPeso){
                        usuario.setPeso(Float.parseFloat(textoPeso));
                    }

                    if(atualizarAltura){
                        usuario.setAltura(Float.parseFloat(textoAltura));
                    }

                    if(atualizarEmail){
                        usuario.setEmail(textoEmail);
                    }

                    if(atualizarSenha){
                        usuario.setSenha(textoNovaSenha);
                    }

                    realm.commitTransaction();

                    Toast.makeText(TelaEditarPerfilActivity.this, "Usuário editado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(TelaEditarPerfilActivity.this, "Usuário não editado!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}