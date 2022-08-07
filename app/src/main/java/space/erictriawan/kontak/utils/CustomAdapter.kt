package space.erictriawan.kontak.utils

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import space.erictriawan.kontak.R
import space.erictriawan.kontak.ui.DetailKontak
import java.util.*
class CustomAdapter(private var context: Context, private var personNames: ArrayList<String>) :
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>(), Filterable {

    var personFilterList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return MyViewHolder(v)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // set the data in items
        holder.name.text = personNames[position]
        holder.itemView.setOnClickListener { // display a toast with person name on item click
            val intent = Intent(context, DetailKontak::class.java)
            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return personNames.size
    }
    inner class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        var name: TextView = itemView.findViewById<View>(R.id.tvName) as TextView
//        var email: TextView = itemView.findViewById<View>(R.id.tvEmail) as TextView
//        var mobileNo: TextView = itemView.findViewById<View>(R.id.tvMobile) as TextView
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    personFilterList = personNames
                } else {
                    val resultList = ArrayList<String>()
                    for (row in personNames) {
                        if (row.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    personFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = personFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                personFilterList = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }

        }
    }
}