package com.padia3d.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    Button calcularVariaciones0Buton, calcularVariaciones1Buton;
    EditText inputElementos, inputFormas, inputElementos1, inputFormas1;
    TextView outputVariaciones0, outputVariaciones1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        calcularVariaciones0Buton = findViewById(R.id.butonCalcularSinRepeticion);
        inputElementos = findViewById(R.id.elementosInput);
        inputFormas = findViewById(R.id.formasInput);
        outputVariaciones0 = findViewById(R.id.outputSinRepeticion);

        calcularVariaciones1Buton = findViewById(R.id.butonCalcularConRepeticion);
        inputElementos1 = findViewById(R.id.elementosInputRepeticion);
        inputFormas1 = findViewById(R.id.formasInputRepeticion);
        outputVariaciones1 = findViewById(R.id.outputConRepeticion);

        calcularVariaciones0Buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigInteger elementos = new BigInteger(inputElementos.getText().toString());
                BigInteger resultado = elementos;

                for (BigInteger i = BigInteger.ONE; i.compareTo(new BigInteger(inputFormas.getText().toString())) < 0; i = i.add(BigInteger.ONE)) {
                    elementos = elementos.subtract(BigInteger.ONE);
                    resultado = resultado.multiply(elementos);
                }

                outputVariaciones0.setText("Variaciones: " + resultado);
            }
        });

        calcularVariaciones1Buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outputVariaciones1.setText("Variaciones: " + new BigInteger(inputElementos1.getText().toString()).pow(Integer.parseInt(inputFormas1.getText().toString())));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.points_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemV) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        }

        if (item.getItemId() == R.id.itemP) {
            Intent i = new Intent(getApplicationContext(), PermutacionesActivity.class);
            startActivity(i);
            finish();
        }

        if (item.getItemId() == R.id.itemC) {
            Intent i = new Intent(getApplicationContext(), CombinacionesActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}