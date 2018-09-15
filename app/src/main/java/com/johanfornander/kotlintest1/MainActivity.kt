package com.johanfornander.kotlintest1

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.iid.FirebaseInstanceId

private var TAG = "firebase"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.main_listView)
        val redColor = Color.parseColor("#FF0000")
        listView.setBackgroundColor(redColor)

        listView.adapter = CustomAdapter(this)


        getFirebaseDeviceToken()
    }


    private fun getFirebaseDeviceToken() {
        try {
            val refreshedToken = FirebaseInstanceId.getInstance().token
            Log.d(TAG, "Refreshed token: " + refreshedToken!!)
        } catch (e: Exception) {
            Log.d(TAG, "getFirebaseDeviceToken failed")
            e.printStackTrace()
        }
    }

    private class CustomAdapter(context: Context): BaseAdapter() {

        private val mContext: Context

        private val names = arrayListOf<String>(
                "Donald trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama"
        )

        init {
            mContext = context
        }

        override fun getCount(): Int {
            return names.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "test string"
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.row_main, parent, false)
            val nameTextView = rowMain.findViewById<TextView>(R.id.name_textView)
            nameTextView.text = names.get(position)

            val positionTextView = rowMain.findViewById<TextView>(R.id.position_textview)
            positionTextView.text = "Row number: $position"

            return rowMain

            //val textView = TextView(mContext)
            //textView.text = "Here is my Row for LISTVIEW"
            //return textView
        }


    }


}
