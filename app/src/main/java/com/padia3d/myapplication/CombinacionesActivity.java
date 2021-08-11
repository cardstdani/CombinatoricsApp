package com.padia3d.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

public class CombinacionesActivity extends AppCompatActivity {

    EditText inputElementos, inputFormas;
    Button butonCalcular;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combinaciones);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputElementos = findViewById(R.id.elementosInput);
        inputFormas = findViewById(R.id.formasInput);
        butonCalcular = findViewById(R.id.butonCalcularSinRepeticion);
        output = findViewById(R.id.outputSinRepeticion);

        butonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigDecimal divisor = factorial(new BigDecimal(inputFormas.getText().toString())).multiply(factorial(new BigDecimal(inputElementos.getText().toString()).subtract(new BigDecimal(inputFormas.getText().toString()))));
                output.setText("Combinaciones: " + factorial(new BigDecimal(inputElementos.getText().toString())).divide(divisor));
            }
        });
    }

    public static BigDecimal factorial(BigDecimal n) {
        if (n.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ONE;
        } else {
            BigDecimal b = n;
            b = b.subtract(BigDecimal.ONE);
            BigDecimal resultado = n;

            for (BigDecimal i = n; i.compareTo(new BigDecimal("1")) > 0; i = i.subtract(BigDecimal.ONE)) {
                resultado = resultado.multiply(b);
                b = b.subtract(new BigDecimal("1"));
            }
            return resultado;
        }
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