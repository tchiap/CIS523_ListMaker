package com.raywenderlich.android.listmaker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {


    // Note:  all code cleared out in Lesson 41 because we don't need it.


    //lateinit var list: TaskList

    // Cut in Lesson 41
    //lateinit var taskListRecyclerView: RecyclerView
    //lateinit var addTaskButton : FloatingActionButton

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Lesson 25 - Bring Everything Together
        setContentView(R.layout.activity_detail)

        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY) as TaskList
        title = list.name


    }

     */

    /*
    private fun showCreateTaskDialog() {

        // Lesson 28
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this)
            .setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task) {
                dialog, _ ->
                    val task = taskEditText.text.toString()
                    list.tasks.add(task)
                    dialog.dismiss()
            }
            .create()
            .show()

    }

     */

    /*
    override fun onBackPressed() {

        // Lesson 29
        val bundle = Bundle()
        bundle.putParcelable(MainActivity.INTENT_LIST_KEY, list)

        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)



        super.onBackPressed()
    }

     */
}