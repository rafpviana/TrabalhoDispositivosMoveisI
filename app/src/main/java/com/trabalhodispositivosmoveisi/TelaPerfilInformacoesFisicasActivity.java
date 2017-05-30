package com.trabalhodispositivosmoveisi;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.w3c.dom.Text;

public class TelaPerfilInformacoesFisicasActivity extends AppCompatActivity {

    private ImageView imageViewCadastroFotoPerfil;
    private TextView textViewCadastroNome, textViewCadastroGenero, textViewCadastroIdade;
    private EditText editTextCadastroPeso, editTextCadastroAltura;
    private Button buttonCadastroAvancar;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil_informacoes_fisicas);

        encontrarViews();

        Intent intentDaTelaPerfilInformacoesPessoais = getIntent();
        usuario = (Usuario) intentDaTelaPerfilInformacoesPessoais.getSerializableExtra("usuario");

        if(usuario != null){
//            Toast.makeText(this, "Id do usuário: "+usuario.getId(), Toast.LENGTH_SHORT).show();
            preencherCabecalho();
            configurarButtonCadastroAvancar();

        }
    }

    private void encontrarViews() {
        imageViewCadastroFotoPerfil = (ImageView) findViewById(R.id.imageview_cadastro_foto_perfil_2);
        textViewCadastroNome = (TextView) findViewById(R.id.textview_cadastro_nome_1);
        textViewCadastroGenero = (TextView) findViewById(R.id.textview_cadastro_genero_1);
        textViewCadastroIdade = (TextView) findViewById(R.id.textview_cadastro_idade_1);
        editTextCadastroPeso = (EditText) findViewById(R.id.edittext_cadastro_peso);
        editTextCadastroAltura = (EditText) findViewById(R.id.edittext_cadastro_altura);
        buttonCadastroAvancar = (Button) findViewById(R.id.button_cadastro_avancar_2);
    }

    private void preencherCabecalho(){
        textViewCadastroNome.setText(usuario.getNome());
        textViewCadastroGenero.setText(usuario.getGenero());
        textViewCadastroIdade.setText(Integer.toString(usuario.getIdade())+" anos");
    }

    private void configurarButtonCadastroAvancar() {
        buttonCadastroAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTextCadastroPeso.getText().toString() != null && !editTextCadastroPeso.getText().toString().isEmpty()
                        && Float.parseFloat(editTextCadastroPeso.getText().toString()) > 0
                        && editTextCadastroAltura.getText().toString() != null && !editTextCadastroAltura.getText().toString().isEmpty()
                        && Float.parseFloat(editTextCadastroAltura.getText().toString()) > 0){

                    usuario.setPeso(Float.parseFloat(editTextCadastroPeso.getText().toString()));
                    usuario.setAltura(Float.parseFloat(editTextCadastroAltura.getText().toString()));

                    irParaTelaPerfilNivelAtividade();

                }
                else{
                    Toast.makeText(TelaPerfilInformacoesFisicasActivity.this, "Alguns campos estão inválidos ou não preenchidos!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void irParaTelaPerfilNivelAtividade(){
        Intent intentParaTelaPerfilNivelAtividadeActivity = new Intent(TelaPerfilInformacoesFisicasActivity.this, TelaPerfilNivelAtividadeActivity.class);
        intentParaTelaPerfilNivelAtividadeActivity.putExtra("usuario", usuario);
        startActivity(intentParaTelaPerfilNivelAtividadeActivity);
    }

}
