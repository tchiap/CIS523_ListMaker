package com.raywenderlich.android.listmaker

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_todo_list.*


class TodoListFragment : Fragment(), ToDoListAdapter.TodoListClickListener {

    //private var listener: OnFragmentInteractionListener? = null

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

        // If the activity exists, then we want to pass the context into the ListDataManager
        activity?.let {
            //listDataManager = ListDataManager(it)
            //listDataManager = ViewModelProviders.of(this).get(ListDataManager::class.java) << this was in the video, but is deprecated.  Use line below
            listDataManager = ViewModelProvider(this).get(ListDataManager::class.java)
        }


        val lists = listDataManager.readLists()


        toDoListRecyclerView = view.findViewById(R.id.lists_recyclerview)
        toDoListRecyclerView.layoutManager = LinearLayoutManager(activity)
        toDoListRecyclerView.adapter = ToDoListAdapter(lists, this)

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { _ ->
            showCreateTodoListDialog()
        }




    }

    /*
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            //listener = context

            // Lesson 33, cut in Lesson 40
            //listDataManager = ListDataManager(context)
        }
    }
     */

    /*
    override fun onDetach() {
        super.onDetach()
        listener = null
    }
     */

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
        //listener?.onTodoListClicked(list)  // deleted in Lesson 40

        // Lesson 43 - Navigation Graph
        view?.let {
            it.findNavController().navigate(R.id.action_todoListFragment_to_taskDetailFragment)
        }
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

    // Lesson 40
    private fun showCreateTodoListDialog() {
        activity?.let {
            val dialogTitle = getString(R.string.name_of_list)

            val positiveButtonTitle = getString(R.string.create_list) // made val since we're not changing it

            val myDialog = AlertDialog.Builder(it)
            val todoTitleEditText = EditText(it)
            todoTitleEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

            myDialog.setTitle(dialogTitle)
            myDialog.setView(todoTitleEditText)

            myDialog.setPositiveButton(positiveButtonTitle) {
                    dialog, _ ->
                //val adapter = toDoListRecyclerView.adapter as ToDoListAdapter

                // Lesson 17
                val list = TaskList(todoTitleEditText.text.toString())

                // Lesson 33, Lesson 40
                addList(list)

                //listDataManager.saveList(list)
                //adapter.addList(list)

                //adapter.addNewItem(todoTitleEditText.text.toString())
                dialog.dismiss()

                // Lesson 25
                showTaskListItems(list)

            }
            myDialog.create().show()
        }

    }


    // Lesson 40

    private fun showTaskListItems(list: TaskList) {
    }



    private fun updateLists() {

        // Lesson 29 and 33
        val lists = listDataManager.readLists()
        toDoListRecyclerView.adapter = ToDoListAdapter(lists, this)
    }
}