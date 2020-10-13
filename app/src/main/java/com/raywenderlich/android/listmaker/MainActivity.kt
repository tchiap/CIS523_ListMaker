package com.raywenderlich.android.listmaker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), TodoListFragment.OnFragmentInteractionListener {

    //private lateinit var toDoListRecyclerView: RecyclerView
    //private val listDataManager: ListDataManager  = ListDataManager(this)

    // Lesson 33
    private var todoListFragment = TodoListFragment.newInstance()

    companion object {
        const val INTENT_LIST_KEY = "list"
        const val LIST_DETAIL_REQUEST_CODE = 123
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        // Lesson 17
        /*
        val lists = listDataManager.readLists()


        toDoListRecyclerView = findViewById(R.id.lists_recyclerview)
        toDoListRecyclerView.layoutManager = LinearLayoutManager(this)
        toDoListRecyclerView.adapter = ToDoListAdapter(lists, this)

         */

        /*
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
         */

        // Lesson 11
        /*
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val adapter = toDoListRecyclerView.adapter as ToDoListAdapter
            adapter.addNewItem()
        }
         */

        // Lesson 13
        // Using underscore since we're not using view (gets rid of warning)
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { _ ->
            showCreateTodoListDialog()
        }

        // Lesson 34
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, todoListFragment)
            .commit()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        // Lesson 29
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LIST_DETAIL_REQUEST_CODE) {
            data?.let {
                val list = data.getParcelableExtra<TaskList>(INTENT_LIST_KEY)!!
                todoListFragment.saveList(list)
                //listDataManager.saveList(list)
                //updateList()
            }
        }
    }

    /*
    private fun updateList() {

        // Lesson 29
        val lists = listDataManager.readLists()
        toDoListRecyclerView.adapter = ToDoListAdapter(lists, this)
    }

     */

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }



    // Lesson 13
    // Some terminology:  modal dialog -- dialog that won't let the user do anything else with the dialog open
    private fun showCreateTodoListDialog() {
        val dialogTitle = getString(R.string.name_of_list)

        val positiveButtonTitle = getString(R.string.create_list) // made val since we're not changing it

        val myDialog = AlertDialog.Builder(this)
        val todoTitleEditText = EditText(this)
        todoTitleEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

        myDialog.setTitle(dialogTitle)
        myDialog.setView(todoTitleEditText)

        myDialog.setPositiveButton(positiveButtonTitle) {
            dialog, _ ->
                //val adapter = toDoListRecyclerView.adapter as ToDoListAdapter

                // Lesson 17
                val list = TaskList(todoTitleEditText.text.toString())

                // Lesson 33
                todoListFragment.addList(list)

                //listDataManager.saveList(list)
                //adapter.addList(list)

                //adapter.addNewItem(todoTitleEditText.text.toString())
                dialog.dismiss()

                // Lesson 25
                showTaskListItems(list)

        }
        myDialog.create().show()

    }


    // Lesson 23
    private fun showTaskListItems(list: TaskList) {

        // Create the intent
        val taskListItem = Intent(this, DetailActivity::class.java)

        // add an extra
        taskListItem.putExtra(INTENT_LIST_KEY, list)
        //startActivity(taskListItem)
        startActivityForResult(taskListItem, LIST_DETAIL_REQUEST_CODE)
    }

    /*
    override fun listItemClicked(list: TaskList) {
        showTaskListItems(list)
    }

     */

    // Lesson 33
    override fun onTodoListClicked(list: TaskList) {
        showTaskListItems(list)
    }

}