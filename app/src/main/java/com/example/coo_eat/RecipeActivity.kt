package com.example.coo_eat

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recipe.*

val uri1 : Uri = Uri.parse("android.resource://com.example.coo_eat/drawable/soup")
val uri2 : Uri = Uri.parse("android.resource://com.example.coo_eat/drawable/noodle")
val uri3 : Uri = Uri.parse("android.resource://com.example.coo_eat/drawable/maratang")

var ItemList = arrayListOf<recipeData>(
    recipeData("된장찌개", "#국/찌개", uri1),
    recipeData("라면", "#국/찌개", uri2),
    recipeData("마라탕", "#국/찌개", uri3),
)

class RecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val mAdapter = recipeAdapter(this, ItemList)
        recycler_view.adapter = mAdapter

        val layout = LinearLayoutManager(this)
        recycler_view.layoutManager = layout
        recycler_view.setHasFixedSize(true)
    }
}