package com.androidestudos.fiapchallange.ui.view.atoms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.androidestudos.fiapchallange.data.GetFuncionarioResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeesDropDownMenu(
    selectedValue: MutableState<String>,
    options: List<GetFuncionarioResult>,
    label: String,
    onValueChangedEvent: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedValue.value,
            onValueChange = {},
            label = { Text(text = label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = OutlinedTextFieldDefaults.colors(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { employee: GetFuncionarioResult ->
                DropdownMenuItem(
                    text = { Text(text = "${employee.nmFuncionario} (${employee.dsCargo} - ${employee.nmDepto})") },
                    onClick = {
                        expanded = false
                        selectedValue.value = "${employee.nmFuncionario} (${employee.dsCargo} - ${employee.nmDepto})"
                        onValueChangedEvent(employee.cdFuncionario)
                    }
                )
            }
        }
    }
}