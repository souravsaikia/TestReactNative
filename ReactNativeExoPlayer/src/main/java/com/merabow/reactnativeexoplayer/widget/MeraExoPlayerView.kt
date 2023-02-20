package com.merabow.reactnativeexoplayer.widget

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.TextureView
import com.facebook.react.bridge.LifecycleEventListener
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.material.progressindicator.CircularProgressIndicator


class MeraExoPlayerView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextureView(context, attrs, defStyleAttr), LifecycleEventListener {

    private var videoPlayerView: PlayerView? = null
    private var progressLoader: CircularProgressIndicator? = null
    private var merabowExoPlayer: MeraBowExoPlayer? = null
    private var videoControlView: MeraVideoControlView? = null

    init {
        // Create an instance of ExoPlayer
        merabowExoPlayer = MeraBowExoPlayer(context, videoControlView)
        videoPlayerView?.player = merabowExoPlayer?.getExoPlayer()
        merabowExoPlayer?.start("https://admin.merabow.com/api/play-video/63e2116cdcaf80ad1e221699/0")
     //   videoPlayerView?.player?.setVideoTextureView(this)
        merabowExoPlayer?.myExoPlayer?.setVideoTextureView(this)
    //   merabowExoPlayer?.videoStateListener = this
      //  initListenerForVideoPlayer()
    }

    override fun onHostResume() {

    }

    override fun onHostPause() {

    }

    override fun onHostDestroy() {

    }


}