package com.zzu.rensiyu.opengl1047;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author 76147
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView surfaceView = new GLSurfaceView(this);
        MyRenderer renderer = new MyRenderer();
        surfaceView.setRenderer(renderer);
        setContentView(surfaceView);
    }
}
