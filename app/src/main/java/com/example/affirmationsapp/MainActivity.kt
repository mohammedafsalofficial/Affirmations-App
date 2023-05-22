package com.example.affirmationsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmationsapp.data.DataSource
import com.example.affirmationsapp.model.Affirmation
import com.example.affirmationsapp.ui.theme.AffirmationsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationsApp()
                }
            }
        }
    }
}

@Composable
fun AffirmationsApp() {
    AffirmationList(affirmationList = DataSource().loadAffirmations())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AffirmationsCard(modifier: Modifier = Modifier, affirmation: Affirmation) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = modifier) {
            Image(
                painter = painterResource(id = affirmation.imageResourceId), 
                contentDescription = stringResource(id = affirmation.stringResourceId),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp)
            )
            Text(
                text = LocalContext.current.getString(affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Composable
fun AffirmationList(modifier: Modifier = Modifier, affirmationList: List<Affirmation>) {
    LazyColumn {
        items(affirmationList) {affirmation ->
            AffirmationsCard(affirmation = affirmation)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AffirmationsCardPreview() {
    AffirmationsCard(affirmation = Affirmation(R.string.affirmation1, R.drawable.image1))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AffirmationsAppTheme {
        AffirmationsApp()
    }
}
