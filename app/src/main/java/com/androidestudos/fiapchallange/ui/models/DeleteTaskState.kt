package com.androidestudos.fiapchallange.ui.models

import com.androidestudos.fiapchallange.data.GetFuncionarioResult
import com.androidestudos.fiapchallange.data.GetTarefasResult
import com.androidestudos.fiapchallange.data.GetTipoTarefaResult

data class DeleteTaskState(
    val tasks: List<GetTarefasResult> = emptyList(),
)