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
    ArrayList<ImageView> images = new ArrayList<ImageView>();

    ArrayList<Integer> array_image = new ArrayList<Integer>();
    ArrayList<Integer> generated = new ArrayList<Integer>();
    ArrayList<Integer> toremove = new ArrayList<Integer>();


    Button button;
    Button button0;
    Button restartButton;
    TextView startText;
    GridLayout grid;

    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

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

    public void begin()
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
        Random rng = new Random();
        for (int i = 0; i < 6; i++) {
            int next = rng.nextInt(11);
            if (!generated.contains(next)) {
                generated.add(next);
                toremove.add(array_image.get(next));
                images.get(i).setImageResource(array_image.get(next));
            } else {
                i--;
            }
        }
        generated.removeAll(generated);
    }

    public void restart(View v)
    {
        grid.setVisibility(View.INVISIBLE);
        restartButton.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.VISIBLE);
        startText.setVisibility(View.VISIBLE);
    }
    public void show(View v)
    {
        button0.setVisibility(View.INVISIBLE);
        startText.setVisibility(View.INVISIBLE);
        begin();
        grid.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
    }

    public void start(View v)
    {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                array_image.removeAll(toremove);
                Random rng = new Random();
                for (int i = 0; i < 5; i++) {
                    int next = rng.nextInt(6);
                    if (!generated.contains(next)) {
                        generated.add(next);
                        images.get(i).setImageResource(array_image.get(next));
                    } else {
                        i--;
                    }
                }
                array_image.removeAll(array_image);
                generated.removeAll(generated);
                toremove.removeAll(toremove);
                restartButton.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "where's your card :p", Toast.LENGTH_LONG).show();
            }
        }, 3000);
        for (int i = 0; i < 6; i++)
        {
            images.get(i).setImageResource(R.drawable.background);
        }
        Toast.makeText(MainActivity.this, "Thinking.....", Toast.LENGTH_LONG).show();
        button.setVisibility(View.INVISIBLE);
    }

}

