package edu.estudiantat.upc.AndroidCalculator;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator;
    private TextView display;
    private TextView expression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        calculator = new Calculator();
        display = findViewById(R.id.DisplayLabel);
        expression = findViewById(R.id.SmallDisplayLabel);
        show();

        numberButton(R.id.button0, "0");
        numberButton(R.id.button1, "1");
        numberButton(R.id.button2, "2");
        numberButton(R.id.button3, "3");
        numberButton(R.id.button4, "4");
        numberButton(R.id.button5, "5");
        numberButton(R.id.button6, "6");
        numberButton(R.id.button7, "7");
        numberButton(R.id.button8, "8");
        numberButton(R.id.button9, "9");

        operatorButton(R.id.buttonSuma, "+");
        operatorButton(R.id.buttonResta, "-");
        operatorButton(R.id.buttonMulti, "×");
        operatorButton(R.id.buttonDivi, "÷");

        trigButton(R.id.buttonSin, "Sin");
        trigButton(R.id.buttonCos, "Cos");
        trigButton(R.id.buttonTan, "Tan");

        findViewById(R.id.buttonDecimal).setOnClickListener(v -> {
            calculator.comma();
            show();
        });

        findViewById(R.id.buttonIgual).setOnClickListener(v -> {
            calculator.equal();
            show();
        });

        findViewById(R.id.buttonRestart).setOnClickListener(v -> {
            calculator.clear();
            show();
        });

        MaterialButtonToggleGroup toggle = findViewById(R.id.degRadToggle);
        toggle.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (!isChecked) {
                return;
            }
            calculator.setDegrees(checkedId == R.id.buttonDeg);
        });
    }

    private void numberButton(int id, String digit) {
        findViewById(id).setOnClickListener(v -> {
            calculator.number(digit);
            show();
        });
    }

    private void operatorButton(int id, String op) {
        findViewById(id).setOnClickListener(v -> {
            calculator.operator(op);
            show();
        });
    }

    private void trigButton(int id, String op) {
        findViewById(id).setOnClickListener(v -> {
            calculator.trig(op);
            show();
        });
    }

    private void show() {
        display.setText(calculator.getCurrent());
        expression.setText(calculator.getExpression());
    }
}