package com.example.ejemploroom.addtasks.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejemploroom.addtasks.domain.AddTaskUseCase
import com.example.ejemploroom.addtasks.domain.GetTasksUseCase
import com.example.ejemploroom.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.ejemploroom.addtasks.ui.TaskUiState.*
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    getTasksUseCase: GetTasksUseCase
) : ViewModel() {
    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _myTaskText = MutableLiveData<String>()
    val myTaskText: LiveData<String> = _myTaskText

    val uiState: StateFlow<TaskUiState> =
        getTasksUseCase()
            .map(::Success)
            .catch { Error(it) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000), Loading
            )

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTaskCreated() {
        onDialogClose()
        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = _myTaskText.value ?: ""))
        }
        _myTaskText.value = ""
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onTaskTextChanged(taskText: String) {
        _myTaskText.value = taskText
    }

    fun onItemRemove(taskModel: TaskModel) {

    }

    fun onCheckBoxSelected(taskModel: TaskModel) {

    }

}