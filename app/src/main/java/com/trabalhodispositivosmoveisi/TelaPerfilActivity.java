package com.trabalhodispositivosmoveisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaPerfilActivity extends AppCompatActivity {

    private Button button_minhas_atividades, button_editar_perfil, button_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);

        button_minhas_atividades = (Button) findViewById(R.id.button_minhas_atividades);
        button_editar_perfil = (Button) findViewById(R.id.button_editar_perfil);
        button_logout = (Button) findViewById(R.id.button_logout);


        button_minhas_atividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(TelaPerfilActivity.this, TelaMinhasAtividadesActivity.class);
                startActivity(i1);
            }
        });

        button_editar_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(TelaPerfilActivity.this, TelaEditarPerfilActivity.class);
                startActivity(i2);
            }
        });

        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(TelaPerfilActivity.this, TelaLoginActivity.class);
                i3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(i3);
            }
        });
    }
}
