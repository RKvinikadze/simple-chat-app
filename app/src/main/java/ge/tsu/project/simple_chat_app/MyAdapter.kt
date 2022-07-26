package ge.tsu.project.simple_chat_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter (private val messages: ArrayList<MessageView>, private val currentUser: String): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return if (viewType == R.layout.message_from){
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.message_from, parent, false)
            MyViewHolder(itemView)
        }else {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.message_to, parent, false)
            MyViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentMessage = messages[position]
        
        holder.content.text = currentMessage.text
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentUser == messages[position].user?.username) {
            R.layout.message_from
        } else {
            R.layout.message_to
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var content : TextView = itemView.findViewById(R.id.message_view)

    }
}