package com.example.opengl_es;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.opengl.GLSurfaceView;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SquareActivity extends AppCompatActivity {

    private GLSurfaceView gLView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        GLSurfaceView g = new GLSurfaceView(this );
        g.setRenderer(new SquareRenderer());
        g.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        setContentView(g);
    }


}
