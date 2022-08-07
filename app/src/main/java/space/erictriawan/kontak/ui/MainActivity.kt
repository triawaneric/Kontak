package space.erictriawan.kontak.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import space.erictriawan.kontak.R
import space.erictriawan.kontak.model.Kontak
import space.erictriawan.kontak.utils.getJsonDataFromAsset

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonFileString = getJsonDataFromAsset(applicationContext, "data.json")
        //Log.i("data", jsonFileString.toString())
        val gson = Gson()
        val listPersonType = object : TypeToken<List<Kontak>>() {}.type
        var persons: List<Kontak> = gson.fromJson(jsonFileString, listPersonType)
        //persons.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }



//        // use arrayadapter and define an array
        val arrayAdapter: ArrayAdapter<*>


        // access the listView from xml file
        var mListView = findViewById<ListView>(R.id.userlist)
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, persons)
        mListView.adapter = arrayAdapter
    }
}