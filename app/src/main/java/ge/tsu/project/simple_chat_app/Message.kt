package ge.tsu.project.simple_chat_app

import java.sql.Timestamp
import java.util.*

data class Message(val user: User ?= null, val messageId: String ?= null, val text: String ?= null, val timestamp: String ?= null)
