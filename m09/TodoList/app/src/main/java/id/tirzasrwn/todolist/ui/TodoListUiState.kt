package id.tirzasrwn.todolist.ui

// type TodoListUiState that hold total, finished, and unfinished todos
data class TodoListUiState (
    val total: Int = 0,
    val finished: Int = 0,
    val unfinished: Int = 0,
)