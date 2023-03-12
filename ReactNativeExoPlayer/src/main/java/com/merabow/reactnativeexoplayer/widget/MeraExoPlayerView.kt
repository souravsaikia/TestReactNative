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
    private var merabowExoPlayer: MeraBowExoPlayer? = null
    private var videoControlView: MeraVideoControlView? = null

    init {
        // Create an instance of ExoPlayer
        merabowExoPlayer = MeraBowExoPlayer(context, videoControlView)

    //   merabowExoPlayer?.videoStateListener = this
      //  initListenerForVideoPlayer()
    }

    fun setVideoUrl(videoUrl: String, thumbnailUrl: String) {
        videoPlayerView?.player = merabowExoPlayer?.getExoPlayer()
        merabowExoPlayer?.start(videoUrl)
        merabowExoPlayer?.myExoPlayer?.setVideoTextureView(this)
    }

    override fun onHostResume() {
        merabowExoPlayer?.resume()
    }

    override fun onHostPause() {
        merabowExoPlayer?.pause()
    }

    override fun onHostDestroy() {
        merabowExoPlayer?.destroy()
    }


}