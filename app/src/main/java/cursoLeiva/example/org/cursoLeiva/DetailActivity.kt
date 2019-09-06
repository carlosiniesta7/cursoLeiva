package cursoLeiva.example.org.cursoLeiva

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        val ID = "DetailActivity:id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val id = intent.getIntExtra(ID, -1)

        MediaProvider.dataAsync { media ->
            //media.filter{ id == it.id}
            media.find { it.id == id }?.let {item ->
                supportActionBar?.title = item.title
                Picasso.with(detail_thumb.context).load(item.thumbUrl).into(detail_thumb)
                detail_video_indicator.visibility = when (item.type) {
                    MediaItem.Type.PHOTO -> View.GONE
                    MediaItem.Type.VIDEO -> View.VISIBLE
                }
            }
        }

    }
}
