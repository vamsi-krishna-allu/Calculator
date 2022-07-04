package com.lab.calculator;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private String operator = "";
    private String result = "";
    private String firstValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textViewInput);
        textView.setText("");
        textView.setMovementMethod(new ScrollingMovementMethod());
        // setting the full screen flag to hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setUpButtonListeners();
    }

    private void setUpButtonListeners() {
        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonDecimal = findViewById(R.id.buttonDecimal);

        button0.setOnClickListener(view -> numberButtonListener("0"));
        button1.setOnClickListener(view -> numberButtonListener("1"));
        button2.setOnClickListener(view -> numberButtonListener("2"));
        button3.setOnClickListener(view -> numberButtonListener("3"));
        button4.setOnClickListener(view -> numberButtonListener("4"));
        button5.setOnClickListener(view -> numberButtonListener("5"));
        button6.setOnClickListener(view -> numberButtonListener("6"));
        button7.setOnClickListener(view -> numberButtonListener("7"));
        button8.setOnClickListener(view -> numberButtonListener("8"));
        button9.setOnClickListener(view -> numberButtonListener("9"));
        buttonDecimal.setOnClickListener(this::decimalButtonListener);

        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonDivide = findViewById(R.id.buttonDivide);

        buttonPlus.setOnClickListener(view -> operatorListeners("+"));
        buttonMinus.setOnClickListener(view -> operatorListeners("-"));
        buttonMultiply.setOnClickListener(view -> operatorListeners("*"));
        buttonDivide.setOnClickListener(view -> operatorListeners("/"));

        Button buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(this::clearTextView);

        Button buttonEquals = findViewById(R.id.buttonEqual);
        buttonEquals.setOnClickListener(this::showResult);
    }

    private void decimalButtonListener(View view) {
        if(!textView.getText().toString().contains(".")){
            textView.setText(textView.getText().toString().concat("."));
        }
    }

    private void operatorListeners(String operator) {
        if(!isEmpty(textView.getText())){
            if(!isEmpty(firstValue)){
                firstValue = calculateResult();
            }else{
                firstValue = textView.getText().toString();
            }
            textView.setText("");
        }
        this.operator = operator;
    }

    private String calculateResult() {
        float leftValue = Float.parseFloat(firstValue);
        float rightValue = Float.parseFloat(textView.getText().toString());
        String value = "";
        switch (operator){
            case "+":
                value = resolveValue(leftValue + rightValue);
                break;
            case "-":
                value = resolveValue(leftValue - rightValue);
                break;
            case "*":
                value = resolveValue(leftValue * rightValue);
                break;
            case "/":
                value = resolveValue(leftValue / rightValue);
                break;
            default:
                break;
        }
        return value;
    }

    private String resolveValue(float value) {
        if(value - (int) value == 0){
            return String.valueOf((int) value);
        }
        return String.valueOf(value);
    }

    private void showResult(View view) {
        if(!isEmpty(firstValue)){
            result = calculateResult();
        }
        textView.setText(result);
        firstValue = "";
    }

    private void numberButtonListener(String value){
        textView.setText(textView.getText().toString().concat(value));
    }

    private void clearTextView(View view) {
        textView.setText("");
        firstValue = "";
        result = "";
    }
}