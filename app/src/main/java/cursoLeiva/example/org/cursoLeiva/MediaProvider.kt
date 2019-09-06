package cursoLeiva.example.org.cursoLeiva

object MediaProvider {

    private const val thumbBase = "https://lorempixel.com/400/400/cats/"

    val data = (1..10).map {
        MediaItem(
            "Title $it",
            "$thumbBase$it",
            if (it % 2 == 0 && it != 4) MediaItem.Type.VIDEO else MediaItem.Type.PHOTO
        )
    }
}