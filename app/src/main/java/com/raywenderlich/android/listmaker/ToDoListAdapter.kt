package com.raywenderlich.android.listmaker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter(private val lists:  ArrayList<TaskList>, val clickListener: TodoListClickListener) : RecyclerView.Adapter<ToDoListViewHolder>() {

    //private val toDoLists = arrayOf("Android Development", "House Work", "Errands", "Shopping")
    //private var toDoLists = mutableListOf("Android Development", "House Work", "Errands", "Shopping")

    // Lesson 11, 14
    /*
    fun addNewItem(listName: String = "") {

        // Lesson 14
        if (listName.isEmpty()) {
            toDoLists.add("Todo List " + (toDoLists.size + 1) )
        } else {
            toDoLists.add(listName)
        }

        // simply tells the RecyclerView to reload all the data
        notifyDataSetChanged()
    }

     */

    // Create an interface for the To-do list click listener
    interface TodoListClickListener {
        fun listItemClicked(list:  TaskList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {

        // inflate  - Lesson 8
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_list_view_holder,parent, false)

        return ToDoListViewHolder(view)

    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {

        // when this method is called, we pass in a ViewHolder
        // Lesson 9 - Bind Data
        holder.listPositionTextView.text = (position + 1).toString()  // 0-based, so +1
        //holder.listTitleTextView.text = toDoLists[position]

        // Lesson 17
        holder.listTitleTextView.text = lists[position].name

        // Lesson 25
        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(lists[position])
        }

    }

    override fun getItemCount(): Int {
        //return toDoLists.size

        // Lesson 17
        return lists.size
    }

    fun addList(list: TaskList) {
        lists.add(list)
        notifyItemInserted(lists.size - 1)
    }
}