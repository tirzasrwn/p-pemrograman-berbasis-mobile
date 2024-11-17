package id.tirzasrwn.todolist.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import id.tirzasrwn.todolist.data.Datasource
import id.tirzasrwn.todolist.model.Todo

class TodoListViewModel : ViewModel() {
    private val _toDoList = getAllTodoList().toMutableStateList()
    val toDoList: List<Todo>
        get() = _toDoList

    private val _uiState = mutableStateOf(
        TodoListUiState(
            total = _toDoList.size,
            finished = _toDoList.count { it.isCompleted.value },
            unfinished = _toDoList.count { !it.isCompleted.value }
        )
    )
    val uiState: TodoListUiState
        get() = _uiState.value

    fun onTaskChecked(toDoItem: Todo, isChecked: Boolean) {
        _toDoList.find { it.taskName == toDoItem.taskName }
            ?.let { it.isCompleted.value = isChecked }
        updateUiState()
    }

    fun addTodoItem(newTaskName: String) {
        if (newTaskName.isNotEmpty() && _toDoList.none { it.taskName == newTaskName }) {
            _toDoList.add(Todo(taskName = newTaskName))
            updateUiState()
        }
    }

    fun removeTodoItem(toDo: Todo) {
        _toDoList.remove(toDo)
        updateUiState()
    }

    private fun updateUiState() {
        _uiState.value = TodoListUiState(
            total = _toDoList.size,
            finished = _toDoList.count { it.isCompleted.value },
            unfinished = _toDoList.count { !it.isCompleted.value }
        )
    }

    private fun getAllTodoList(): List<Todo> {
        return Datasource().loadMoods()
    }
}
