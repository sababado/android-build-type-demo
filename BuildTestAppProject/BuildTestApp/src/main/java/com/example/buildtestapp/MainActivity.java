package com.example.buildtestapp;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((TextView)findViewById(R.id.app_id)).setText(getMetaData("app_id"));
        ((TextView)findViewById(R.id.access_key)).setText(getMetaData("access_key"));
        ((TextView)findViewById(R.id.encrypt_key)).setText(getMetaData("encrypt_key"));
    }

    /**
     * Get a meta-data value from the manifest.
     * @param key Key to lookup by.
     * @return A value is returned, or "Key Not Found" if the key doesn't exist.
     */
    private String getMetaData(String key) {
        try {
            ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            return bundle.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Failed to load meta-data, NameNotFound: " + e.getMessage());
        } catch (NullPointerException e) {
            Log.e(TAG, "Failed to load meta-data, NullPointer: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Failed to load meta-data, Exception: " + e.getMessage());
        }
        return "Key Not Found";
    }
}
