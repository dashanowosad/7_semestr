package com.example.opengl_es;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Sphere {
    private FloatBuffer mVertexBuffer;

    int n = 0;

    Sphere (Float R) {
        Float i = 0f;
        Integer dtheta = 15;
        Integer dphi = 15;

        Integer theta;
        Integer phi;
        Float DTOR = (float)(Math.PI / 180.0f);

        ByteBuffer byteBuf = ByteBuffer.allocateDirect(5000 * 3 * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        mVertexBuffer = byteBuf.asFloatBuffer();
        byteBuf = ByteBuffer.allocateDirect(5000 * 2 * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        theta = -90;
        while (theta <= 90 - dtheta) {
            phi = 0;
            while (phi <= 360 - dphi) {

                mVertexBuffer.put((float) ((Math.cos((theta * DTOR)) * Math.cos((phi * DTOR))) * R));
                mVertexBuffer.put((float) ((Math.cos((theta * DTOR)) * Math.sin((phi * DTOR))) * R /1.5));
                mVertexBuffer.put((float) (Math.sin((theta * DTOR)) * R));

                mVertexBuffer.put((float) ((Math.cos(((theta + dtheta) * DTOR)) * Math.cos((phi * DTOR))) * R));
                mVertexBuffer.put((float) ((Math.cos(((theta + dtheta) * DTOR)) * Math.sin((phi * DTOR))) * R/1.5));
                mVertexBuffer.put((float) (Math.sin(((theta + dtheta) * DTOR)) * R));

                mVertexBuffer.put((float) ((Math.cos(((theta + dtheta) * DTOR)) * Math.cos(((phi + dphi) * DTOR))) * R));
                mVertexBuffer.put((float) ((Math.cos(((theta + dtheta) * DTOR)) * Math.sin(((phi + dphi) * DTOR))) * R/1.5));
                mVertexBuffer.put((float) (Math.sin(((theta + dtheta) * DTOR)) * R));
                n += 3;

                mVertexBuffer.put((float) ((Math.cos((theta * DTOR)) * Math.cos(((phi + dphi) * DTOR))) * R));
                mVertexBuffer.put((float) ((Math.cos((theta * DTOR)) * Math.sin(((phi + dphi) * DTOR))) * R/1.5));
                mVertexBuffer.put((float) (Math.sin((theta * DTOR)) * R));
                n++;
                phi += dphi;
            }
            theta += dtheta;
        }

        mVertexBuffer.position(0);
    }

    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CCW);    // Front face in counter-clockwise orientation
        gl.glEnable(GL10.GL_CULL_FACE); // Enable cull face
        gl.glCullFace(GL10.GL_BACK);    // Cull the back face (don't display)
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
        gl.glColor4f(0,1,1,1);
        int i = 0;
        i = 0;
        while (i < n) {
            gl.glColor4f(0,1,1,1);
            gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, i, 4);
            i += 4;
        }
        gl.glColor4f(0,1,1,1);
    }
}
