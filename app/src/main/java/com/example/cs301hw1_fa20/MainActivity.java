// @author Kyle Sanchez

package com.example.cs301hw1_fa20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    /**
     External Citation
     Date: 7 September 2020
     Problem: Didn't know how to use Radio Groups and Buttons
     Resource:
     https://developer.android.com/reference/android/widget/RadioGroup?hl=en
     Solution: I read the documentation to learn how to use radio groups/buttons
     */

    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        /**
         External Citation
         Date: 10 September 2020
         Problem: Didn't know how to use Spinners
         Resource:
         https://developer.android.com/guide/topics/ui/controls/spinner
         Solution: I read the documentation to learn how to use spinners
         */

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.hairStyles,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        radioGroup = findViewById(R.id.radioGroup);


    }
    /**
     External Citation
     Date: 8 September 2020
     Problem: Wanted to know how to use toasts
     Resource:
     https://developer.android.com/guide/topics/ui/notifiers/toasts
     Solution: I read the documentation to learn how to use toasts
     */

    public void checkedButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        // This toast  dipslays text when radio buttons are selected.
        Toast.makeText(this,"Selected Radio Button: " + radioButton.getText(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // do nothing for now
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // do nothing for now
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        // do nothing for now
    }
}