package com.trabalhodispositivosmoveisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaPerfilNivelAtividadeActivity extends AppCompatActivity {

    private Button btnAvancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil_nivel_atividade);

        btnAvancar = (Button) findViewById(R.id.btnAvancar);
        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TelaPerfilNivelAtividadeActivity.this, TelaPerfilActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
