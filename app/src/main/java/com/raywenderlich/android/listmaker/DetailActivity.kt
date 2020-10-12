package com.raywenderlich.android.listmaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DetailActivity : AppCompatActivity() {

    lateinit var list: TaskList
    lateinit var taskListRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Lesson 25 - Bring Everything Together
        setContentView(R.layout.activity_detail)

        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY) as TaskList
        title = list.name

        // Lesson 26
        taskListRecyclerView = findViewById(R.id.task_list_recyclerview)
        taskListRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}