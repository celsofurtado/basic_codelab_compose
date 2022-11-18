package br.pro.celsofurtado.basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.pro.celsofurtado.basicscodelab.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                MyApp()
            }
        }
    }
}

@Composable
private fun MyApp(modifier: Modifier = Modifier) {

    var shouldShowOnBoarding by remember {
        mutableStateOf(true)
    }

    Surface(modifier) {
        if (shouldShowOnBoarding) {
            OnBoardingScreen(onContinueClicked = { shouldShowOnBoarding = false })
        } else {
            Greetings()
        }
    }

}

@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("World", "Compose", "Android", "Google")
) {

    Column(modifier = modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }

}

@Composable
fun Greeting(name: String) {

    val expanded = remember { mutableStateOf(false) }
    val extraExpanding = if (expanded.value) 64.dp else 0.dp

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .padding(bottom = extraExpanding)

            ) {
                Text(
                    text = "Hello,",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    //color = Color.Yellow
                )
                Text(
                    text = "$name!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    //color = Color.Yellow
                )
            }

            //Text(text = "Teste", modifier = Modifier.weight(0.2f).background(Color.Blue))

            ElevatedButton(onClick = {
                expanded.value = !expanded.value
            }, modifier = Modifier.weight(0.4f)) {
                Text(
                    if (expanded.value) "Show less" else "Show more"
                )
            }

        }
    }
}

@Composable
fun OnBoardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text(
                text = "Continue",
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingsPreview() {
    BasicsCodelabTheme {

        Greetings()

    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    BasicsCodelabTheme {

        OnBoardingScreen(onContinueClicked = {})

    }
}

@Preview
@Composable
fun MyAppPreview() {
    BasicsCodelabTheme {
        MyApp(Modifier.fillMaxSize())
    }
}