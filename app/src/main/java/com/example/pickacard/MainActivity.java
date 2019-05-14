package com.example.pickacard;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<ImageView> images = new ArrayList<ImageView>();//list of the images places in gridview

    ArrayList<Integer> array_image = new ArrayList<Integer>(); //list contains all images of cards
    ArrayList<Integer> generated = new ArrayList<Integer>();  //list to contain generated random numbers
    ArrayList<Integer> toremove = new ArrayList<Integer>();  // list to the first shown images to remove next click


    Button button;   //i picked a card
    Button button0;  //  // ok button
    Button restartButton;  //restart button
    TextView startText;
    GridLayout grid;

    final Handler handler = new Handler();    //handler to delay

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*to hide title bar and status bar*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //add places to array images
        images.add((ImageView) findViewById(R.id.d1));
        images.add((ImageView) findViewById(R.id.d2));
        images.add((ImageView) findViewById(R.id.d3));
        images.add((ImageView) findViewById(R.id.d4));
        images.add((ImageView) findViewById(R.id.d5));
        images.add((ImageView) findViewById(R.id.d6));

        button0 =(Button) findViewById(R.id.b0);
        button  = (Button) findViewById(R.id.b1);
        restartButton = (Button) findViewById(R.id.b2);
        startText = (TextView) findViewById(R.id.t1);
        grid = (GridLayout)findViewById(R.id.g1);
    }

    public void begin()/// add all images to array_image and generate random from it.
    {
        array_image.add(R.drawable.k1);
        array_image.add(R.drawable.k2);
        array_image.add(R.drawable.k3);
        array_image.add(R.drawable.k4);
        array_image.add(R.drawable.j1);
        array_image.add(R.drawable.j2);
        array_image.add(R.drawable.j3);
        array_image.add(R.drawable.j4);
        array_image.add(R.drawable.q1);
        array_image.add(R.drawable.q2);
        array_image.add(R.drawable.q3);
        array_image.add(R.drawable.q4);


        Random rng = new Random(); //new random
        for (int i = 0; i < 6; i++) {
            int next = rng.nextInt(11);//random int from 0 to 11 (number of cards)
            if (!generated.contains(next)) {///check if this num hadn't generated before
                generated.add(next);  //add the rand num to generated list
                toremove.add(array_image.get(next));   //add ( image of index (rand num) of array_image  ) to toremove
                images.get(i).setImageResource(array_image.get(next));  /*set gird element of i to image of
                 index (rand num) of array_image*/
            } else {///if the number has generated before repeate with the same i
                i--;
            }
        }
        generated.removeAll(generated);  //remove all elements of generated to reuse again
    }

    public void restart(View v)  //restart button
    {
        /*
        *hide grid
         * hide restartbutton
          * show starttext
          * show ok button
          */
        grid.setVisibility(View.INVISIBLE);
        restartButton.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.VISIBLE);
        startText.setVisibility(View.VISIBLE);
    }
    public void show(View v) //ok button
    {
        /*
        * hide starttext
         * hide ok button
         * call begin and show grid and picked a card button*/
        button0.setVisibility(View.INVISIBLE);
        startText.setVisibility(View.INVISIBLE);
        begin();
        grid.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
    }

    public void start(View v)// i peaked a card button
    {
        // reset of the code will be excuted and this handler will too but after its time
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                array_image.removeAll(toremove);//remove all the shown images before from array_image
                Random rng = new Random();
                for (int i = 0; i < 5; i++) {
                    int next = rng.nextInt(6);///the new size of array_image is 6
                    if (!generated.contains(next)) {/////check if this num hadn't generated before
                        generated.add(next);//add the rand num to generated list
                        images.get(i).setImageResource(array_image.get(next)); /*set gird element of i to image of
                 index (rand num) of array_image*/
                    } else {
                        i--;
                    }
                }
                //clear all elements of temp arrays to restart again
                array_image.removeAll(array_image);
                generated.removeAll(generated);
                toremove.removeAll(toremove);

                restartButton.setVisibility(View.VISIBLE); //show restartbutton
                Toast.makeText(MainActivity.this, "where's your card :p", Toast.LENGTH_LONG).show();
            }
        }, 3000);///will be executed after 3 secs

        //this will be excuted first
        for (int i = 0; i < 6; i++)
        {
            //to make all cards fold
            images.get(i).setImageResource(R.drawable.background);
        }
        Toast.makeText(MainActivity.this, "Thinking.....", Toast.LENGTH_LONG).show();
        button.setVisibility(View.INVISIBLE);//hide i peaked a card button
    }

}

