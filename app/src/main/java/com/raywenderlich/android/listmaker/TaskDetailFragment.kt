package com.raywenderlich.android.listmaker

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*


class TaskDetailFragment : Fragment() {

    lateinit var list:  TaskList
    lateinit var taskListRecyclerView: RecyclerView

    lateinit var addTaskButton : FloatingActionButton

    lateinit var listDataManager: ListDataManager



    // Lesson 44 - we don't need onCreate() anymore - we're getting the list from the Bundle
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Lesson 36 -- if there are arguments...
        arguments?.let {
            list = it.getParcelable(ARG_LIST)!!  // (!!) we need to force unwrap it
        }

    }

     */



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get listDataManager, then access the list

        // Lesson 44 -- used ViewModelProvider and not ViewModelProviders, because ViewModelProviders is deprecated
        listDataManager = ViewModelProvider(this).get(ListDataManager::class.java)

        // Lesson 44
        arguments?.let {
            // get passed in args
            val args = TaskDetailFragmentArgs.fromBundle(it)
            list = listDataManager.readLists().filter { list -> list.name == args.listString }[0]
        }


        // Copy and pasted from DetailActivity -- Lesson 41

        activity?.let {
            // Lesson 26
            taskListRecyclerView = view.findViewById(R.id.task_list_recyclerview)
            taskListRecyclerView.layoutManager = LinearLayoutManager(it)

            // Lesson 27
            taskListRecyclerView.adapter = TaskListAdapter(list)

            // Lesson 44
            it.toolbar?.title = list.name

            // Lesson 28
            addTaskButton = view.findViewById(R.id.add_task_button)
            addTaskButton.setOnClickListener {
                showCreateTaskDialog()
            }
        }



    }

    private fun showCreateTaskDialog() {

        // Lesson 41
        activity?.let {
            // Lesson 28
            val taskEditText = EditText(it)
            taskEditText.inputType = InputType.TYPE_CLASS_TEXT

            AlertDialog.Builder(it)
                .setTitle(R.string.task_to_add)
                .setView(taskEditText)
                .setPositiveButton(R.string.add_task) {
                        dialog, _ ->
                    val task = taskEditText.text.toString()
                    list.tasks.add(task)
                    listDataManager.saveList(list)
                    dialog.dismiss()
                }
                .create()
                .show()
        }



    }

    companion object {

        private val ARG_LIST = "list"
        //fun newInstance(list: TaskList) = TaskDetailFragment()
        // Lesson 36 -- we want to create a Bundle (contains key-value pairs)
        fun newInstance(list: TaskList)  : TaskDetailFragment {
            val bundle = Bundle()
            bundle.putParcelable(ARG_LIST, list)
            val fragment = TaskDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}