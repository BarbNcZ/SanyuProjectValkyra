package com.androidestudos.fiapchallange.ui.view.pages.login

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.androidestudos.fiapchallange.ui.Route
import com.androidestudos.fiapchallange.ui.models.LoginEvents
import com.androidestudos.fiapchallange.ui.viewmodel.LoginViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginContainer(
    viewModel: LoginViewModel = koinViewModel(),
    navHostController: NavHostController,
    isManager: MutableState<Boolean>,
){

    val state = viewModel.state.collectAsStateWithLifecycle()

    val lifeCycleOwner = LocalLifecycleOwner.current

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    DisposableEffect(lifeCycleOwner) {

        val observer = LifecycleEventObserver{ _, event ->
            if( event == Lifecycle.Event.ON_CREATE ){

                scope.launch {
                    viewModel.event.collect{ event ->
                        when(event){
                            is LoginEvents.LoginSuccessfully -> {

                                isManager.value = event.isManager

                                if (event.isManager) {
                                    navHostController.navigate(
                                        Route.MENU.routeId
                                    )
                                } else {
                                    navHostController.navigate(
                                        "${Route.TASKS.routeId}/${event.cdFuncionario}"
                                    )
                                }
                            }
                            LoginEvents.LoginFailed -> Toast.makeText(context, "Falha ao fazer Login", Toast.LENGTH_LONG).show()
                            else -> Unit
                        }
                    }
                }
            }
        }

        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LoginScreen(
        viewModel::login,
    )
}