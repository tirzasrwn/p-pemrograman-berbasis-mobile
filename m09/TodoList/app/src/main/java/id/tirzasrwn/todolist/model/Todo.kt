package id.tirzasrwn.todolist.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

// type todo that hold task name and is the task complete or not
data class Todo(
    var taskName: String,
    var isCompleted: MutableState<Boolean> = mutableStateOf(false)
)
