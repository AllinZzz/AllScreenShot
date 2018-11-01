package com.allin.allscreenshot;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allin.allscreenshot.manager.ScreenShotManager;

import java.security.Permission;
import java.security.Permissions;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ImageView ivScreenShot;
    private TextView tvPath;
    private String[] permissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        requestPermission();
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

//            if (ContextCompat.checkSelfPermission(this,permissions))


            requestPermissions(permissions,100);
        } else {
            initScreenShotManager();
        }
    }

    private void initScreenShotManager() {
        ScreenShotManager.newInstance(this)
        .setListener(new ScreenShotManager.OnScreenShotListener() {
            @Override
            public void onShot(String imagePath) {
                Log.d(TAG,"onShot : " + imagePath);
                tvPath.setText(imagePath);
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                ivScreenShot.setImageBitmap(bitmap);
                ivScreenShot.setVisibility(View.VISIBLE);
            }
        }).startListen();
    }

    private void findView() {
        ivScreenShot = findViewById(R.id.iv_screen_shot);
        tvPath = findViewById(R.id.textView);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG,"requestCode : " + requestCode + " , permissions " + Arrays.toString(permissions) + ", grantResults : " + Arrays.toString(grantResults));
        if (requestCode == 100) {
            initScreenShotManager();
        }
    }
}
