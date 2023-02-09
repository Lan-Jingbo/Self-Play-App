package com.example.warmup

import android.os.Bundle
import android.util.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.*
import com.example.warmup.Model.*
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.gson.GsonBuilder
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    private val gson = GsonBuilder()
        .setLenient()
        .registerTypeAdapter(
            TeaserVideoResponse::class.java,
            TeaserVideoDeserializer()
        )
        .create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // All users are here
        val usersList: ArrayList<UserModelClass> = ArrayList()

        try {
            val obj = JSONObject(getJSONFromAssets()!!) // assert it must exist
            Log.e("TEST", "obj : $obj")
            val usersArray = obj.getJSONArray("tvShows") // the name should be the same as json file header
            val data = gson.fromJson(usersArray.toString(), Array<UserModelClass>::class.java)
            for (i in 0 until usersArray.length()) { // NO related to view presenting
                val user = data[i]
                val type = user.type
                val teaserText = user.teaserText
                val teaserImage = user.teaserImage
                val teaserVideo = user.teaserVideo
                val showurl = user.showUrl
                val id = user.id
                val description = user.description
                val title = user.title

                val videoDetail = UserModelClass(type, teaserText, teaserVideo, showurl, teaserImage,
                    id, description, title)

                usersList.add(videoDetail)
                /*val navy = findViewById<ShapeableImageView>(R.id.image_content)
                navy.setOnClickListener {
                    Toast.makeText(this, "You will go to next page",
                        Toast.LENGTH_SHORT).show()
                }*/
            }

        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }
        val rvUsersList = findViewById<RecyclerView>(R.id.rvUsersList)
        rvUsersList.layoutManager = LinearLayoutManager(this)
        val itemAdapter = UserAdapter(this, usersList)
        Log.e("TEST", "size : ${usersList.size}")
        rvUsersList.adapter = itemAdapter
    }


    /**
     * Method to load the JSON from the Assets file and return the object
     */
    private fun getJSONFromAssets(): String? {
        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = assets.open("Videos.json")
            val size = myUsersJSONFile.available()
            val buffer = ByteArray(size)
            myUsersJSONFile.read(buffer)
            myUsersJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            Log.e("TEST", "getJSONFromAssets() : ${ex.message}")
            ex.printStackTrace()
            return null
        }
        return json
    }
}

enum class URLType(var url: String) {
    DASH(""), HLS("");
}