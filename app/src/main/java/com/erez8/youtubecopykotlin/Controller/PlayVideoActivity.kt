Epackage com.erez8.youtubecopykotlin.Controller

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.MediaController
import com.erez8.youtubecopykotlin.Adapter.MainAdapter
import com.erez8.youtubecopykotlin.Adapter.PlayVideoAdapter
import com.erez8.youtubecopykotlin.Modle.RelatedVideos
import com.erez8.youtubecopykotlin.Modle.VideoModle
import com.erez8.youtubecopykotlin.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_play_video.*
import okhttp3.*
import java.io.IOException
import java.net.URI

class PlayVideoActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {

            p1?.loadVideo(id)

    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
            print("fail")
    }

    var id = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_video)

        id = intent.getStringExtra("id")
        Log.d("videoID",id)
        this.init()

        this.fetchJson(id)

        play_button.setOnClickListener {
            youtube_player.initialize("Apikey ",this)
        }
    }

    fun init() {
        playVideo_recyclerview.layoutManager = LinearLayoutManager(this)

        val videoURL = "https://www.youtube.com/watch?v=$id"
        val uri = Uri.parse(videoURL)


    }

    fun fetchJson( id : String) {
        val url = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=20&relatedToVideoId=$id&type=video&key=APikey"
        print(id)

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("faild", e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                val serverResponse = response.body?.string()
                Log.i("yes", serverResponse)

                val gson = GsonBuilder().create()
                val feed = gson.fromJson(serverResponse, RelatedVideos::class.java)

                runOnUiThread {
                    playVideo_recyclerview.adapter = PlayVideoAdapter(feed)
                }
            }

        })
    }
}
