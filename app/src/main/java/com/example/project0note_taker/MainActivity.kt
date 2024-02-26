package com.example.project0note_taker

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.project0note_taker.ui.theme.Project0NoteTakerTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * One word -> Class -> Mango
         * One word -> Method -> mango
         * verb
         */
        if(Build.VERSION.SDK_INT <= 28){
            // Provide alternative
        }else{
            // Implement bottom navigation
        }
        setContent {
            Project0NoteTakerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0x00000000)
                ) {
                    Greeting("Android")
                }
            }
        }
    }



}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val coroutine = rememberCoroutineScope()
    var myText by remember{
        mutableStateOf("Hello")
    }
    // unconfined - whatever thread is available
    LaunchedEffect(key1 = Unit ){
        coroutine.launch(Dispatchers.Unconfined) {
            myText = "Goodbye"
        }
    }

    //context vs scope
    Surface(color = Color.Blue) {
        Text(
            text = "Hello $myText!",
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Project0NoteTakerTheme {
        Greeting("Android")
    }
}