package com.allin.allscreenshot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.allin.allscreenshot.manager.ScreenShotManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ImageView ivScreenShot;
    private TextView tvPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initScreenShotManager();

    }

    private void initScreenShotManager() {
        ScreenShotManager.newInstance(this)
        .setListener(new ScreenShotManager.OnScreenShotListener() {
            @Override
            public void onShot(String imagePath) {
                Log.d(TAG,"onShot : " + imagePath);
            }
        }).startListen();
    }

    private void findView() {
        ivScreenShot = findViewById(R.id.iv_screen_shot);
        tvPath = findViewById(R.id.textView);
    }
}
