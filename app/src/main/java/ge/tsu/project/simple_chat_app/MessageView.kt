package ge.tsu.project.simple_chat_app

import java.sql.Timestamp
import java.util.*

data class MessageView(val user: User ?= null, val text: String ?= null)
