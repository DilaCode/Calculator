package com.dilanka.calculatorapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
private EditText display;
private String currentInput="";
private String operator ="";
private Double firstNumber=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      display = findViewById(R.id.display);
      setNumberButtonListeners();
      setOperatorButtonListeners();

    }

    private void setNumberButtonListeners(){
        int[] numberButtons = {
                R.id.button_0, R.id.button_1, R.id.button_2,
                R.id.button_3, R.id.button_4, R.id.button_5,
                R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9, R.id.button_dot
        };

        for(int id : numberButtons){
            findViewById(id).setOnClickListener(view -> {
                Button button = (Button) view;
                currentInput += button.getText().toString();
                display.setText(currentInput);
            });
        }
    }

    private void setOperatorButtonListeners(){
        findViewById(R.id.button_add).setOnClickListener(view -> setOperator("+"));
        findViewById(R.id.button_subtract).setOnClickListener(view -> setOperator("-"));
        findViewById(R.id.button_multiply).setOnClickListener(view -> setOperator("*"));
        findViewById(R.id.button_divide).setOnClickListener(view -> setOperator("/"));

        findViewById(R.id.button_equals).setOnClickListener(view -> calculateResult());
        findViewById(R.id.button_clear).setOnClickListener(view -> clearDisplay());
        findViewById(R.id.button_delete).setOnClickListener(view -> deleteLastChatacter());
    }

    private void setOperator(String op){
        if(firstNumber == null && !currentInput.isEmpty()){
            firstNumber= Double.parseDouble(currentInput);
            operator=op;
            currentInput="";
        }
    }

    private void calculateResult(){
        if(firstNumber != null & !currentInput.isEmpty()){
            double secondNumber =Double.parseDouble(currentInput);
            double result = 0.0;

            switch (operator){
                case"+":
                    result=firstNumber+secondNumber;
                    break;

                case "-":
                        result=firstNumber-secondNumber;
                        break;

                case "*":
                    result=firstNumber*secondNumber;
                    break;

                case "/":
                    if (secondNumber != 0) result= firstNumber/secondNumber;
                    else display.setText("Error");
                    break;
            }
            display.setText(String.valueOf(result));
            firstNumber=null;
            operator="";
            currentInput="";
        }
    }

    private void clearDisplay(){
        currentInput="";
        firstNumber=null;
        operator="";
        display.setText("");
    }

    private void deleteLastChatacter(){
        if(!currentInput.isEmpty()){
            currentInput = currentInput.substring(0, currentInput.length()-1);
            display.setText(currentInput);
        }
    }
}