package com.example.tenzahi_lsi3_mesure_glycemine;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private TextView tvAge,tvResult;
    private SeekBar sbAge;
    private RadioButton rbIsFasting,rbIsNotFasting;
    private EditText etValeur;
    private Button btnConsulter;

    private void init(){
        tvAge=(TextView) findViewById(R.id.tvAge);
        tvResult=(TextView) findViewById(R.id.tvResult);
        sbAge=(SeekBar) findViewById(R.id.sbAge);
        rbIsFasting=(RadioButton) findViewById(R.id.rbIsFasting);
        rbIsNotFasting=(RadioButton) findViewById(R.id.rbIsNotFasting);
        etValeur=(EditText) findViewById(R.id.etValeur);
        btnConsulter=(Button) findViewById(R.id.btnConsulter);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //Action de changement sur seekbar
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("information","onProgressChange"+i);
                tvAge.setText("votre age :"+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnConsulter.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                calculer(v);
            }

            private void calculer(View v)
            {
                int age ;
                float valeur;
                boolean verifAge = false , verifValeur = false ;
                if(sbAge.getProgress()!=0)
                    verifAge= true ;
                else
                    Toast.makeText(MainActivity.this,"Veuillez verifier votre age",Toast.LENGTH_SHORT).show();
                if(!etValeur.getText().toString().isEmpty())
                    verifValeur=true;
                else
                    Toast.makeText(MainActivity.this, "Veuillez verifier votre valeur mesure", Toast.LENGTH_LONG).show();
                if(verifAge && verifValeur)
                {
                    String contenuTexte = etValeur.getText().toString();
                    double niveauGlycemie = Double.parseDouble(contenuTexte);

                    int contenuAge = sbAge.getProgress();

                    boolean estAJean = rbIsFasting.isChecked();

                    String message;

                    if (estAJean) {
                        if (contenuAge >= 13) {
                            if (niveauGlycemie < 5.0)
                                message = "Le niveau de glycémie est bas avant le repas ";
                            else if (niveauGlycemie >= 5.0 && niveauGlycemie <= 7.2)
                                message = "Le niveau de glycémie est nomal avant le repas ";
                            else
                                message = "Le niveau de glycémie est élevé avant le repas";
                        } else if (contenuAge >= 6 && contenuAge <= 12)
                            if (niveauGlycemie < 5.0)
                                message = "Le niveau de glycémie est bas avant le repas";
                            else if (niveauGlycemie >= 5.0 && niveauGlycemie <= 10.0)
                                message = "Le niveau de glycémie est normal avant le repas";
                            else
                                message = "Le niveau de glycémie est élevé avant le repas";
                        else if (niveauGlycemie < 5.5)
                            message = "Le niveau de glycémie est bas avant le repas";
                        else if (niveauGlycemie >= 5.0 && niveauGlycemie <= 10.0)
                            message = "Le niveau de glycémie est normal avant le repas";
                        else
                            message = "Le niveau de glycémie est élevé avant le repas";
                    }
                    else
                    if(niveauGlycemie<10.5)
                        message="Le niveau de glycémie est normal apres le repas";
                    else
                        message="Le niveau de glycémie est élevé apres le repas";
                    tvResult.setText(message);
                }
            }
        });


    }
}