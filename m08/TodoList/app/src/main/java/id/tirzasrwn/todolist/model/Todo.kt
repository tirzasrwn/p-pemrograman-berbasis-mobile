package id.tirzasrwn.todolist.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Todo(
    var taskName: String,
    var isCompleted: MutableState<Boolean> = mutableStateOf(false)
)
