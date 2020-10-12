package com.raywenderlich.android.listmaker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter : RecyclerView.Adapter<ToDoListViewHolder>() {

    private val toDoLists = arrayOf("Android Development", "House Work", "Errands")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {

        // inflate  - Lesson 8
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_list_view_holder,parent, false)

        return ToDoListViewHolder(view)

    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return toDoLists.size
    }
}