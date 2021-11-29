package com.example.coo_eat

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.category2.*
import kotlinx.android.synthetic.main.category_1.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import javax.xml.parsers.DocumentBuilderFactory

class Category2Activity : AppCompatActivity() {
    val db = Firebase.firestore
    val TAG: String = "Category2 : " //log출력을 위한 TAG

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category2)

        CoroutineScope(Dispatchers.Main).launch {
            val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
            val user_email = pref.getString("email", "no email")
            val user_ingredients = db.collection("${user_email}").document("ingredient")
            var items = mutableListOf<String>()
            var foodNames = mutableListOf<String>()
            var foodImages = mutableListOf<String>()
            var foodCategories = mutableListOf<String>()

            // 재료 배열로 받아옴
            user_ingredients.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val item =
                            document["my"].toString().replace("[", "").replace("]", "").split(", ")
                        for (i in 0..item.size - 1) {
                            items.add(item[i])
                        }
                    }
                }

            // 사용자가 선택한 재료가 포함된 레시피 정보를 API에서 추출
            CoroutineScope(Dispatchers.IO).async {
                val key: String = "cf8505a99bb545f8882c"
                val url: String = "http://openapi.foodsafetykorea.go.kr/api/" + key + "/COOKRCP01/xml/1/1000"
                val xml: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url)

                xml.documentElement.normalize()
                println("Root element : " + xml.documentElement.nodeName)

                val list: NodeList = xml.getElementsByTagName("row")

                for (i in 0..list.length - 1) {
                    var n: Node = list.item(i)
                    if (n.getNodeType() == Node.ELEMENT_NODE) {
                        val elem = n as Element
                        val map = mutableMapOf<String, String>()

                        for (j in 0..elem.attributes.length - 1) {
                            map.putIfAbsent(
                                elem.attributes.item(j).nodeName,
                                elem.attributes.item(j).nodeValue
                            )
                        }
                        val ingredients = elem.getElementsByTagName("RCP_PARTS_DTLS").item(0).textContent
                        val ingredientsArray = ingredients.split(" ")

                        val equal = ingredientsArray.intersect(items.toList())
                        val category = elem.getElementsByTagName("RCP_PAT2").item(0).textContent
                        if ((equal.size >= 2) and (category == "밥")) {
                            Log.d(TAG, "추천 레시피: ${elem.getElementsByTagName("RCP_NM").item(0).textContent}")
                            foodNames.add(elem.getElementsByTagName("RCP_NM").item(0).textContent)
                            foodImages.add(elem.getElementsByTagName("ATT_FILE_NO_MAIN").item(0).textContent)
                            //foodCategories.add(elem.getElementsByTagName("RCP_PAT2").item(0).textContent)
                        }
                    }
                }
            }.await()

            val Category2Layout: LinearLayout = findViewById(R.id.category2_main)

            for (i in 0..foodNames.size-1) {
                val foodImage = ImageView(this@Category2Activity)
                val foodName = TextView(this@Category2Activity)
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )

                val imageLayoutParams = LinearLayout.LayoutParams(
                    900,
                    350,
                )

                imageLayoutParams.gravity = Gravity.CENTER

                layoutParams.setMargins(80,10,0,50)

                foodImage.layoutParams = imageLayoutParams
                foodName.layoutParams = layoutParams

                foodImage.setScaleType(ImageView.ScaleType.FIT_XY)


                Glide.with(this@Category2Activity).load(foodImages[i]).into(foodImage)

                foodName.setText(foodNames[i])
                foodName.setTextSize(Dimension.SP,15.0f)
                foodName.setTypeface(foodName.typeface, Typeface.BOLD)
                foodName.setTextColor(Color.BLACK)

                Category2Layout.addView(foodImage)
                Category2Layout.addView(foodName)
            }
        }
        // 뒤로가기 버튼 클릭
        btn_category2_back.setOnClickListener {
            val intent = Intent(this, RecipeActivity::class.java)
            startActivity(intent)
        }
        // 카테고리 이동
        btn_category2_soup.setOnClickListener {
            val intent = Intent(this, Category1Activity::class.java)
            startActivity(intent)
        }
        btn_category2_vegan.setOnClickListener {
            val intent = Intent(this, Category3Activity::class.java)
            startActivity(intent)
        }
        btn_category2_snack.setOnClickListener {
            val intent = Intent(this, Category4Activity::class.java)
            startActivity(intent)
        }
    }
}