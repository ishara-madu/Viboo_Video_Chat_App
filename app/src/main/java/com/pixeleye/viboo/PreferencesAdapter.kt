package com.pixeleye.viboo

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.URL


class PreferencesAdapter(private val preferences: List<Preferences>,private val selectedTitle: String,private val selectedBtn:Int) :
    RecyclerView.Adapter<PreferencesAdapter.PreferencesViewHolder>() {

    class PreferencesViewHolder(itemView: View,selectedBtn: Int) : RecyclerView.ViewHolder(itemView) {
        internal val btn: AppCompatButton = itemView.findViewById(R.id.cardBtn)
        init {
            btn.setOnClickListener {
                val intent = Intent(it.context, MainActivity::class.java)
                when(selectedBtn){
                    1 -> addToMap("lookingTitle",btn.text.toString())
                    2 -> addToMap("locationTitle",btn.text.toString())
                }
                it.context.startActivity(intent)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreferencesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.prefercnce_card, parent, false)
        return PreferencesViewHolder(view,selectedBtn)
    }

    override fun onBindViewHolder(holder: PreferencesViewHolder, position: Int) {
        val preferences = preferences[position]
        holder.btn.text = preferences.title
        CoroutineScope(Dispatchers.IO).launch {
            val drawable = loadImageFromUrl(preferences.image)
            withContext(Dispatchers.Main) {
                drawable?.let {
                    holder.btn.setCompoundDrawablesWithIntrinsicBounds(it, null, null, null)
                }
            }
            if(preferences.title == selectedTitle){
                holder.btn.backgroundTintList = holder.itemView.context.getColorStateList(R.color.holo_white_dark)
            }
        }
    }

    override fun getItemCount(): Int = preferences.size



    private fun loadImageFromUrl(url: String): Drawable? {
        return try {
            val inputStream: InputStream = URL(url).content as InputStream
            Drawable.createFromStream(inputStream, "src")
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
