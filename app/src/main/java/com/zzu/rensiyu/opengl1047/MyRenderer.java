package com.zzu.rensiyu.opengl1047;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @author 76147
 */
public class MyRenderer implements GLSurfaceView.Renderer {


    float[] data1 = new float[]{
            0f, 0f, 0f,
            -0.6f, 0.6f, -0.2f,
            0.3f, 0.3f, 0.2f,
            0.8f, 0.3f, -0.6f
    };

    byte[] byte1 = new byte[]{
            0, 1, 2,
            1, 2, 3
    };
    int[] color1 = new int[]{
            0, 65535, 0, 0, // 最下顶点绿色
            0, 0, 65535, 0, // 左1顶点蓝色
            65535, 0, 0, 0, // 右1顶点红色
            65535, 65535, 0, 0 // 右2顶点黄色
    };

    float[] data2 = new float[]{
//            上面四个点
            -0.3f, 0.1f, -0.3f,
            -0.6f, -0.1f, 0f,
            0f, -0.1f, 0f,
            0.3f, 0.1f, -0.3f,
//            下面四个点
            -0.3f, -0.4f, -0.3f,
            -0.6f, -0.6f, 0f,
            0f, -0.6f, 0f,
            0.3f, -0.4f, -0.3f
    };
    //立方体的结构
    byte[] byte2 = new byte[]{
            0, 1, 2,
            1, 2, 3,
            3, 0, 4,
            0, 1, 5,
            1, 5, 4,
            5, 4, 7,
            4, 7, 6,
            7, 6, 5,
            5, 6, 2,
            2, 3, 7
    };

    private FloatBuffer dataBuffer1;
    private ByteBuffer byteBuffer1;
    private IntBuffer colorData1;
    private FloatBuffer dataBuffer2;
    private ByteBuffer byteBuffer2;


    public MyRenderer() {
        dataBuffer1 = floatBufferUtil(data1);
        byteBuffer1 = ByteBuffer.wrap(byte1);
        colorData1 = intBufferUtil(color1);

        dataBuffer2 = floatBufferUtil(data2);
        byteBuffer2 = ByteBuffer.wrap(byte2);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
//        gl.glClearColor(1, 1, 0, 0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        // 启用顶点坐标数据1
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, dataBuffer1);
        gl.glColorPointer(4, GL10.GL_FIXED, 0, colorData1);
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, byteBuffer1.remaining(), GL10.GL_UNSIGNED_BYTE, byteBuffer1);


        gl.glLoadIdentity();
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, dataBuffer2);
        gl.glColor4f(0, 1, 0, 0);
        gl.glLineWidth(5f);
        gl.glDrawElements(GL10.GL_LINE_LOOP, byteBuffer2.remaining(), GL10.GL_UNSIGNED_BYTE, byteBuffer2);


        gl.glFinish();
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

    // 定义一个工具方法，将int[]数组转换为OpenGL ES所需的IntBuffer
    private IntBuffer intBufferUtil(int[] arr) {
        IntBuffer mBuffer;
        // 初始化ByteBuffer，长度为arr数组的长度*4，因为一个int占4字节
        ByteBuffer qbb = ByteBuffer.allocateDirect(arr.length * 4);
        // 数组排列用nativeOrder
        qbb.order(ByteOrder.nativeOrder());
        mBuffer = qbb.asIntBuffer();
        mBuffer.put(arr);
        mBuffer.position(0);
        return mBuffer;
    }

    // 定义一个工具方法，将float[]数组转换为OpenGL ES所需的FloatBuffer
    private FloatBuffer floatBufferUtil(float[] arr) {
        FloatBuffer mBuffer;
        // 初始化ByteBuffer，长度为arr数组的长度*4，因为一个int占4字节
        ByteBuffer qbb = ByteBuffer.allocateDirect(arr.length * 4);
        // 数组排列用nativeOrder
        qbb.order(ByteOrder.nativeOrder());
        mBuffer = qbb.asFloatBuffer();
        mBuffer.put(arr);
        mBuffer.position(0);
        return mBuffer;
    }
}
