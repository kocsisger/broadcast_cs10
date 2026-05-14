package hu.unideb.inf.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver customReceiver = new CustomReceiver();
    static final String ACTION_CUSTOM_BROADCAST = "hu.unideb.inf.myapplication.MainActivity.ACTION_CUSTOM_BROADCAST_57edjmnoüöji9rtf68";

    IntentFilter filter;

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

        filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(ACTION_CUSTOM_BROADCAST);

        new Thread(
                () -> {
                    for (int i = 0; i < 20; i++) {
                        try {
                            Thread.sleep(1000);
                            Log.d("PendingIntent", "Count:" + (i+1));
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).start();

        Intent intent = new Intent(this, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
                );

        AlarmManager alarmManager = getSystemService(AlarmManager.class);
        long triggerTime = System.currentTimeMillis() + 10*1000;

        alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                triggerTime,
                pendingIntent
        );

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        this.registerReceiver(customReceiver, filter, RECEIVER_EXPORTED);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(customReceiver);
    }

    public void sendCustomBroadcast(View view) {
        Intent intent = new Intent(ACTION_CUSTOM_BROADCAST);
        this.sendBroadcast(intent);
    }
}