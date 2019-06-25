package com.erez8.youtubecopykotlin.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.erez8.youtubecopykotlin.Adapter.MainAdapter
import com.erez8.youtubecopykotlin.Modle.ChannelModel
import com.erez8.youtubecopykotlin.Modle.VideoModle
import com.erez8.youtubecopykotlin.R
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity(), VideoClickEvent {

    lateinit var list : VideoModle
    val delegate = this


    override fun videoHasBeenClicked(p1: Int) {

        val intent = Intent(this,PlayVideoActivity::class.java)
        intent.putExtra("id", list.items[p1].id)
        startActivity(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.init()
        this.fetchJson()
    }

    fun init() {
        recylerView_main.layoutManager = LinearLayoutManager(this)
    }

    fun fetchChannelJson() {
        val url = "https://yt3.ggpht.com/a/AGF-l78jSjcRilhkD11bTIjnlJA1ziqBJwrVNXHPAw=s88-mo-c-c0xffffffff-rj-k-no"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("faild", e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                val gson = GsonBuilder().create()
                val channel = gson.fromJson(body, ChannelModel::class.java)

            }

        })
    }

    fun fetchJson() {

        val url = "https://www.googleapis.com/youtube/v3/videos?part=snippet%20&chart=mostPopular&maxResults=30&key=-Apikey"

        val request  = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("faild", e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                val serverResponse = response.body?.string()
                Log.i("yes", serverResponse)

                val gson = GsonBuilder().create()
                val feed = gson.fromJson(serverResponse,VideoModle:: class.java)
                list = feed
                runOnUiThread {
                    recylerView_main.adapter = MainAdapter(feed,delegate)
                }
            }

        })
    }
}


interface VideoClickEvent{
    fun videoHasBeenClicked(p1 : Int)
}
