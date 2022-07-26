package ge.tsu.project.simple_chat_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.sql.Timestamp
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button?>(R.id.loginBtn).setOnClickListener{
            val chatActivity = Intent(this, ChatActivity::class.java)
            chatActivity.putExtra("username", findViewById<TextView>(R.id.username).text.toString())
            startActivity(chatActivity)
        }
    }


}