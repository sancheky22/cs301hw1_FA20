// @author Kyle Sanchez
package com.example.cs301hw1_fa20;

import android.graphics.Color;

import java.util.concurrent.ThreadLocalRandom;

public class Face {
    // Instance variables for different color values
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;

    // Constructor
    // calls the randomize function
    public Face(){
        randomize();
    }

    /**
     External Citation
     Date: 10 September 2020
     Problem: Didn't know how to make random int in specific rnage
     Resource:
     https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
     Solution: Used line of example code from stack overflow
     */

    public void randomize(){
        // creates random rgb values (0-255) for skin,eye,hair
        int skinR = ThreadLocalRandom.current().nextInt(0,255);
        int skinG = ThreadLocalRandom.current().nextInt(0,255);
        int skinB = ThreadLocalRandom.current().nextInt(0,255);

        int eyeR = ThreadLocalRandom.current().nextInt(0,255);
        int eyeG = ThreadLocalRandom.current().nextInt(0,255);
        int eyeB = ThreadLocalRandom.current().nextInt(0,255);

        int hairR = ThreadLocalRandom.current().nextInt(0,255);
        int hairG = ThreadLocalRandom.current().nextInt(0,255);
        int hairB = ThreadLocalRandom.current().nextInt(0,255);

        // creates random colors from within specific range
        int randSkin = Color.argb(255,skinR,skinG,skinB);
        int randEye = Color.argb(255,eyeR,eyeG,eyeB);
        int randHairCol  = Color.argb(255,hairR,hairG,hairB);
        int randHairSty =  ThreadLocalRandom.current().nextInt(1,3);

        // setting instance variables to randomized values
        this.skinColor = randSkin;
        this.eyeColor = randEye;
        this.hairColor = randHairCol;
        this.hairStyle = randHairSty;
    }
}
