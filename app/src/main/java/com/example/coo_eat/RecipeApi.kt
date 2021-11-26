package com.example.coo_eat

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory


@RequiresApi(Build.VERSION_CODES.N)
fun main() {
    val key : String = "cf8505a99bb545f8882c"
    val url : String = "http://openapi.foodsafetykorea.go.kr/api/" + key + "/COOKRCP01/xml/1/10"
    val xml : Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url)

    xml.documentElement.normalize()
    println("Root element : " + xml.documentElement.nodeName)

    val list : NodeList = xml.getElementsByTagName("row")
    println(list)

    for (i in 0..list.length - 1) {
        var n : Node = list.item(i)
        if (n.getNodeType() == Node.ELEMENT_NODE) {
            val elem = n as Element
            val map = mutableMapOf<String, String>()

            for (j in 0..elem.attributes.length - 1) {
                map.putIfAbsent(elem.attributes.item(j).nodeName, elem.attributes.item(j).nodeValue)
            }

            val ingredients = elem.getElementsByTagName("RCP_PARTS_DTLS").item(0).textContent
            val ingredientsArray = ingredients.split(" ")

            for (h in 0..ingredientsArray.size - 1) {
                if ("달걀" == ingredientsArray[h]) {
                    println("${elem.getElementsByTagName("RCP_NM").item(0).textContent}")
                }
            }
        }
    }
}