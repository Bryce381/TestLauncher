package com.example.testlauncher.ui.network;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.testlauncher.R;
import com.example.testlauncher.logic.model.NetworkStatusModel;
import com.example.testlauncher.tool.LogUtil;

import org.w3c.dom.Text;

public class NetworkMonitorActivity extends AppCompatActivity {

    TextView networkConnect;
    TextView networkType;
    Boolean connect;
    String connectionType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.d("TAG", "onCreate");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_network_monitor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.networkMonitor), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        networkConnect = findViewById(R.id.networkConnect);
        networkType = findViewById(R.id.networkType);
        networkConnect.setText(NetworkStatusModel.isConnected() ? "Connected" : "Not connected");
        networkType.setText(NetworkStatusModel.getConnectionType());


//        networkViewModel.getNetworkUsage().observe(this, networkUsageModel -> {
//            // Update UI based on networkUsageModel
//        });
    }
}