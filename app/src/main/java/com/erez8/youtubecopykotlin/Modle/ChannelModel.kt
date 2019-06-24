package com.erez8.youtubecopykotlin.Modle


data class ChannelModel (
    val kind: String,
    val etag: String,
    val pageInfo: ChannelPageInfo,
    val items: List<Item>
)

data class ChannelItem (
    val kind: String,
    val etag: String,
    val id: String,
    val snippet: ChannelSnippet
)

data class ChannelSnippet (
    val title: String,
    val description: String,
    val customURL: String,
    val publishedAt: String,
    val thumbnails: ChannelThumbnails,
    val defaultLanguage: String,
    val localized: ChannelLocalized,
    val country: String
)

data class ChannelLocalized (
    val title: String,
    val description: String
)

data class ChannelThumbnails (
    val default: ChannelDefault,
    val medium: ChannelDefault,
    val high: ChannelDefault
)

data class ChannelDefault (
    val url: String,
    val width: Long,
    val height: Long
)

data class ChannelPageInfo (
    val totalResults: Long,
    val resultsPerPage: Long
)
