package com.example.lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab3.ui.theme.Lab3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TodoApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TodoApp(modifier: Modifier = Modifier) {
    var taskText by remember { mutableStateOf(TextFieldValue("")) }
    val taskList = remember { mutableStateListOf<String>() }

    Column(modifier = modifier.padding(16.dp)) {
        // Поле введення завдання
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            TextField(
                value = taskText,
                onValueChange = { taskText = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Введіть завдання") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (taskText.text.isNotBlank()) {
                    taskList.add(taskText.text)
                    taskText = TextFieldValue("")
                }
            }) {
                Text("Додати")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Список завдань
        LazyColumn {
            items(taskList.size) { index ->
                Text(
                    text = "${index + 1}. ${taskList[index]}",
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoAppPreview() {
    Lab3Theme {
        TodoApp()
    }
}
