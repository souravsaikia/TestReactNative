package com.example.reactnativeexoplayer;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class MeraBowReactNativeHelper extends ReactContextBaseJavaModule {

    public MeraBowReactNativeHelper(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ReactNativeExoPlayer";
    }

    @ReactMethod
    public void sayHello() {
        Toast.makeText(getReactApplicationContext(), "Hello from MyModule!", Toast.LENGTH_SHORT).show();
    }

    @ReactMethod
    public void testForUi(String videoUrl) {
        Log.d("FATAL", "testForUi: " + videoUrl);
    }
}
