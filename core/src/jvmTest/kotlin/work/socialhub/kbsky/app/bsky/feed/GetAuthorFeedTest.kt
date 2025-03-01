package work.socialhub.kbsky.app.bsky.feed

import work.socialhub.kbsky.AbstractTest
import work.socialhub.kbsky.BlueskyFactory
import work.socialhub.kbsky.api.entity.app.bsky.feed.FeedGetAuthorFeedRequest
import work.socialhub.kbsky.domain.Service
import kotlin.test.Test

class GetAuthorFeedTest : AbstractTest() {

    @Test
    fun testGetAuthorFeed() {
        val feeds = BlueskyFactory
            .instance(Service.BSKY_SOCIAL.uri)
            .feed()
            .getAuthorFeed(
                FeedGetAuthorFeedRequest(auth()).also {
                    it.actor = "uakihir0.com"
                }
            )

        feeds.data.feed.forEach {
            print(it.post)
        }
    }
}
