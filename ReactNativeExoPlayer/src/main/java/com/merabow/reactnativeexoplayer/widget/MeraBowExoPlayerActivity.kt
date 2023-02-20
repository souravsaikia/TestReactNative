package com.merabow.reactnativeexoplayer.widget

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.reactnativeexoplayer.R
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlin.math.roundToInt

class MeraBowExoPlayerActivity : AppCompatActivity()
    , MeraBowExoPlayer.ChatbotVideoStateListener ,
    MeraBowExoPlayer.VideoDimensionsListener {

    private var videoUrl = ""
    private var thumbnailUrl = ""
    private var videoPlayerView: PlayerView? = null
    private var progressLoader: CircularProgressIndicator? = null
    private var merabowExoPlayer: MeraBowExoPlayer? = null
    private var videoControlView: MeraVideoControlView? = null
    private var parent: ConstraintLayout? = null

    private var videoWidth = 0
    private var videoHeight = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mera_bow_exo_player)

        initViews()
        getVideoDetailsData()
        initVideoPlayer()
    }

    private fun initViews() {
        videoPlayerView = findViewById(R.id.video_player)
        progressLoader = findViewById(R.id.loader)
        videoControlView = findViewById(R.id.video_control)
        parent = findViewById(R.id.parent_view)
    }

    private fun getVideoDetailsData() {
        videoUrl = intent?.getStringExtra("videoUrl") ?: ""
        thumbnailUrl = intent?.getStringExtra("thumbnailUrl") ?: ""
    }

    private fun initVideoPlayer() {
        merabowExoPlayer = MeraBowExoPlayer(this, videoControlView)
        videoPlayerView?.player = merabowExoPlayer?.getExoPlayer()
        merabowExoPlayer?.start(videoUrl)
        merabowExoPlayer?.videoStateListener = this
        initListenerForVideoPlayer()
    }

    private fun initListenerForVideoPlayer() {
        videoPlayerView?.videoSurfaceView?.setOnClickListener {
            if (merabowExoPlayer?.getExoPlayer()?.isPlaying == true) {
                merabowExoPlayer?.pause()
            } else {
                merabowExoPlayer?.resume()
            }
        }

        merabowExoPlayer?.videoDimensionsListener = this
        merabowExoPlayer?.getExoPlayer()?.addListener(object : Player.Listener {
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                super.onPlayerStateChanged(playWhenReady, playbackState)
                if (playbackState == Player.STATE_READY) {
                    val duration = merabowExoPlayer!!.getExoPlayer().duration ?: 0
                }
            }
        })

    }

    override fun onInitialStateLoading() {
        progressLoader?.visibility = View.VISIBLE
        progressLoader?.bringToFront()
    }

    override fun onVideoReadyToPlay() {
        progressLoader?.gone()
    }

    override fun onVideoStateChange(stopDuration: Long, videoDuration: Long) {
        //nope
    }

    override fun onVideoPlayerError(e: PlaybackException) {
    }

    override fun onPause() {
        super.onPause()
        merabowExoPlayer?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        merabowExoPlayer?.destroy()
    }

    override fun onResume() {
        super.onResume()
        merabowExoPlayer?.resume()
    }

    override fun onStop() {
        super.onStop()
        merabowExoPlayer?.stop()
    }

    override fun setWidth(width: Int) {
        videoWidth = width
    }

    override fun setHeight(height: Int) {
        videoHeight = height
        setLayoutParams()
   //     parent?.requestLayout()
    }

    private fun setLayoutParams() {
        adjustLayoutDimensions(videoWidth, videoHeight)
    }

    private fun adjustLayoutDimensions(width: Int, height: Int) {
        parent?.layoutParams = ConstraintLayout.LayoutParams(width, height)
        val params = parent?.layoutParams as ConstraintLayout.LayoutParams
        params.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID
        params.topMargin = TOP_MARGIN
        parent?.layoutParams = params
        videoPlayerView?.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
    }

    private fun convertDpToPixel(dp: Float, context: Context): Int {
        val r = context.resources
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.displayMetrics).toInt()
    }

    companion object {
        const val MAX_ALLOWED_WIDTH = 240f
        const val TOP_MARGIN = 16
    }
}
