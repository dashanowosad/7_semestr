package com.example.opengl_es;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SphereActivity  extends AppCompatActivity {
    private GLSurfaceView gLView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GLSurfaceView g = new GLSurfaceView(this );
        g.setRenderer(new SphereRender());
        g.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        setContentView(g);
    }
}
