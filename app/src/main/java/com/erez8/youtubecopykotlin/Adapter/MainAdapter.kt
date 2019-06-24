package com.erez8.youtubecopykotlin.Adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.erez8.youtubecopykotlin.Controller.PlayVideoActivity
import com.erez8.youtubecopykotlin.Controller.VideoClickEvent
import com.erez8.youtubecopykotlin.Modle.VideoModle
import com.erez8.youtubecopykotlin.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_view.view.*



class MainAdapter(val feed : VideoModle, delegate : VideoClickEvent) : RecyclerView.Adapter<ViewHolder>() {

    var viewDelegate = delegate
    val videos  = feed.items


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        val  cellForRow = inflater.inflate(R.layout.video_view,p0,false)
        return ViewHolder(cellForRow,viewDelegate)
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val videoTitle = videos.get(p1).snippet
        val videoID = videos.get(p1).id

        p0.view.textView_video_title.text = videoTitle.title
        p0.view.textView_channel_name.text = videoTitle.channelTitle
        Picasso.get().load(videoTitle.thumbnails.high.url).into(p0.view.imageView_video_view)
    }

}


class ViewHolder (val view : View, val delegate: VideoClickEvent) : RecyclerView.ViewHolder(view)  {
    val holderDelegate = delegate
    init {
        view.setOnClickListener {
                delegate.videoHasBeenClicked(adapterPosition)

        }
    }
}
