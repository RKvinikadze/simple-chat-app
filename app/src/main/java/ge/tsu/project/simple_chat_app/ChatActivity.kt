package ge.tsu.project.simple_chat_app

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.sql.Timestamp
import java.util.*
import kotlin.collections.ArrayList

class ChatActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var messageArrayList: ArrayList<MessageView>
    private lateinit var messageRecyclerView: RecyclerView
    private lateinit var currentUser: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        messageArrayList = arrayListOf<MessageView>()
        messageRecyclerView = findViewById(R.id.recycler_view)
        messageRecyclerView.layoutManager = LinearLayoutManager(this)

        currentUser = if (intent.getStringExtra("username") == null) "" else intent.getStringExtra("username")!!

        findViewById<Button?>(R.id.send_message).setOnClickListener{
            val msg = findViewById<EditText>(R.id.edit_message)
            sendMessage(it, msg.text.toString());
            msg.text = null
        }

        getMessages()

        print("++++++++++++++++++++++++ " + currentUser)

        title = currentUser
    }

    private fun getMessages(){
        dbRef = FirebaseDatabase.getInstance().getReference("message")
        val query = dbRef.orderByChild("timestamp")

        query.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                messageArrayList.clear()

                if (snapshot.exists()){
                    for (msgSnapshot in snapshot.children){
                        val message = msgSnapshot.getValue(MessageView::class.java)
                        messageArrayList.add(message!!)
                    }

                    messageRecyclerView.adapter = MessagesAdapter(messageArrayList, currentUser)
                }
                messageRecyclerView.layoutManager?.scrollToPosition(messageArrayList.size - 1)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun sendMessage(view: View, text: String){
        val dbRefMsg: DatabaseReference = FirebaseDatabase.getInstance().reference

        val user = User(userId = UUID.randomUUID().toString(), username = currentUser)
        val message = Message(user, UUID.randomUUID().toString(), text, Timestamp(System.currentTimeMillis()).toString())

        dbRefMsg.child("message").child(message.messageId.toString()).setValue(message)

    }
}