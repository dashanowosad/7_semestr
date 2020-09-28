package com.example.opengl_es;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class SphereRender implements GLSurfaceView.Renderer {

    private Sphere sphere;
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        sphere = new Sphere(0.5f);


    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClearColor(1,1,1,1);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LESS);
        sphere.draw(gl);
    }
}
