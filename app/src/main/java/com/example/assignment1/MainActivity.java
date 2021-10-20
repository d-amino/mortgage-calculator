package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner rateSpi, periodspi, frequencyspi;
    private EditText enterAmount;
    private TextView hasToPay;

    int year;
    int freq;
    double rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rateSpi = findViewById(R.id.rateSpi);
        periodspi = findViewById(R.id.periodSpi);
        frequencyspi = findViewById(R.id.frequencySpi);

        Button calculatePayment = findViewById(R.id.button_calculator);
        calculatePayment.setOnClickListener(this);

        rateSpi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedClass = parent.getItemAtPosition(position).toString();
                switch (selectedClass) {
                    case "Fixed rate: 3.74%":
                        rate = 0.0374;
                        break;
                    case "Fixed rate: 3.04%":
                        rate = 0.0304;
                        break;
                    case "Fixed rate: 1.55%":
                        rate = 0.0155;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                rate = 0.0370;
            }
        });
        periodspi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedClass = parent.getItemAtPosition(position).toString();

                switch (selectedClass) {
                    case "5 years":
                        year = 5;
                        break;

                    case "10 years":
                        year = 10;
                        break;

                    case "15 years":
                        year = 15;
                        break;

                    case "20 years":
                        year = 20;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                year = 5;
            }

        });
        frequencyspi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedClass = parent.getItemAtPosition(position).toString();

                switch (selectedClass) {
                    case "Monthly":
                        freq = 12;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                freq = 12;
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_calculator:

                int n = freq * year;
                double c = rate / 12;
                enterAmount = findViewById(R.id.enterAmount);
                int amount = Integer.parseInt(enterAmount.getText().toString());
                double totPay = amount * c *Math.pow(1 + c, n)/(Math.pow(1 + c, n) - 1);
                String totalPay = new DecimalFormat("#.0#").format(totPay);
                hasToPay = findViewById(R.id.total);
                hasToPay.setText("The Payment you need to make is: $" + (totalPay));
                break;
        }
    }
}