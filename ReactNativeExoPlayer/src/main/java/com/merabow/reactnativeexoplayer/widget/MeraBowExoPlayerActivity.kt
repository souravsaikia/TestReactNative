package com.merabow.reactnativeexoplayer.widget

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.reactnativeexoplayer.R
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.material.progressindicator.CircularProgressIndicator

class MeraBowExoPlayerActivity : AppCompatActivity() {

    private var videoUrl = ""
    private var thumbnailUrl = ""
    private var videoPlayerView: PlayerView? = null
    private var progressLoader: CircularProgressIndicator? = null
    private var errorImage: ImageView? = null
    private var merabowExoPlayer: MeraBowExoPlayer? = null
    private var videoControlView: MeraVideoControlView? = null
    private var parentLayout: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mera_bow_exo_player)

        initViews()
        getVideoDetailsData()
    }

    private fun initViews() {
        videoPlayerView = findViewById(R.id.video_player)
        progressLoader = findViewById(R.id.loader)
        videoControlView = findViewById(R.id.video_control)
    }

    private fun getVideoDetailsData() {
        videoUrl = intent?.getStringExtra("videoUrl") ?: ""
        thumbnailUrl = intent?.getStringExtra("thumbnailUrl") ?: ""
    }
}
