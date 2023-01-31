import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import com.example.warmup.Model.*
import com.example.warmup.R
import com.example.warmup.UserAdapter
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // All users are here
        val usersList: ArrayList<UserModelClass> = ArrayList()

        try {
            val obj = JSONObject(getJSONFromAssets()!!) // assert it must exist
            val usersArray = obj.getJSONArray("users")
            var gson = Gson()
            val data = gson.fromJson(usersArray.toString(), Array<UserModelClass>::class.java)
            for (i in 0 until usersArray.length()) {
                val user = data[i]
                val type = user.type
                val teaserText = user.teaserText
                val teaserImage = user.teaserImage
                val teaserVideo = user.teaserVideo
                val showurl = user.showurl
                val id = user.id
                val description = user.description
                val title = user.title
                val videoDetail = UserModelClass(type, teaserText, teaserVideo, showurl, teaserImage,
                    id, description, title)
                usersList.add(videoDetail)
            }

        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }
        print(usersList.size)
        val rvUsersList = findViewById<RecyclerView>(R.id.rvUsersList)
        rvUsersList.layoutManager = LinearLayoutManager(this)
        val itemAdapter = UserAdapter(this, usersList)
        android.util.Log.e("SIZE", "" + usersList.size)
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
            ex.printStackTrace()
            return null
        }
        return json
    }
}
