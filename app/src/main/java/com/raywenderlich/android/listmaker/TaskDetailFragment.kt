package com.raywenderlich.android.listmaker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class TaskDetailFragment : Fragment() {

    lateinit var list:  TaskList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Lesson 36 -- if there are arguments...
        arguments?.let {
            list = it.getParcelable(ARG_LIST)!!  // (!!) we need to force unwrap it
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_detail, container, false)
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