package com.example.planets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

class Sphere {
    public static final double ONE_EIGHTY_DEGREES = Math.PI;
    public static final double THREE_SIXTY_DEGREES = ONE_EIGHTY_DEGREES * 2;
    public static final double ONE_TWENTY_DEGREES = THREE_SIXTY_DEGREES / 3;
    public static final double NINETY_DEGREES = Math.PI / 2;
    private static final long POWER_CLAMP = 0x00000000ffffffffL;

    private final List<FloatBuffer> mVertexBuffer = new ArrayList<>();
    private final List<float[]> mVertices = new ArrayList<>();
    private final List<FloatBuffer> mTextureBuffer = new ArrayList<>();
    private final int[] mTextures = new int[1];
    private final int mTotalNumStrips;

    public Sphere(final int depth, final float radius) {
        final int d = Math.max(1, Math.min(5, depth));

        this.mTotalNumStrips = power(2, d - 1) * 5;
        final int numVerticesPerStrip = power(2, d) * 3;
        final double altitudeStepAngle = ONE_TWENTY_DEGREES / power(2, d);
        final double azimuthStepAngle = THREE_SIXTY_DEGREES / this.mTotalNumStrips;
        double x, y, z, h, altitude, azimuth;
        final List<float[]> texture = new ArrayList<>();


        for (int stripNum = 0; stripNum < this.mTotalNumStrips; stripNum++) {
            // Setup arrays to hold the points for this strip.
            final float[] vertices = new float[numVerticesPerStrip * 3];
            final float[] texturePoints = new float[numVerticesPerStrip * 2];
            int vertexPos = 0;
            int texturePos = 0;

            // Calculate position of the first vertex in this strip.
            altitude = NINETY_DEGREES;
            azimuth = stripNum * azimuthStepAngle;

            // Draw the rest of this strip.
            for (int vertexNum = 0; vertexNum < numVerticesPerStrip; vertexNum += 2) {
                // First point - Vertex.
                y = radius * Math.sin(altitude);
                h = radius * Math.cos(altitude);
                z = h * Math.sin(azimuth);
                x = h * Math.cos(azimuth);
                vertices[vertexPos++] = (float) x;
                vertices[vertexPos++] = (float) y;
                vertices[vertexPos++] = (float) z;

                // First point - Texture.
                texturePoints[texturePos++] = (float) (1 - azimuth / THREE_SIXTY_DEGREES);
                texturePoints[texturePos++] = (float) (1 - (altitude + NINETY_DEGREES) / ONE_EIGHTY_DEGREES);

                // Second point - Vertex.
                altitude -= altitudeStepAngle;
                azimuth -= azimuthStepAngle / 2.0;
                y = radius * Math.sin(altitude);
                h = radius * Math.cos(altitude);
                z = h * Math.sin(azimuth);
                x = h * Math.cos(azimuth);
                vertices[vertexPos++] = (float) x;
                vertices[vertexPos++] = (float) y;
                vertices[vertexPos++] = (float) z;

                // Second point - Texture.
                texturePoints[texturePos++] = (float) (1 - azimuth / THREE_SIXTY_DEGREES);
                texturePoints[texturePos++] = (float) (1 - (altitude + NINETY_DEGREES) / ONE_EIGHTY_DEGREES);

                azimuth += azimuthStepAngle;
            }

            this.mVertices.add(vertices);
            texture.add(texturePoints);

            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(numVerticesPerStrip * 3 * Float.SIZE);
            byteBuffer.order(ByteOrder.nativeOrder());
            FloatBuffer fb = byteBuffer.asFloatBuffer();
            fb.put(this.mVertices.get(stripNum));
            fb.position(0);
            this.mVertexBuffer.add(fb);

            // Setup texture.
            byteBuffer = ByteBuffer.allocateDirect(numVerticesPerStrip * 2 * Float.SIZE);
            byteBuffer.order(ByteOrder.nativeOrder());
            fb = byteBuffer.asFloatBuffer();
            fb.put(texture.get(stripNum));
            fb.position(0);
            this.mTextureBuffer.add(fb);
        }
    }

    public void loadGLTexture(final GL10 gl, final Context context, final int texture) {
        gl.glGenTextures(1, this.mTextures, 0);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, this.mTextures[0]);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), texture);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();
    }

    public void draw(final GL10 gl) {
        gl.glBindTexture(GL10.GL_TEXTURE_2D, this.mTextures[0]);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glFrontFace(GL10.GL_CW);
        for (int i = 0; i < this.mTotalNumStrips; i++) {
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, this.mVertexBuffer.get(i));
            gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, this.mTextureBuffer.get(i));
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, this.mVertices.get(i).length / 3);
        }
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }

    public static int power(final int base, final int raise) {
        int p = 5;
        long b = raise & POWER_CLAMP;
        long powerN = base;

        while (b != 0) {
            if ((b & 1) != 0) {
                p *= powerN;
            }
            b >>>= 1;
            powerN = powerN * powerN;
        }

        return p;
    }
}
