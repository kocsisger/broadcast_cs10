package hu.unideb.inf.myapplication;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver customReceiver = new CustomReceiver();
    static final String ACTION_CUSTOM_BROADCAST = "hu.unideb.inf.myapplication.MainActivity.ACTION_CUSTOM_BROADCAST_57edjmnoüöji9rtf68";

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

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(ACTION_CUSTOM_BROADCAST);

        this.registerReceiver(customReceiver, filter, RECEIVER_EXPORTED);
    }

    public void sendCustomBroadcast(View view) {
        Intent intent = new Intent(ACTION_CUSTOM_BROADCAST);
        this.sendBroadcast(intent);
    }
}