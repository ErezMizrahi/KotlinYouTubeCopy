package com.erez8.youtubecopykotlin.Modle


data class RelatedVideos (
    val kind: String,
    val etag: String,
    val nextPageToken: String,
    val regionCode: String,
    val pageInfo: RelatedVideosPageInfo,
    val items: List< RelatedVideosItem>
)

data class RelatedVideosItem (
    val kind: RelatedVideosItemKind,
    val etag: String,
    val id: ID,
    val snippet: Snippet
)

data class ID (
    val kind: IDKind,
    val videoID: String
)

enum class IDKind {
    YoutubeVideo
}

enum class RelatedVideosItemKind {
    YoutubeSearchResult
}

data class RelatedVideosSnippet (
    val publishedAt: String,
    val channelID: String,
    val title: String,
    val description: String,
    val thumbnails: Thumbnails,
    val channelTitle: String,
    val liveBroadcastContent: LiveBroadcastContent
)

enum class RelatedVideosLiveBroadcastContent {
    Live,
    None
}

data class RelatedVideosThumbnails (
    val default: Default,
    val medium: Default,
    val high: Default
)

data class RelatedVideosDefault (
    val url: String,
    val width: Long,
    val height: Long
)

data class RelatedVideosPageInfo (
    val totalResults: Long,
    val resultsPerPage: Long
)
