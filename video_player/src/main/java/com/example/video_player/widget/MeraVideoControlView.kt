package com.example.video_player.widget

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.example.video_player.R
import com.google.android.exoplayer2.ui.DefaultTimeBar
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.ui.TimeBar

class MeraVideoControlView(context: Context, attributeSet: AttributeSet) :
    PlayerControlView(context, attributeSet) {

    private val scrubber: DefaultTimeBar =
        findViewById(com.google.android.exoplayer2.R.id.exo_progress)

    private val centerPlayButton: ImageView = findViewById(R.id.video_center_play_button)
    private val centerPauseButton: ImageView = findViewById(R.id.video_center_pause_button)
    private val videoControlContainer: LinearLayout = findViewById(R.id.nav_container)
    private val videoPauseButtonHandler = Handler(Looper.getMainLooper())
    private val controllerPlayButton : ImageView = findViewById(com.google.android.exoplayer2.R.id.exo_play)
    private val controllerPauseButton : ImageView = findViewById(com.google.android.exoplayer2.R.id.exo_pause)

    var listener: Listener? = null
        set(value) {
            field = value
            setUpListener()
        }

    init {
        showController(true)
    }

    fun updateCenterButtonState(isPlaying: Boolean) {
        centerPlayButtonConditionalShow(!isPlaying)
        centerPauseButtonConditionalShow(isPlaying)
        controllerPlayButtonConditionalShow(!isPlaying)
        controllerPauseButtonConditionalShow(isPlaying)
    }

    private fun centerPlayButtonConditionalShow(isShowing: Boolean) {
        centerPlayButton.showWithCondition(isShowing)
    }

    private fun centerPauseButtonConditionalShow(isShowing: Boolean) {
        centerPauseButton.showWithCondition(isShowing)
        if (isShowing) {
            videoPauseButtonHandler.postDelayed({
                centerPauseButtonConditionalShow(false)
            }, HIDE_DELAY)
        }
    }

    private fun controllerPlayButtonConditionalShow(isShowing: Boolean) {
        controllerPlayButton.showWithCondition(isShowing)
    }

    private fun controllerPauseButtonConditionalShow(isShowing: Boolean) {
        controllerPauseButton.showWithCondition(isShowing)
    }

    private fun setUpListener() {
        centerPlayButton.setOnClickListener {
            listener?.onCenterPlayButtonClicked()
            if (!videoControlContainer.isVisible) {
                showController(true)
            }
        }

        centerPauseButton.setOnClickListener {
            it.hide()
            centerPlayButton.show()
            listener?.onCenterPauseButtonClicked()
        }

        scrubber.addListener(object : TimeBar.OnScrubListener {
            override fun onScrubStart(timeBar: TimeBar, position: Long) {
                listener?.onScrubMove(position)
            }

            override fun onScrubMove(timeBar: TimeBar, position: Long) {
                listener?.onScrubStart()
            }

            override fun onScrubStop(timeBar: TimeBar, position: Long, cancelled: Boolean) {
                listener?.onScrubStop()
            }

        })

    }

    fun showController(toShow: Boolean) {
        videoControlContainer.showWithCondition(toShow)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        videoPauseButtonHandler.removeCallbacksAndMessages(null)
    }

    interface Listener {
        fun onCenterPlayButtonClicked()
        fun onCenterPauseButtonClicked()
        fun onScrubStart()
        fun onScrubStop()
        fun onScrubMove(position: Long)
    }

    companion object {
        const val HIDE_DELAY = 1000L
    }

}

fun View.showWithCondition(shouldShow: Boolean) {
    if (shouldShow) show() else gone()
}

fun View.show() {
    // optimize recalculate
    if (this.visibility != View.VISIBLE)
        this.visibility = View.VISIBLE
}

fun View.hide() {
    // optimize recalculate
    if (this.visibility != View.GONE)
        this.visibility = View.GONE
}

fun View.gone() {
    hide()
}