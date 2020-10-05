// @author Kyle Sanchez

package com.example.cs301hw1_fa20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        View.OnClickListener, SeekBar.OnSeekBarChangeListener {



    /**
     External Citation
     Date: 7 September 2020
     Problem: Didn't know how to use Radio Groups and Buttons
     Resource:
     https://developer.android.com/reference/android/widget/RadioGroup?hl=en
     Solution: I read the documentation to learn how to use radio groups/buttons
     */

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Face faceModel;

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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hairStyles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);




        radioGroup = findViewById(R.id.radioGroup);

        //Setting up the randomFaceButton and it's corresponding listener
        Button randomButton = findViewById(R.id.randomFaceButton);
        randomButton.setOnClickListener(this);

        this.faceModel = findViewById(R.id.Face);

    }
    /**
     External Citation
     Date: 8 September 2020
     Problem: Wanted to know how to use toasts
     Resource:
     https://developer.android.com/guide/topics/ui/notifiers/toasts
     Solution: I read the documentation to learn how to use toasts
     */

    // Listener for radio buttons

    public void checkedButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        // sets radio  button to the ID of the radio button in the SV
        radioButton = findViewById(radioId);
        // This toast  dipslays text when radio buttons are selected.
        Toast.makeText(this,"Selected Radio Button: " + radioButton.getText(),Toast.LENGTH_SHORT).show();

        /*
        if(radioButton.getText()=="Style1"){
            faceModel.hairStyle = 1;
            faceModel.invalidate();
        }else if(radioButton.getText()=="Style2"){
            faceModel.hairStyle = 2;
            faceModel.invalidate();
        }else if(radioButton.getText()=="Style3"){
            faceModel.hairStyle = 3;
            faceModel.invalidate();
        }*/


    }

    // Spinner listeners
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // do nothing for now
        String style = parent.getItemAtPosition(position).toString();
        Log.d("Style Check",style);


        /**
         External Citation
         Date: 3 October 2020
         Problem: Wanted to know how to use toasts for Spinners
         Resource:
         https://stackoverflow.com/questions/20151414/how-can-i-use-onitemselected-in-android
         Solution: I used the example provided
         */

        Toast.makeText(parent.getContext(),"Hair Style: "+ parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT).show();

        // if style selected is changes to the corresponding hairstyle and
        // sets current int hairStyle to its current hair style number (1-3)
        // it then tellls the SV the updated style and redraws it
        if(style.equals("Style1")){
            Log.d("Style1 was checked",style);
            faceModel.invalidate();
            faceModel.hairStyle = 1;
            faceModel.invalidate();
        }else if(style.equals("Style2")){
            faceModel.invalidate();
            faceModel.hairStyle = 2;
            faceModel.invalidate();
        }else if(style.equals("Style3")){
            faceModel.invalidate();
            faceModel.hairStyle = 3;
            faceModel.invalidate();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // do nothing for now
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        // do nothing for now
    }

    //Button listener (For random face)
    @Override
    public void onClick(View view){
        Log.d("ONCLICK","I've been clicked");
        faceModel.randomize();
        faceModel.invalidate();
    }

    // Seekbar Listeners
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


}