package cursoLeiva.example.org.cursoLeiva

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //private val recyclerView by lazy { recycler as RecyclerView}
    val adapter = MediaAdapter(MediaProvider.data) { mediaItem -> toast(mediaItem.title) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.adapter = adapter
        //adapter.items = getMedia()
    }

    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.layout.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        adapter.items = MediaProvider.data.let { media ->
            when(item.itemId) {
                R.id.filter_all -> media
                R.id.filter_photos -> media.filter { it.type == MediaItem.Type.PHOTO }
                R.id.filter_videos -> media.filter { it.type == MediaItem.Type.VIDEO }
                else -> emptyList()
            }
        }
        return true
    }
}
