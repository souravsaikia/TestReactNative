package com.merabow.exoplayersample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exoplayersample.R
import com.merabow.reactnativeexoplayer.widget.MeraBowExoPlayerActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startVideoStream("" , "https://admin.merabow.com/api/play-video/63e2116cdcaf80ad1e221699/0")
    }

    fun startVideoStream(thumbnailUrl: String, videoUrl: String) {
        val intent = Intent(this, MeraBowExoPlayerActivity::class.java)
        intent.putExtra("videoUrl", videoUrl)
        intent.putExtra("thumbnailUrl", thumbnailUrl)
        startActivity(intent)
    }
}