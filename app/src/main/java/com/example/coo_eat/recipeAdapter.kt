package com.example.coo_eat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

var mPosition = 0
class recipeAdapter(private val context: Context, private val ItemList: ArrayList<recipeData>):RecyclerView.Adapter<recipeAdapter.ItemViewHolder>() {

    fun setPosition(position: Int) {
        mPosition = position
    }

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val foodPhoto = itemView.findViewById<ImageView>(R.id.foodImg)
        private val foodName = itemView.findViewById<TextView>(R.id.foodName)
        private val foodCategory = itemView.findViewById<TextView>(R.id.foodCategory)

        fun bind(data: recipeData) {
            //View에 데이터 세팅
            foodPhoto.setImageURI(data.photo)
            foodName.text = data.name
            foodCategory.text = data.category
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recipe_recyclerview, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(ItemList[position])
        holder.itemView.setOnClickListener {view -> setPosition(position)
        }
    }

    override fun getItemCount(): Int {
        return ItemList.size
    }
}

