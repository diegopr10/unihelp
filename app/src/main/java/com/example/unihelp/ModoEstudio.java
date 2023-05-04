package com.example.unihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import java.util.Locale;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



public class ModoEstudio extends AppCompatActivity {

    private EditText et_time_input;
    private TextView tv_time_input;
    private TextView tv_timer;
    private ProgressBar progress_bar;
    private Button btn_start;

    private boolean isCountDownTimerRunning = false;
    private CountDownTimer countDownTimer;
    int countProgress;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_estudio);

        prog();

    }
    public void prog(){
        et_time_input = findViewById(R.id.et_time_input);
        tv_time_input = findViewById(R.id.tv_time_input);
        progress_bar = findViewById(R.id.progress_bar);
        btn_start = findViewById(R.id.btn_start);
        tv_timer = findViewById(R.id.tv_timer);

        // Botón de inicio
        btn_start.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {


                if (!isCountDownTimerRunning) {
                    // Obtener el tiempo en minutos desde el campo de entrada de tiempo
                    int minutes = Integer.parseInt(et_time_input.getText().toString());

                    // Convertir los minutos a milisegundos
                    long millisInFuture = minutes * 60 * 1000;

                    // Crear un nuevo contador de tiempo
                    countDownTimer = new CountDownTimer(millisInFuture, 1000) {

                        public void onTick(long millisUntilFinished) {
                            // Esto es para actualizar el contador y barra de progreso
                            int seconds = (int) (millisUntilFinished / 1000) % 60;
                            int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                            tv_timer.setText(timeLeftFormatted);
                            countProgress = (int) (((millisInFuture-millisUntilFinished)*100)/millisInFuture);
                            progress_bar.setProgress(countProgress);
                        }


                        public void onFinish() {
                            // Mostrar un mensaje cuando el contador de tiempo finalice
                            tv_timer.setText("00:00");
                            Toast.makeText(ModoEstudio.this, "¡El tiempo ha terminado!", Toast.LENGTH_SHORT).show();
                            isCountDownTimerRunning = false;
                        }
                    };

                    // Iniciar el contador de tiempo
                    countDownTimer.start();
                    isCountDownTimerRunning = true;

                } else {
                    Toast.makeText(ModoEstudio.this, "Ya hay un temporizador en funcionamiento.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        //Metodo para asegurarse de que el temporizador se detenga correctamente si la actividad se
        // destruye antes de que finalice el temporizador

    protected void onDestroy() {
        super.onDestroy();

        // Detener el temporizador si está en funcionamiento
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
