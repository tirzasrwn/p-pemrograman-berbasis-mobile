package id.tirzasrwn.todolist.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import id.tirzasrwn.todolist.data.Datasource
import id.tirzasrwn.todolist.model.Todo

// viewmodel class to get and set data layer
class TodoListViewModel : ViewModel() {
    // local data layer using variable
    // _toDoList holds all todos we have
    private val _toDoList = getAllTodoList().toMutableStateList()
    // read-only variable that can be read outside this class especially ui layer
    val toDoList: List<Todo> get() = _toDoList

    // local data layer using variable
    // _uiState holds information about the number of total, finished,
    // and unfinished todos
    private val _uiState = mutableStateOf(
        TodoListUiState(
            total = _toDoList.size,
            finished = _toDoList.count { it.isCompleted.value },
            unfinished = _toDoList.count { !it.isCompleted.value }
        )
    )
    // read-only variable that can be read outside this class especially ui layer
    val uiState: TodoListUiState get() = _uiState.value

    // handle task check/uncheck
    fun onTaskChecked(toDoItem: Todo, isChecked: Boolean) {
        _toDoList.find { it.taskName == toDoItem.taskName }
            ?.let { it.isCompleted.value = isChecked }
        updateUiState() // update UI state after checking a task
    }

    // add a new todo item
    fun addTodoItem(newTaskName: String) {
        if (newTaskName.isNotEmpty() && _toDoList.none { it.taskName == newTaskName }) {
            _toDoList.add(Todo(taskName = newTaskName)) // add new task
            updateUiState() // update UI state after adding a task
        }
    }

    // remove a todo item
    fun removeTodoItem(toDo: Todo) {
        _toDoList.remove(toDo) // remove task from list
        updateUiState() // update UI state after removing a task
    }

    // recount total, finished, and unfinished todos
    private fun updateUiState() {
        _uiState.value = TodoListUiState(
            total = _toDoList.size,
            finished = _toDoList.count { it.isCompleted.value },
            unfinished = _toDoList.count { !it.isCompleted.value }
        )
    }

    // load initial todo list from datasource
    private fun getAllTodoList(): List<Todo> {
        return Datasource().loadTodos() // get todos from datasource
    }
}
