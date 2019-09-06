package cursoLeiva.example.org.cursoLeiva

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    //private val recyclerView by lazy { recycler as RecyclerView}
    val adapter = MediaAdapter { navigateToDetail(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.adapter = adapter
        MediaProvider.dataAsync { adapter.items = it }
        //adapter.items = getMedia()
    }

    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.layout.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

         MediaProvider.dataAsync {  media ->
             adapter.items =  when(item.itemId) {
                R.id.filter_all -> media
                R.id.filter_photos -> media.filter { it.type == MediaItem.Type.PHOTO }
                R.id.filter_videos -> media.filter { it.type == MediaItem.Type.VIDEO }
                else -> emptyList()
            }
        }
        return true
    }

    private fun navigateToDetail(it: MediaItem) {
        //Gracias a Anko
        startActivity<DetailActivity>(DetailActivity.ID to it.id)
    }
}
