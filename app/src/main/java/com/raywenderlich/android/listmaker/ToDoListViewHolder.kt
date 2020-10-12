package com.raywenderlich.android.listmaker

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // Lesson 9 - Bind Data

    var listPositionTextView = itemView.findViewById<TextView>(R.id.itemNumber)
    var listTitleTextView = itemView.findViewById<TextView>(R.id.itemString)



}