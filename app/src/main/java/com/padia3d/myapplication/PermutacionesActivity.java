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
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PermutacionesActivity extends AppCompatActivity {

    Button butonCalcular1, butonCalcular2;
    TextView output1, output2;
    EditText input1, inputElementosR, inputRepeticionesR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permutaciones);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        input1 = findViewById(R.id.elementosInput);
        output1 = findViewById(R.id.outputSinRepeticion);
        butonCalcular1 = findViewById(R.id.butonCalcularSinRepeticion);
        butonCalcular2 = findViewById(R.id.butonCalcularConRepeticion);
        output2 = findViewById(R.id.outputConRepeticion);
        inputElementosR = findViewById(R.id.elementosInputRepeticion);
        inputRepeticionesR = findViewById(R.id.repeticionesInput);

        butonCalcular1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output1.setText("Permutaciones: " + factorial(new BigDecimal(input1.getText().toString())));
            }
        });

        butonCalcular2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputRepeticiones = inputRepeticionesR.getText().toString();
                inputRepeticiones = inputRepeticiones.replace(" ", "");
                inputRepeticiones += ",";
                int lastIndex = 0;
                List<Integer> repeticiones = new ArrayList<Integer>();

                for (int i = 0; i < inputRepeticiones.length(); i++) {
                    if (inputRepeticiones.charAt(i) == ',' && i != inputRepeticiones.length()) {
                        repeticiones.add(Integer.valueOf(inputRepeticiones.substring(lastIndex, i)));
                        lastIndex = i + 1;
                    }
                }

                BigDecimal divisor = new BigDecimal("1");
                for (int a = 0; a < repeticiones.size(); a++) {
                    repeticiones.set(a, factorial(new BigDecimal(repeticiones.get(a).toString())).intValue());
                    divisor = divisor.multiply(new BigDecimal(repeticiones.get(a)));
                }


                output2.setText("Permutaciones: " + factorial(new BigDecimal(inputElementosR.getText().toString())).divide(divisor));
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