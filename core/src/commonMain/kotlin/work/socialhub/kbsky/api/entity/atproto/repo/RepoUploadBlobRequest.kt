package work.socialhub.kbsky.api.entity.atproto.repo

import work.socialhub.kbsky.api.entity.share.AuthRequest

class RepoUploadBlobRequest(
    accessJwt: String,
    var bytes: ByteArray,
    var name: String = "data",
) : AuthRequest(accessJwt)
