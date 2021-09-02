package live.adabe.findmyblood.ui.fragments.ktx

import android.content.ContentResolver
import android.net.Uri
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.IOException
import okio.source

class InputStreamRequestBody(
    private val contentType: MediaType?,
    private val contentResolver: ContentResolver,
    private val uri: Uri
) : RequestBody() {
    override fun contentType() = contentType

    override fun contentLength(): Long = -1

    @Throws(IOException::class)
    override fun writeTo(sink: BufferedSink) {
        val input = contentResolver.openInputStream(uri)

        input?.use { sink.writeAll(it.source()) }
            ?: throw IOException("Could not open $uri")
    }
}