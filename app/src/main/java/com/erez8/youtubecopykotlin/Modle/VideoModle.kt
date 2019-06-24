package com.erez8.youtubecopykotlin.Modle

data class VideoModle (
    val kind: String,
    val etag: String,
    val nextPageToken: String,
    val pageInfo: PageInfo,
    val items: List<Item>
)

data class Item (
    val kind: Kind,
    val etag: String,
    val id: String,
    val snippet: Snippet
)

enum class Kind {
    YoutubeVideo
}

data class Snippet (
    val publishedAt: String,
    val channelID: String,
    val title: String,
    val description: String,
    val thumbnails: Thumbnails,
    val channelTitle: String,
    val tags: List<String>? = null,
    val categoryID: String,
    val liveBroadcastContent: LiveBroadcastContent,
    val localized: Localized,
    val defaultAudioLanguage: DefaultAudioLanguage? = null,
    val defaultLanguage: DefaultLanguage? = null
)

enum class DefaultAudioLanguage {
    En,
    EnUS,
    Zxx
}

enum class DefaultLanguage {
    En,
    Es
}

enum class LiveBroadcastContent {
    None
}

data class Localized (
    val title: String,
    val description: String
)

data class Thumbnails (
    val default: Default,
    val medium: Default,
    val high: Default,
    val standard: Default,
    val maxres: Default
)

data class Default (
    val url: String,
    val width: Long,
    val height: Long
)

data class PageInfo (
    val totalResults: Long,
    val resultsPerPage: Long
)