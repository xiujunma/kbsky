package work.socialhub.kbsky.model.share

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.kbsky.model.bsky.actor.ActorProfile
import work.socialhub.kbsky.model.bsky.feed.FeedLike
import work.socialhub.kbsky.model.bsky.feed.FeedPost
import work.socialhub.kbsky.model.bsky.feed.FeedRepost
import work.socialhub.kbsky.model.bsky.graph.GraphBlock
import work.socialhub.kbsky.model.bsky.graph.GraphFollow
import work.socialhub.kbsky.model.bsky.graph.GraphListItem
import work.socialhub.kbsky.util.json.RecordPolymorphicSerializer

/**
 * @see ActorProfile
 * @see GraphFollow
 * @see GraphBlock
 * @see FeedLike
 * @see FeedPost
 * @see FeedRepost
 * @see GraphListItem
 */
@Serializable(with = RecordPolymorphicSerializer::class)
abstract class RecordUnion {
    @SerialName("\$type")
    abstract var type: String

    val asActorProfile get() = this as? ActorProfile
    val asGraphFollow get() = this as? GraphFollow
    val asGraphBlock get() = this as? GraphBlock
    val asFeedLike get() = this as? FeedLike
    val asFeedPost get() = this as? FeedPost
    val asFeedRepost get() = this as? FeedRepost
    val asGraphListItem get() = this as? GraphListItem
}
