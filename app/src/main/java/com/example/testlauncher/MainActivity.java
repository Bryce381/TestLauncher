package com.example.testlauncher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.testlauncher.service.NetworkMonitorService;
import com.example.testlauncher.tool.LogUtil;
import com.example.testlauncher.ui.network.NetworkMonitorActivity;

public class MainActivity extends AppCompatActivity{

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = new Intent(this, NetworkMonitorService.class);
        startService(intent);
        button = findViewById(R.id.Button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LogUtil.d("TAG", "onclick");
                Intent intent1 = new Intent(MainActivity.this, NetworkMonitorActivity.class);
                startActivity(intent1);
            }

        });
    }
}