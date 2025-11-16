package com.example.geartracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.geartracker.ui.theme.GearTrackerTheme
import java.math.BigDecimal
import java.math.RoundingMode

val example = StravaActivity(
    id = 12345678987654321,
    name = "Lunch Ride",
    distance = 28099.0,
    moving_time = 4250,
    elapsed_time = 4410,
    total_elevation_gain = 516.0,
    type = "Ride",
    start_date = "2018-02-16T14:52:54Z"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            GearTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    LazyColumn(contentPadding = innerPadding, modifier = Modifier.fillMaxSize()) {
                        items(100){
                            StravaActivityCard(example)
                        }
                    }


                }
            }
        }
    }
}

data class StravaActivity(
    val id: Long,
    val name: String,
    val distance: Double,
    val moving_time: Int,
    val elapsed_time: Int,
    val total_elevation_gain: Double,
    val type: String,
    val start_date: String
)

@Composable
fun StravaActivityCard(stravaActivity: StravaActivity, modifier: Modifier = Modifier) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .height(100.dp)
    )  {
        Row(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier
                .width(16.dp)
                .fillMaxHeight()
            )

            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = stravaActivity.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = BigDecimal(stravaActivity.distance / 1000).setScale(2, RoundingMode.HALF_EVEN).toString() + " km • "
                            + stravaActivity.total_elevation_gain + " m • "
                            + ( if ( stravaActivity.moving_time / 3600 > 0) (stravaActivity.moving_time / 3600).toString() + "h " else "" )
                            + ( stravaActivity.moving_time / 60 ) % 60 + "m",
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp
                )
            }


        }


    }
}

@Preview(showBackground = true)
@Composable
fun StravaActivityCard() {
    GearTrackerTheme {
        StravaActivityCard(example)
    }
}