package ge.tsu.project.simple_chat_app

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class MessagesAdapter (private val messages: ArrayList<MessageView>, private val currentUser: String): RecyclerView.Adapter<MessagesAdapter.MyViewHolder>(){

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

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentMessage = messages[position]

        val sdf = SimpleDateFormat("HH:mm")
        val dateString: String = sdf.format(Timestamp.valueOf(currentMessage.timestamp))
        
        holder.content.text = currentMessage.text
        holder.user.text = currentMessage.user?.username
        holder.time.text = dateString
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
        var content : TextView = itemView.findViewById(R.id.msgView)
        var time : TextView = itemView.findViewById(R.id.msgTime)
        var user : TextView = itemView.findViewById(R.id.msgUser)

    }
}

private fun Calendar.setTimeInMillis(valueOf: Timestamp?) {

}
