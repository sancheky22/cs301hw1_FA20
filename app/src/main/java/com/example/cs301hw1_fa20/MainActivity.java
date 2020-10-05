/**
 *  @author Kyle Sanchez
 *  Version 10/4/2020
 */
package com.example.cs301hw1_fa20;

import androidx.appcompat.app.AppCompatActivity;


import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
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

    // instance variables
    private Face faceModel; // need instance of Face to access things in SV
    private SeekBar redSB;
    private SeekBar greenSB;
    private SeekBar blueSB;
    private RadioButton hairRadioButton;
    private RadioButton eyesRadioButton;
    private RadioButton skinRadioButton;
    // These ints are used to hold the value that each slider has
    private int redSlider = 0;
    private int greenSlider = 0;
    private int blueSlider = 0;

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

        // Setting spinner adapters and listeners
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hairStyles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // Seekbar listeners
        // init red seek bar and set MainActivity as listener
        this.redSB = findViewById(R.id.redSeekBar);
        redSB.setOnSeekBarChangeListener(this);
        // init green seek bar and set MainActivity as listener
        this.greenSB = findViewById(R.id.greenSeekBar);
        greenSB.setOnSeekBarChangeListener(this);
        // init blue seek bar and set MainActivity as listener
        this.blueSB = findViewById(R.id.blueSeekBar);
        blueSB.setOnSeekBarChangeListener(this);


        // setting up radio group for radio buttons
        radioGroup = findViewById(R.id.radioGroup);
        // telling Main Activity which radio button I am referring to and setting a listener on it
        this.hairRadioButton = findViewById(R.id.radio_Hair);
        this.hairRadioButton.setOnClickListener(this);

        this.eyesRadioButton = findViewById(R.id.radio_Eyes);
        this.eyesRadioButton.setOnClickListener(this);

        this.skinRadioButton = findViewById(R.id.radio_Skin);
        this.skinRadioButton.setOnClickListener(this);


        //Setting up the randomFaceButton and it's corresponding listener
        Button randomButton = findViewById(R.id.randomFaceButton);
        randomButton.setOnClickListener(this);


        // making instance of SV (which I named face)
        this.faceModel = findViewById(R.id.Face); // This will be used throughout the Main Activity

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
        Toast.makeText(this,"Selected Radio Button: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }

    // Spinner listeners
    // When different items in the spinner are selected (Hair styles) the SV is updated to reflect what
    // hair style was clicked
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // This string is used to check if the string of the Style matches what is clicked on
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

        // Toast that displays what hair style is selected
        Toast.makeText(parent.getContext(),"Hair Style: "+ parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT).show();

        // if style selected is changes to the corresponding hairstyle and
        // sets current int hairStyle to its current hair style number (1-3)
        // it then tellls the SV the updated style and redraws it
        if(style.equals("Style1")){
            Log.d("Style1 was checked",style);
            faceModel.invalidate();
            faceModel.setHairStyle(1);
            faceModel.invalidate();
        }else if(style.equals("Style2")){
            faceModel.invalidate();
            faceModel.setHairStyle(2);
            faceModel.invalidate();
        }else if(style.equals("Style3")){
            faceModel.invalidate();
            faceModel.setHairStyle(3);
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
        // ints for different red,green, blue values
        // this is used to update the Seekbars when the different radio buttons
        // are selected
        int r,g,b;
        // if the randomFaceButton is clicked then randomize
        if(view.getId()==R.id.randomFaceButton) {
            Log.d("ONCLICK","I've been clicked");
            faceModel.randomize();
            faceModel.invalidate();
        }else if(view.getId()==R.id.radio_Hair){ // if the radioHair Button is selected then update the progress with the corresponding r,g,b values
            r= Color.red(faceModel.getHairColor());
            g= Color.green(faceModel.getHairColor());
            b= Color.blue(faceModel.getHairColor());
            // sets the progress of each seek bar to its corresponding int value
            this.redSB.setProgress(r);
            this.greenSB.setProgress(g);
            this.blueSB.setProgress(b);

        }else if(view.getId()==R.id.radio_Eyes){ // if the radioEyes Button is selected then update the progress with the corresponding r,g,b values
            r= Color.red(faceModel.getEyeColor());
            g= Color.green(faceModel.getEyeColor());
            b= Color.blue(faceModel.getEyeColor());
            // sets the progress of each seek bar to its corresponding int value
            this.redSB.setProgress(r);
            this.greenSB.setProgress(g);
            this.blueSB.setProgress(b);

        }else if(view.getId()==R.id.radio_Skin){ // if the radioSkin Button is selected then update the progress with the corresponding r,g,b values
            r= Color.red(faceModel.getSkinColor());
            g= Color.green(faceModel.getSkinColor());
            b= Color.blue(faceModel.getSkinColor());
            // sets the progress of each seek bar to its corresponding int value
            this.redSB.setProgress(r);
            this.greenSB.setProgress(g);
            this.blueSB.setProgress(b);
        }

    }

    // Seekbar Listeners
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // first checks if the seekBar change is coming from user, if yes proceeds
        if(fromUser) {
            // if the Seek bar is moved it makes the Slider int equal to the progress
            // this progress is the value of what the seekBar is
            if (seekBar.getId() == R.id.redSeekBar) {
                redSlider = progress;
            } else if (seekBar.getId() == R.id.greenSeekBar) {
                greenSlider = progress;
            } else if (seekBar.getId() == R.id.blueSeekBar) {
                blueSlider = progress;
            }

            // this new int is the color of what the slider values are
            // could have used Color.rgb
            // but wanted to emphasize that the opacity is opaque
            int color = Color.argb(255,redSlider,greenSlider,blueSlider);

            // depending on what radio button is checked the corresponding skin,eye, or hair color is
            // updated with the value of the slider
            if (this.hairRadioButton.isChecked()) {
                faceModel.setHairColor(color);
            } else if (this.skinRadioButton.isChecked()) {
                faceModel.setSkinColor(color);
            } else if (this.eyesRadioButton.isChecked()) {
                faceModel.setEyeColor(color);
            }
        }
        // tells SV to reDraw itself after changes are made with the Seek Bars
        faceModel.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // do nothing
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // do nothing
    }


}