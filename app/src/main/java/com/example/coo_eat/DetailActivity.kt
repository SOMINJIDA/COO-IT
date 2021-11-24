package com.example.coo_eat

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var check_btn = false
        val scrap_btn = findViewById<ImageButton>(R.id.imageView5) as ImageButton

        scrap_btn.setOnClickListener(View.OnClickListener {
            if (!check_btn) {
                scrap_btn.setSelected(true)
                check_btn = true
            }
            else {
                check_btn = false
                scrap_btn.setSelected(false)
            }
        })



//        val scrap_btn = ImageButton(this)
//        scrap_btn.setOnClickListener {
//            if
//        }
//        scrap_btn.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                scrap_btn.setSelected(true);
//            }
//        });


    }
}