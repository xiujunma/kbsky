package work.socialhub.kbsky.util.json

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import work.socialhub.kbsky.model.app.bsky.actor.ActorDefsAdultContentPref
import work.socialhub.kbsky.model.app.bsky.actor.ActorDefsContentLabelPref
import work.socialhub.kbsky.model.app.bsky.actor.ActorDefsFeedViewPref
import work.socialhub.kbsky.model.app.bsky.actor.ActorDefsLabelersPref
import work.socialhub.kbsky.model.app.bsky.actor.ActorDefsPersonalDetailsPref
import work.socialhub.kbsky.model.app.bsky.actor.ActorDefsPreferencesUnion
import work.socialhub.kbsky.model.app.bsky.actor.ActorDefsSavedFeedsPref
import work.socialhub.kbsky.model.app.bsky.actor.ActorDefsSavedFeedsPrefV2
import work.socialhub.kbsky.model.app.bsky.actor.ActorDefsThreadViewPref
import work.socialhub.kbsky.util.json.JsonElementUtil.type

object ActorDefsPreferencesPolymorphicSerializer :
    JsonContentPolymorphicSerializer<ActorDefsPreferencesUnion>(
        ActorDefsPreferencesUnion::class
    ) {

    override fun selectDeserializer(
        element: JsonElement
    ): DeserializationStrategy<ActorDefsPreferencesUnion> {
        return when (val type = element.type()) {
            ActorDefsPersonalDetailsPref.TYPE -> ActorDefsPersonalDetailsPref.serializer()
            ActorDefsAdultContentPref.TYPE -> ActorDefsAdultContentPref.serializer()
            ActorDefsContentLabelPref.TYPE -> ActorDefsContentLabelPref.serializer()
            ActorDefsSavedFeedsPref.TYPE -> ActorDefsSavedFeedsPref.serializer()
            ActorDefsSavedFeedsPrefV2.TYPE -> ActorDefsSavedFeedsPrefV2.serializer()
            ActorDefsFeedViewPref.TYPE -> ActorDefsFeedViewPref.serializer()
            ActorDefsThreadViewPref.TYPE -> ActorDefsThreadViewPref.serializer()
            ActorDefsLabelersPref.TYPE -> ActorDefsLabelersPref.serializer()
            else -> {
                println("[Warning] Unknown Item type: $type (ActorDefsPreferencesUnion)")
                Unknown.serializer()
            }
        }
    }

    @Serializable
    class Unknown : ActorDefsPreferencesUnion() {
        override var type: String = "unknown"
    }
}