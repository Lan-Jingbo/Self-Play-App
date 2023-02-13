package com.example.warmup

import android.app.Activity
import android.os.Bundle
import android.provider.MediaStore.Video
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleExpandableListAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.warmup.Model.UserModelClass
import com.example.warmup.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.PlayerView

class PlayActivity : AppCompatActivity() {

    lateinit var simpleExoPlayer : SimpleExoPlayer
    lateinit var mediaItem : MediaItem
    lateinit var playerView : PlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_layout)

        val video = intent.getSerializableExtra("INFO") as? UserModelClass

        val title : TextView = findViewById(R.id.video_title)
        val description : TextView = findViewById(R.id.description)
        val videoItself : PlayerView = findViewById(R.id.player_view)
        val caption : TextView = findViewById(R.id.caption)

        title.text = video?.title
        description.text = video?.teaserText
        caption.text = video?.description

        simpleExoPlayer = SimpleExoPlayer.Builder(this).build() // Initialized
        playerView = videoItself // Initialized
        mediaItem = MediaItem.fromUri(video?.getTeaserVideo()?.url!!)
        playerView.setPlayer(simpleExoPlayer)
        simpleExoPlayer.setMediaItem(mediaItem)
        simpleExoPlayer.prepare()
        simpleExoPlayer.pause()
    }
}