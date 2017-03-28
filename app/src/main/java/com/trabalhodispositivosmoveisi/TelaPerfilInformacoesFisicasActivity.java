package com.trabalhodispositivosmoveisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaPerfilInformacoesFisicasActivity extends AppCompatActivity {

    private Button b_id_avancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil_informacoes_fisicas);

        b_id_avancar = (Button) findViewById(R.id.b_id_avancar);

        b_id_avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(TelaPerfilInformacoesFisicasActivity.this, TelaPerfilNivelAtividadeActivity.class);
                startActivity(newIntent);
            }
        });
    }
}
