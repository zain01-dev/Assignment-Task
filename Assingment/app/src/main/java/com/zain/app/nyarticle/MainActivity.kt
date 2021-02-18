package com.zain.app.nyarticle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zain.app.nyarticle.view.ui.ArticleListActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val intent = Intent(this, ArticleListActivity::class.java)
        startActivity(intent)
        finish()

    }

}
