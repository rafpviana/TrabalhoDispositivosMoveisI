package com.trabalhodispositivosmoveisi;

import android.app.TimePickerDialog;
import android.content.Intent;
import java.util.Calendar;
import android.net.Uri;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

public class TelaMinhasAtividadesActivity extends AppCompatActivity {


    private ImageButton imageButtonAlarme, imageButtonAbdomen, imageButtonPerna, imageButtonPeitoral;
    private int nivelAtividadeSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_minhas_atividades);

        encontrarViews();

        Intent intentDaTelaPerfil = getIntent();
        nivelAtividadeSelecionado = intentDaTelaPerfil.getIntExtra("nivelAtividade", 0);

        if(nivelAtividadeSelecionado != 0){

            configurarImageButtonAlarm();
            configurarImageButtonAbdomen();
            configurarImageButtonPerna();
            configurarImageButtonPeitoral();

        }

    }

    private void encontrarViews(){
        imageButtonAlarme = (ImageButton) findViewById(R.id.imagebutton_alarme);
        imageButtonAbdomen = (ImageButton) findViewById(R.id.imagebutton_abdomen);
        imageButtonPerna = (ImageButton) findViewById(R.id.imagebutton_perna);
        imageButtonPeitoral = (ImageButton) findViewById(R.id.imagebutton_peitoral);
    }

    private void configurarImageButtonAlarm(){
        imageButtonAlarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(TelaMinhasAtividadesActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                                .putExtra(AlarmClock.EXTRA_MESSAGE, "Hora do treino!")
                                .putExtra(AlarmClock.EXTRA_HOUR, selectedHour)
                                .putExtra(AlarmClock.EXTRA_MINUTES, selectedMinute);
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }

                        Toast.makeText(TelaMinhasAtividadesActivity.this, "Alarme de treino criado com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Selecione a hora do próximo treino!");
                mTimePicker.show();
            }
        });
    }

    private void configurarImageButtonAbdomen(){
        imageButtonAbdomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nivelAtividadeSelecionado == 1){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=SJgUXzZ8wVw&index=1&list=PLBA52D75AAD54D6D5&t=366s")));
                    Log.i("Video", "Video Playing....");
                }
                else if(nivelAtividadeSelecionado == 2){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=CLPLyx_n4xk&index=4&list=PLBA52D75AAD54D6D5&t=226s")));
                    Log.i("Video", "Video Playing....");
                }
                else if(nivelAtividadeSelecionado == 3){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=RDvOQtqb3HU&index=5&list=PLBA52D75AAD54D6D5")));
                    Log.i("Video", "Video Playing....");
                }

            }
        });
    }

    private void configurarImageButtonPerna(){
        imageButtonPerna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nivelAtividadeSelecionado == 1){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=GPIWLfkOltw&index=3&list=PLTvKRR7y6Vf_BZIE9YU72seV0uzx1NWEA")));
                    Log.i("Video", "Video Playing....");
                }
                else if(nivelAtividadeSelecionado == 2){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=YIhU9M9IMUM&index=2&list=PLTvKRR7y6Vf_BZIE9YU72seV0uzx1NWEA")));
                    Log.i("Video", "Video Playing....");
                }
                else if(nivelAtividadeSelecionado == 3){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=rbLmfe0GYfY&index=1&list=PLTvKRR7y6Vf_BZIE9YU72seV0uzx1NWEA")));
                    Log.i("Video", "Video Playing....");
                }
            }
        });
    }

    private void configurarImageButtonPeitoral(){
        imageButtonPeitoral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nivelAtividadeSelecionado == 1){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=t3eEVQjEg7I&t=47s&list=PLTvKRR7y6Vf-OJ1vXVfoYrnWclFl5oUkI&index=2")));
                    Log.i("Video", "Video Playing....");
                }
                else if(nivelAtividadeSelecionado == 2){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=46CMygaDauA&list=PLTvKRR7y6Vf-OJ1vXVfoYrnWclFl5oUkI&index=1")));
                    Log.i("Video", "Video Playing....");
                }
                else if(nivelAtividadeSelecionado == 3){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=85ePQtPuPL0&t=282s&list=PLTvKRR7y6Vf-OJ1vXVfoYrnWclFl5oUkI&index=4")));
                    Log.i("Video", "Video Playing....");
                }
            }
        });
    }



}