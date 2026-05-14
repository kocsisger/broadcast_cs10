package hu.unideb.inf.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String toastMessage = "Unknown action received.";

        switch (intent.getAction()){
            case Intent.ACTION_POWER_CONNECTED:    toastMessage = "Power connected.";    break;
            case Intent.ACTION_POWER_DISCONNECTED: toastMessage = "Power disconnected."; break;
            case MainActivity.ACTION_CUSTOM_BROADCAST: toastMessage = "Custom broadcast received.";
        }

        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
    }
}
