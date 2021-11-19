package com.jan.rappimovies.app.ui.video

import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.jan.rappimovies.app.R

class VideoPlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private var key = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        intent.getStringExtra("key")?.let { key ->
            this.key = key
            findViewById<YouTubePlayerView>(R.id.youtubePlayer).initialize(
                getString(R.string.youtube_api_key), this
            )
        } ?: run {
            Toast.makeText(
                this@VideoPlayerActivity,
                getString(R.string.lab_video_key_error),
                Toast.LENGTH_SHORT
            ).show()
            onBackPressed()
        }
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?, player: YouTubePlayer?, b: Boolean
    ) {
        player?.loadVideo(key)
        player?.play()
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?, result: YouTubeInitializationResult?
    ) {
        Toast.makeText(
            this@VideoPlayerActivity, getString(R.string.msg_youtube_error), Toast.LENGTH_SHORT
        ).show()
    }
}