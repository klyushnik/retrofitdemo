package studio.sanguine.retrofitdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MyAdapter (val data: ArrayList<Book>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleText.setText(data.get(position).title)
        holder.subtitleText.setText(data.get(position).subtitle)
        holder.authorText.setText(data.get(position).author)
        holder.publisherText.setText(data.get(position).publisher)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
    var titleText: TextView = view.findViewById(R.id.title_textView)
    var subtitleText: TextView = view.findViewById(R.id.subtitle_textview)
    var authorText: TextView = view.findViewById(R.id.author_textView)
    var publisherText: TextView = view.findViewById(R.id.publisher_textview)
}