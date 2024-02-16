package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = (TextView) findViewById(R.id.screen);

        Button btnEqual = (Button)findViewById(R.id.btnEqual);
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
            }
        });
    }

    private TextView screen;
    private int op1=0;
    private int op2=0;
    private Ops operator=null;
    private boolean isOp1=true;

    public enum Ops {
        PLUS,
        MINUS,
        MULTIPLICATE,
        DIVIDE;
    }

    public void compute() {
        if(!isOp1){
            switch(operator) {
                case PLUS : op1 = op1 + op2; break;
                case MINUS : op1 = op1 - op2; break;
                case MULTIPLICATE : op1 = op1 * op2; break;
                case DIVIDE : op1 = op1 / op2; break;
                default : return; // do nothing if no operator
            }
            op2 = 0;
            isOp1 = true;
            updateDisplay();
        }
    }

    private void updateDisplay() {
        int v = isOp1 ? op1 : op2;
        screen.setText(String.format(Locale.getDefault(), "%9d", v));
    }


    public void setOperator(View v) {
        int viewId = v.getId();

        if (viewId == R.id.btnPlus) {
            operator = Ops.PLUS;
        } else if (viewId == R.id.btnMinus) {
            operator = Ops.MINUS;
        } else if (viewId == R.id.btnMultiplicate) {
            operator = Ops.MULTIPLICATE;
        } else if (viewId == R.id.btnDivide) {
            operator = Ops.DIVIDE;
        } else {
            Toast.makeText(this, "Opérateur non reconnu", Toast.LENGTH_LONG).show();
            return;
        }

        isOp1 = false;
        updateDisplay();
    }

    public void addNumber(View v){
        try {
            int val = Integer.parseInt(((Button)v).getText().toString());
            if (isOp1) {
                op1 = op1 * 10 + val;
                updateDisplay();
            } else {
                op2 = op2 * 10 + val;
                updateDisplay();
            }
        }catch (NumberFormatException | ClassCastException e) {
            Toast.makeText(this, "Valeur erronée",Toast.LENGTH_LONG);
        }
    }

    public void resetCalculator(View v) {
        op1 = 0;
        op2 = 0;
        operator = null;
        isOp1 = true;
        updateDisplay(); // Met à jour l'affichage pour montrer la réinitialisation, par ex. à "0"
    }




}