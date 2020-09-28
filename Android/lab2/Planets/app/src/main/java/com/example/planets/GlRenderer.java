package com.example.planets;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class GlRenderer implements Renderer {

    float p1;
    float p2;
    float p3;
    private static final int AXIAL_TILT_DEGREES = 30;
    private static final float CLEAR_RED = 0.0f;
    private static final float CLEAR_GREEN = 0.0f;
    private static final float CLEAR_BLUE = 0.0f;
    private static final float CLEAR_ALPHA = 0.5f;
    private static final float OBJECT_DISTANCE = -10.0f;

    private final Sphere mEarth;
    private final Sphere mSun;
    private final Sphere mMoon;
    private final Context mContext;



    public GlRenderer(final Context context) {
        this.mContext = context;
        this.mSun = new Sphere(3, 2);
        this.mEarth = new Sphere(2, 1);
        this.mMoon = new Sphere(2, 1);

    }

    @Override
    public void onDrawFrame(final GL10 gl) {
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, OBJECT_DISTANCE);
        gl.glRotatef(AXIAL_TILT_DEGREES, 1, 0, 0);
        p1=(p1>360)?0:p1+0.5f;
        gl.glRotatef(p1, 0, 1, 0);
        this.mSun.draw(gl);

        p2=(p2>360)?0:p2+1f;
        gl.glRotatef(p2,0,1,0);
        gl.glTranslatef(4.5f, 0.0f, 0.0f);
        this.mEarth.draw(gl);

        p3=(p3>360)?0:p3+3f;
        gl.glRotatef(p3,0,2,1);
        gl.glScalef(0.5f,0.5f,0.5f);
        gl.glTranslatef(2.0f, -1.5f, 2.0f);
        this.mMoon.draw(gl);

    }

    @Override
    public void onSurfaceChanged(final GL10 gl, final int width, final int height) {

        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 120.0f, (float) width / height, 0.1f, 50.0f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

    }

    @Override
    public void onSurfaceCreated(final GL10 gl, final EGLConfig config) {

        this.mSun.loadGLTexture(gl, this.mContext, R.drawable.sun2);
        this.mEarth.loadGLTexture(gl, this.mContext, R.drawable.earth);
        this.mMoon.loadGLTexture(gl, this.mContext, R.drawable.moon);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glClearColor(CLEAR_RED, CLEAR_GREEN, CLEAR_BLUE, CLEAR_ALPHA);
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    }
}
