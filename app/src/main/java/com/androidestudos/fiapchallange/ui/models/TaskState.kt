package com.androidestudos.fiapchallange.ui.models

import com.androidestudos.fiapchallange.data.GetTarefaResult

data class TaskState(
    val tarefa: GetTarefaResult = GetTarefaResult(-1, "", "", 0, 0, 0)
)