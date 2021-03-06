package com.example.opengl_es;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class CubeRender implements GLSurfaceView.Renderer {

    private float[] colors = {  // Colors of the 6 faces
            0.583f,  0.771f,  0.014f,
            0.609f,  0.115f,  0.436f,
            0.327f,  0.483f,  0.844f,
            0.822f,  0.569f,  0.201f,
            0.435f,  0.602f,  0.223f,
            0.310f,  0.747f,  0.185f,
            0.597f,  0.770f,  0.761f,
            0.559f,  0.436f,  0.730f,
            0.359f,  0.583f,  0.152f,
            0.483f,  0.596f,  0.789f,
            0.559f,  0.861f,  0.639f,
            0.195f,  0.548f,  0.859f,
            0.014f,  0.184f,  0.576f,
            0.771f,  0.328f,  0.970f,
            0.406f,  0.615f,  0.116f,
            0.676f,  0.977f,  0.133f,
            0.971f,  0.572f,  0.833f,
            0.140f,  0.616f,  0.489f,
            0.997f,  0.513f,  0.064f,
            0.945f,  0.719f,  0.592f,
            0.543f,  0.021f,  0.978f,
            0.279f,  0.317f,  0.505f,
            0.167f,  0.620f,  0.077f,
            0.347f,  0.857f,  0.137f,
            0.055f,  0.953f,  0.042f,
            0.714f,  0.505f,  0.345f,
            0.783f,  0.290f,  0.734f,
            0.722f,  0.645f,  0.174f,
            0.302f,  0.455f,  0.848f,
            0.225f,  0.587f,  0.040f,
            0.517f,  0.713f,  0.338f,
            0.053f,  0.959f,  0.120f,
            0.393f,  0.621f,  0.362f,
            0.673f,  0.211f,  0.457f,
            0.820f,  0.883f,  0.371f,
            0.982f,  0.099f,  0.879f

    };

    /*private float a[]= {
            1.0f, 1.0f,-1.0f,
            -1.0f, 1.0f,-1.0f,
            -1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,

            1.0f,-1.0f, 1.0f,
            -1.0f,-1.0f, 1.0f,
            -1.0f,-1.0f,-1.0f,
            1.0f,-1.0f,-1.0f,

            1.0f, 1.0f, 1.0f,
            -1.0f, 1.0f, 1.0f,
            -1.0f,-1.0f, 1.0f,
            1.0f,-1.0f, 1.0f,

            1.0f,-1.0f,-1.0f,
            -1.0f,-1.0f,-1.0f,
            -1.0f, 1.0f,-1.0f,
            1.0f, 1.0f,-1.0f,

            -1.0f, 1.0f, 1.0f,
            -1.0f, 1.0f,-1.0f,
            -1.0f,-1.0f,-1.0f,
            -1.0f,-1.0f, 1.0f,

            1.0f, 1.0f,-1.0f,
            1.0f, 1.0f, 1.0f,
            1.0f,-1.0f, 1.0f,
            1.0f,-1.0f,-1.0f
    };*/

    float []a = new float[]{
            -1,0.5f,1,
            -1,-0.5f,1,
            1,-0.5f,1,
            1,0.5f,1
    };

    float []a1 = new float[]{
            -1,0.5f,-1,
            -1,-0.5f,-1,
            1,-0.5f,-1,
            1,0.5f,-1
    };

    float []a2 = new float[]{
            1, 0.5f, -1,
            -1, 0.5f, -1,
            -1, 0.5f, 1,
            1, 0.5f, 1
    };
    float []a3 = new float[]{
            1, -0.5f, -1,
            -1, -0.5f, -1,
            -1, -0.5f, 1,
            1, -0.5f, 1
    };

    float []a4 = new float[]{
            -1, 0.5f, -1,
            -1, 0.5f, 1,
            -1, -0.5f, 1,
            -1, -0.5f, -1
    };

    float []a5 = new float[]{
            1, 0.5f, -1,
            1, 0.5f, 1,
            1, -0.5f, 1,
            1, -0.5f, -1
    };

    float []a6 = new float[]{
            -1,0.5f,1,
            -1,-0.5f,1,
            1,-0.5f,1,
            1,0.5f,1,

            -1,0.5f,-1,
            -1,-0.5f,-1,
            1,-0.5f,-1,
            1,0.5f,-1,

            1, 0.5f, -1,
            -1, 0.5f, -1,
            -1, 0.5f, 1,
            1, 0.5f, 1,

            1, -0.5f, -1,
            -1, -0.5f, -1,
            -1, -0.5f, 1,
            1, -0.5f, 1,

            -1, 0.5f, -1,
            -1, 0.5f, 1,
            -1, -0.5f, 1,
            -1, -0.5f, -1,

            1, 0.5f, -1,
            1, 0.5f, 1,
            1, -0.5f, 1,
            1, -0.5f, -1


    };
    FloatBuffer f1, f2, f3, f4, f5, f6, f7, col;
    ByteBuffer b;

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        /*b = ByteBuffer.allocateDirect(a.length *4);
        b.order(ByteOrder.nativeOrder());
        f1 = b.asFloatBuffer();
        f1.put(a);
        f1.position(0);

        b = ByteBuffer.allocateDirect(a.length *4);
        b.order(ByteOrder.nativeOrder());
        f2 = b.asFloatBuffer();
        f2.put(a1);
        f2.position(0);

        b = ByteBuffer.allocateDirect(a.length *4);
        b.order(ByteOrder.nativeOrder());
        f3 = b.asFloatBuffer();
        f3.put(a2);
        f3.position(0);

        b = ByteBuffer.allocateDirect(a.length *4);
        b.order(ByteOrder.nativeOrder());
        f4 = b.asFloatBuffer();
        f4.put(a3);
        f4.position(0);

        b = ByteBuffer.allocateDirect(a.length *4);
        b.order(ByteOrder.nativeOrder());
        f5 = b.asFloatBuffer();
        f5.put(a4);
        f5.position(0);

        b = ByteBuffer.allocateDirect(a.length *4);
        b.order(ByteOrder.nativeOrder());
        f6 = b.asFloatBuffer();
        f6.put(a5);
        f6.position(0);
*/
        b = ByteBuffer.allocateDirect(a6.length *4);
        b.order(ByteOrder.nativeOrder());
        f7 = b.asFloatBuffer();
        f7.put(a6);
        f7.position(0);

        b = ByteBuffer.allocateDirect(colors.length *4);
        b.order(ByteOrder.nativeOrder());

        col = b.asFloatBuffer();
        col.put(colors);
        col.position(0);
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

        /*gl.glLoadIdentity();
        gl.glRotatef(-45, 1,1,1);
        gl.glScalef(0.5f,0.5f,0.25f);
        gl.glColor4f(1,0,1,1);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,f1);
        //gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        //gl.glColorPointer(4,GL10.GL_FLOAT,0,col);
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,0,4);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        //gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

        gl.glColor4f(1,0,0,1);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,f2);
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,0,4);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glColor4f(1,1,0,1);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,f3);
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,0,4);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glColor4f(0,1,0,1);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,f4);
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,0,4);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glColor4f(0,1,1,1);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,f5);
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,0,4);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glColor4f(0,0,1,1);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,f6);
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,0,4);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);*/

        gl.glLoadIdentity();
        gl.glRotatef(-45, 1,1,1);
        gl.glScalef(0.5f,0.5f,0.25f);
        //gl.glColor4f(1,0,1,1);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,f7);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glColorPointer(4,GL10.GL_FLOAT,0,col);
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,0,24);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}
