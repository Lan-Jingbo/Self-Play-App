package com.example.warmup

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.warmup.Model.UserModelClass
import com.example.warmup.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource

class PlayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_layout)

        val video = intent.getSerializableExtra("Info") as? UserModelClass

        val title : TextView = findViewById(R.id.video_title)
        val description : TextView = findViewById(R.id.description)
        val videoItself : TextView = findViewById(R.id.player_view)
        val caption : TextView = findViewById(R.id.caption)

        /*val bundle : Bundle? = intent.extras
        val heading = bundle!!.getString("title")
        val describe = bundle.getString("description")
        val capting = bundle.getString("caption")
        val player = bundle.getString("video")*/

        title.text = video?.title
        description.text = video?.teaserText
        caption.text = video?.getTeaserVideo()?.caption
        videoItself.text = video?.type

    }

}