package com.example.planets;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    private GLSurfaceView mGlSurfaceView;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.mGlSurfaceView = new GLSurfaceView(this);
        this.mGlSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.mGlSurfaceView.setRenderer(new GlRenderer(this));
        this.setContentView(this.mGlSurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mGlSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        this.mGlSurfaceView.onPause();
        super.onPause();
    }
}
