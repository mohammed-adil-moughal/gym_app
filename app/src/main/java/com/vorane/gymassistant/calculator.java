package com.vorane.gymassistant;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vorane.gymassistant.R;

public class calculator extends AppCompatActivity {
   public double H_eight;
    public double W_eight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        setuptoolbar();
        final TextView resulttext= (TextView) findViewById(R.id.Result);
        final Spinner weights= (Spinner) findViewById(R.id.spinnerweight1);
        final EditText weight= (EditText) findViewById(R.id.weight1);
        final EditText heightfeet=(EditText)findViewById(R.id.heightfeet);
        final EditText heightinches= (EditText) findViewById(R.id.heightinches);
        final EditText age= (EditText) findViewById(R.id.editTextage);
        final TextView result_advice= (TextView) findViewById(R.id.Resulttext);
        final TextView BMR_result_text= (TextView) findViewById(R.id.BMRResulttext);
        final TextView BMR_result= (TextView) findViewById(R.id.BMRResult);

         final RadioGroup radioGroup= (RadioGroup) findViewById(R.id.radio);
        RadioButton radioButton;

    final int heightval=0;


        Button click= (Button) findViewById(R.id.buttonclick);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (weight.getText().toString().length() == 0 || heightfeet.getText().toString().length() == 0 || heightinches.getText().toString().length() == 0 || age.getText().toString().length() == 0)
                { if(weight.getText().toString().length() == 0 )
                    weight.setError(" Weight is required!");
                    if( heightfeet.getText().toString().length() == 0 )
                       heightfeet.setError(" Height is required!");
                    if( heightinches.getText().toString().length() == 0)
                        heightinches.setError("Height in Inches");
                    if(  age.getText().toString().length() == 0)
                        age.setError("Age is mandatory");
                }
                else {
                    final int weightval = (int) weights.getSelectedItemPosition();
                    double new_weight = 0;
                    double new_height = 0;
                    double bmr_weight = 0;
                    switch (weightval) {
                        case 0://pounds
                            new_weight = Integer.parseInt(weight.getText().toString()) * 0.45;
                            bmr_weight = Integer.parseInt(weight.getText().toString()) / 2.2;
                            break;
                        case 1://Kilos
                            new_weight = Integer.parseInt(weight.getText().toString());
                            bmr_weight = Integer.parseInt(weight.getText().toString());
                            break;

                    }
                    double bmrheight = 0;
                    bmrheight = (((Integer.parseInt(heightfeet.getText().toString()) * 12) + Integer.parseInt(heightinches.getText().toString())) * 2.54);
                    new_height = ((Integer.parseInt(heightfeet.getText().toString()) * 12) + Integer.parseInt(heightinches.getText().toString())) * 0.025;
                    new_height = new_height * new_height;
                    double result = new_weight / new_height;
                    resulttext.setText(String.valueOf(result));
                    if (result < 19 || result > 25) {
                        resulttext.setText(String.valueOf(result));
                        resulttext.setTextColor(Color.parseColor("#FF0000"));

                        result_advice.setText("BMI Is Slighty Alarming");
                    } else {
                        resulttext.setText(String.valueOf(result));
                        resulttext.setTextColor(Color.parseColor("#00FF00"));
                        result_advice.setText(" BMI Is  Normal ");

                    }
                   // Toast.makeText(getApplicationContext(), String.valueOf(result), Toast.LENGTH_LONG).show();

                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton;
                    int age_val = Integer.parseInt(age.getText().toString());

                    radioButton = (RadioButton) findViewById(selectedId);

                    Toast.makeText(getApplicationContext(),
                            radioButton.getText(), Toast.LENGTH_SHORT).show();
                    double bmr = 0;

                    if (radioButton.getText().equals("male")) {
                        bmr = 66.47 + (13.75 * bmr_weight) + (5.0 * bmrheight) - (6.75 * age_val);
                    } else if (radioButton.getText().equals("female")) {
                        bmr = 665.09 + (9.56 * bmr_weight) + (1.84 * bmrheight) - (4.67 * age_val);
                    }


                    BMR_result_text.setText(String.valueOf(bmr));
                    BMR_result.setText("Basic Metablolism Rate Is");
                }
            }
        });



    }
    private void setuptoolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.sups_toolbar);
        toolbar.setTitle("Calculator");

        toolbar.setNavigationIcon(R.drawable.left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
