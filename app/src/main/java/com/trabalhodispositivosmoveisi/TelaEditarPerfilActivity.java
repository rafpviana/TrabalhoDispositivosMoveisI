package com.trabalhodispositivosmoveisi;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaEditarPerfilActivity extends AppCompatActivity {

    private Button buttonEditSalvar, buttonEditCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar_perfil);

        buttonEditCancelar = (Button) findViewById(R.id.buttonEditCancelar);
        buttonEditSalvar = (Button) findViewById(R.id.buttonEditSalvar);

        buttonEditCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonEditSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}