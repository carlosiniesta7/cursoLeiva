package cursoLeiva.example.org.cursoLeiva

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_media_item.view.*
import kotlin.properties.Delegates

class MediaAdapter(items: List<MediaItem> = emptyList(), val listener: (MediaItem) -> Unit) : RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    var items: List<MediaItem> by Delegates.observable(items) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = parent.inflate(R.layout.view_media_item)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener{ listener(items[position]) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: MediaItem) = with(itemView) {
                media_title.text = item.title
                //media_thumb.loadUrl(item.thumbUrl)
                Picasso.with(media_thumb.context).load(item.thumbUrl).into(media_thumb)
                media_video_indicator.visibility = when (item.type) {
                    MediaItem.Type.PHOTO -> View.GONE
                    MediaItem.Type.VIDEO -> View.VISIBLE
                }
        }
    }

}