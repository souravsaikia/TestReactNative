package com.merabow.reactnativeexoplayer

import android.content.Intent
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.merabow.reactnativeexoplayer.widget.MeraBowExoPlayerActivity
import com.merabow.reactnativeexoplayer.widget.MeraExoPlayerView

class MeraBowReactNativeHelper(reactContext: ReactApplicationContext?) :
    ReactContextBaseJavaModule(reactContext) {
    override fun getName(): String {
        return "ReactNativeExoPlayer"
    }

    @ReactMethod
    fun sayHello() {
        Toast.makeText(reactApplicationContext, "Hello from MyModule!", Toast.LENGTH_SHORT).show()
    }

    @ReactMethod
    fun testForUi(videoUrl: String) {
        Log.d("FATAL", "testForUi: $videoUrl")
    }

    @ReactMethod
    fun startVideoStream(thumbnailUrl: String, videoUrl: String) {
        val intent = Intent(reactApplicationContext, MeraBowExoPlayerActivity::class.java)
        intent.putExtra("videoUrl", videoUrl)
        intent.putExtra("thumbnailUrl", thumbnailUrl)
        reactApplicationContext.startActivity(intent)
    }

    private var playerView: MeraExoPlayerView? = null

    @ReactMethod
    fun createPlayer() {
        // Create a new instance of VideoPlayerView and add it to the React view hierarchy
        playerView = MeraExoPlayerView(reactApplicationContext)
        (reactApplicationContext as ReactContext).addLifecycleEventListener(playerView!!)
        (reactApplicationContext as ReactContext).currentActivity?.let {
            (it as ViewGroup).addView(playerView)
        }
    }

}