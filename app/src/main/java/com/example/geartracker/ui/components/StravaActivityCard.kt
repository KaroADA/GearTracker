package com.example.geartracker.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.geartracker.R
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import com.example.geartracker.data.model.StravaActivity

@RequiresApi(Build.VERSION_CODES.O)
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
                Spacer(modifier = Modifier.weight(1f))
                Row(modifier = Modifier.fillMaxWidth()) {

                    var odt = OffsetDateTime.parse(stravaActivity.start_date);
                    var dtf = DateTimeFormatter.ofPattern("MMM dd, uuuu 'at' h:mm a", Locale.ENGLISH);

                    Text(
                        text = dtf.format(odt),
                        fontWeight = FontWeight.Light,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Icon(painter = painterResource(id = R.drawable.play_arrow_24px),
                        contentDescription = null,
                        Modifier
                            .size(
                                with(LocalDensity.current) {
                                    24.sp.toDp()
                                }
                            )
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}
