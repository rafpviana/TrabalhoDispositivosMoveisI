package com.trabalhodispositivosmoveisi;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.Toast;

public class TelaPerfilInformacoesPessoaisActivity extends AppCompatActivity {

    private ImageView imageViewCadastroFotoPerfil;
    private EditText editTextCadastroNome, edittextCadastroGenero, editTextCadastroIdade;
    private Button buttonCadastroAvancar;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil_informacoes_pessoais);

        encontrarViews();

        Intent intentDaTelaCadastro = getIntent();
        usuario = (Usuario) intentDaTelaCadastro.getSerializableExtra("usuario");

        if(usuario != null){
//            Toast.makeText(this, "Id do usuário: "+usuario.getId(), Toast.LENGTH_SHORT).show();
            configurarButtonCadastroAvancar();
        }
    }

    private void encontrarViews() {
        imageViewCadastroFotoPerfil = (ImageView) findViewById(R.id.imageview_cadastro_foto_perfil_1);
        editTextCadastroNome = (EditText) findViewById(R.id.edittext_cadastro_nome);
        edittextCadastroGenero = (EditText) findViewById(R.id.edittext_cadastro_genero);
        editTextCadastroIdade = (EditText) findViewById(R.id.edittext_cadastro_idade);
        buttonCadastroAvancar = (Button) findViewById(R.id.button_cadastro_avancar_1);
    }

    private void configurarButtonCadastroAvancar() {
        buttonCadastroAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTextCadastroNome.getText().toString() != null && !editTextCadastroNome.getText().toString().isEmpty()
                        && edittextCadastroGenero.getText().toString() != null && !edittextCadastroGenero.getText().toString().isEmpty()
                        && editTextCadastroIdade.getText().toString() != null && !editTextCadastroIdade.getText().toString().isEmpty()){

                    if(edittextCadastroGenero.getText().toString().equals("masculino")
                            || edittextCadastroGenero.getText().toString().equals("Masculino")
                            || edittextCadastroGenero.getText().toString().equals("MASCULINO")
                            || edittextCadastroGenero.getText().toString().equals("feminino")
                            || edittextCadastroGenero.getText().toString().equals("Feminino")
                            || edittextCadastroGenero.getText().toString().equals("FEMININO")){

                        if(Integer.parseInt(editTextCadastroIdade.getText().toString()) > 0){
                            usuario.setNome(editTextCadastroNome.getText().toString());
                            usuario.setIdade(Integer.parseInt(editTextCadastroIdade.getText().toString()));
                            usuario.setGenero(edittextCadastroGenero.getText().toString());

                            irParaTelaPerfilInformacoesFisicas();
                        }
                        else{
                            Toast.makeText(TelaPerfilInformacoesPessoaisActivity.this, "Idade inválida!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(TelaPerfilInformacoesPessoaisActivity.this, "Gênero inválido!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(TelaPerfilInformacoesPessoaisActivity.this, "Alguns campos estão inválidos ou não preenchidos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void irParaTelaPerfilInformacoesFisicas(){
        Intent intentParaTelaPerfilInformacoesFisicasActivity = new Intent(TelaPerfilInformacoesPessoaisActivity.this, TelaPerfilInformacoesFisicasActivity.class);
        intentParaTelaPerfilInformacoesFisicasActivity.putExtra("usuario", usuario);
//        intentParaTelaPerfilInformacoesFisicasActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        finish();
        startActivity(intentParaTelaPerfilInformacoesFisicasActivity);
    }
}
