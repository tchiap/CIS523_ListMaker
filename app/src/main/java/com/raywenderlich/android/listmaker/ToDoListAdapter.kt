package com.raywenderlich.android.listmaker

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter : RecyclerView.Adapter<ToDoListViewHolder>() {

    private val toDoLists = arrayOf("Android Development", "House Work", "Errands")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return toDoLists.size
    }
}