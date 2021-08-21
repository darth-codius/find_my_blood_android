package live.adabe.findmyblood.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import live.adabe.findmyblood.MainActivity
import live.adabe.findmyblood.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            with(
                Intent(this, MainActivity::class.java)
            ) {
                startActivity(this)
                finish()
            }
        }, 2000)
    }
}