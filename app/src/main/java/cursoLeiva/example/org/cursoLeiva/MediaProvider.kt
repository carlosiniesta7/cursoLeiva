package cursoLeiva.example.org.cursoLeiva

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

object MediaProvider {

    private const val thumbBase = "https://lorempixel.com/400/400/cats/"

    private var data = emptyList<MediaItem>()

    fun dataAsync(callback: (List<MediaItem>) -> Unit) {
        doAsync {
            if(data.isEmpty()) {
                Thread.sleep(2000)
                data = (1..10).map {
                    MediaItem(
                        it,
                        "Title $it",
                        "$thumbBase$it",
                        if (it % 2 == 0 && it != 4) MediaItem.Type.VIDEO else MediaItem.Type.PHOTO
                    )
                }
            }
            uiThread {
                callback(data)
            }
        }
    }
}