package com.example.geartracker

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.geartracker.ui.theme.GearTrackerTheme
import com.example.geartracker.ui.components.StravaActivityCard
import com.example.geartracker.data.model.StravaActivity
import com.example.geartracker.ui.App
import com.example.geartracker.data.api.startStravaAuth



class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        handleIntent(intent)

        setContent {
            GearTrackerTheme {
                App(
                    onLoginClick = {
                        Toast.makeText(this, "Login to strava", Toast.LENGTH_SHORT).show()
                        startStravaAuth(this)
                    }
                )
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        val data: Uri? = intent.data

        if (data != null && data.scheme == "geartracker" && data.host == "geartracker.karoada.ovh") {

            val error = data.getQueryParameter("error")
            if (error != null) {
                Log.i("StravaAuth", "Login cancelled: $error")
                return
            }

            val code = data.getQueryParameter("code")
            if (code != null) {
                Log.i("StravaAuth", "Code: $code")
                // exchangeCodeForToken(code)
            }
        }
    }
}
