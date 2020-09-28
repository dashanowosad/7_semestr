package com.example.opengl_es;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {

    protected RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.relativeLayout = new RelativeLayout(this);

////////////////////
        Button ButCub = new Button(this);
        ButCub.setText("Нарисовать куб");
        ButCub.setId(View.generateViewId());

        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params1.addRule(RelativeLayout.CENTER_VERTICAL);
        params1.addRule(RelativeLayout.CENTER_HORIZONTAL);

        this.relativeLayout.addView(ButCub, params1);

        ButCub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CubeActivity.class);
                startActivity(intent);
            }
        });

///////////////////////////

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.ABOVE, ButCub.getId());
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);

        Button ButSq = new Button(this);
        ButSq.setText("Нарисовать квадрат");
        ButSq.setId(View.generateViewId());

        this.relativeLayout.addView(ButSq, params);

        ButSq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SquareActivity.class);
                startActivity(intent);
            }
        });

/////////////////////////

        Button ButSph = new Button(this);
        ButSph.setText("Нарисовать сферу");

        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params2.addRule(RelativeLayout.BELOW, ButCub.getId());
        params2.addRule(RelativeLayout.CENTER_HORIZONTAL);

        this.relativeLayout.addView(ButSph, params2);

        setContentView(this.relativeLayout);

        ButSph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SphereActivity.class);
                startActivity(intent);
            }
        });


    }
}