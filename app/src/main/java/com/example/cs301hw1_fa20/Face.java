// @author Kyle Sanchez
package com.example.cs301hw1_fa20;

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
        // creates random colors from within specific range
        int randSkin = ThreadLocalRandom.current().nextInt(0,255);
        int randEye = ThreadLocalRandom.current().nextInt(0,255);
        int randHairCol  = ThreadLocalRandom.current().nextInt(0,255);
        int randHairSty =  ThreadLocalRandom.current().nextInt(1,3);

        // setting instance variables to randomized values
        this.skinColor = randSkin;
        this.eyeColor = randEye;
        this.hairColor = randHairCol;
        this.hairStyle = randHairSty;
    }
}
