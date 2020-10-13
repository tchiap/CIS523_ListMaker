package com.raywenderlich.android.listmaker

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class TodoListFragment : Fragment(), ToDoListAdapter.TodoListClickListener {

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var toDoListRecyclerView: RecyclerView
    private lateinit var listDataManager: ListDataManager //  = ListDataManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    // only intended to inflate the layout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    // Note:  called immediately after onCreateView()  -- Makes it a good place to do all your configuration
    // Lesson 33
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lists = listDataManager.readLists()


        toDoListRecyclerView = view.findViewById(R.id.lists_recyclerview)
        toDoListRecyclerView.layoutManager = LinearLayoutManager(activity)
        toDoListRecyclerView.adapter = ToDoListAdapter(lists, this)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context

            // Lesson 33
            listDataManager = ListDataManager(context)
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onTodoListClicked(list: TaskList)
    }

    companion object {
        fun newInstance(): TodoListFragment {
            return TodoListFragment()
        }
    }

    // Lesson 33
    override fun listItemClicked(list: TaskList) {

        // this listener is the Activity
        listener?.onTodoListClicked(list)
    }


    // Lesson 33
    fun addList(list: TaskList) {
        listDataManager.saveList(list)
        val todoAdapter = toDoListRecyclerView.adapter as ToDoListAdapter
        todoAdapter.addList(list)
    }

    // Lesson 33
    fun saveList(list: TaskList) {
        listDataManager.saveList(list)
        updateLists()
    }

    private fun updateLists() {

        // Lesson 29 and 33
        val lists = listDataManager.readLists()
        toDoListRecyclerView.adapter = ToDoListAdapter(lists, this)
    }
}