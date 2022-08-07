package space.erictriawan.kontak.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.contains
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import space.erictriawan.kontak.R
import space.erictriawan.kontak.model.Kontak
import space.erictriawan.kontak.utils.CustomAdapter
import space.erictriawan.kontak.utils.getJsonDataFromAsset

class MainActivity : AppCompatActivity() {
    var personName: ArrayList<String> = ArrayList()
    var emailId: ArrayList<String> = ArrayList()
    var mobileNumbers: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val searchView = findViewById<SearchView>(R.id.searchView)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager


        try {
//            val obj = JSONObject(loadJSONFromAsset())


            val jsonFileString = getJsonDataFromAsset(applicationContext, "data.json")

            val gson = Gson()
            var listType = object:TypeToken<List<Kontak>>() {}.type
            var myModelList:List<Kontak> = gson.fromJson(jsonFileString, listType)

            Log.e("print ada :",myModelList.toString())
//            val userArray = obj.getJSONArray(o)
            for (i in 0 until myModelList.size) {
                personName.add(myModelList[i].fName.toString())
                emailId.add(myModelList[i].email.toString())
                mobileNumbers.add(myModelList[i].phone.toString())
            }
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }
        val customAdapter = CustomAdapter(this@MainActivity, personName)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                customAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                customAdapter.filter.filter(newText)
                return false
            }

        })
        recyclerView.adapter = customAdapter





        // val jsonFileString = getJsonDataFromAsset(applicationContext, "data.json")

    }




}