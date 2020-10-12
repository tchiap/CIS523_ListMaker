package com.raywenderlich.android.listmaker

import android.content.Context
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

class MainActivity : AppCompatActivity() {

    private lateinit var toDoListRecyclerView: RecyclerView
    val listDataManager: ListDataManager  = ListDataManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        toDoListRecyclerView = findViewById(R.id.lists_recyclerview)
        toDoListRecyclerView.layoutManager = LinearLayoutManager(this)
        toDoListRecyclerView.adapter = ToDoListAdapter()

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

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

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
                val adapter = toDoListRecyclerView.adapter as ToDoListAdapter
                adapter.addNewItem(todoTitleEditText.text.toString())
                dialog.dismiss()
        }
        myDialog.create().show()

    }
}