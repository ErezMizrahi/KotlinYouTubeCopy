package com.erez8.youtubecopykotlin.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.erez8.youtubecopykotlin.Modle.RelatedVideos
import com.erez8.youtubecopykotlin.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.related_video_view.view.*
import kotlinx.android.synthetic.main.video_view.view.*

class PlayVideoAdapter (feed : RelatedVideos) : RecyclerView.Adapter<RelatedVideosViewHolder>() {
    val videos  = feed.items

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RelatedVideosViewHolder {
       val layout = LayoutInflater.from(p0.context)
        val cellForRow = layout.inflate(R.layout.related_video_view,p0,false)
        return RelatedVideosViewHolder(cellForRow)
    }

    override fun getItemCount(): Int { return videos.count() }

    override fun onBindViewHolder(p0: RelatedVideosViewHolder, p1: Int) {
        val videoInfo = videos.get(p1).snippet
        p0.view.textView_videoName_relatedVideos.text = videoInfo.title
        p0.view.textView3_channel_name_relatedVideos.text = videoInfo.channelTitle
        p0.view.textView_date_relatedVideos.text = videoInfo.publishedAt
        Picasso.get().load(videoInfo.thumbnails.default.url).into(p0.view.imageView_thumbnail)
    }

}

class RelatedVideosViewHolder(val view : View) : RecyclerView.ViewHolder(view)