package com.raywenderlich.android.listmaker

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.preference.PreferenceManager

class ListDataManager(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext

    fun saveList(list: TaskList) {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)

        // From videos, this does not work with androidX PreferenceManager 1.1.1
        //sharedPrefs.putStringSet(list.name, list.tasks.toHashSet())
        //sharedPrefs.apply()

        // Shared preferences cannot store an ArrayList.  We can use a set
        sharedPrefs.edit().putStringSet(list. name, list.tasks.toHashSet()).apply()

    }

    fun readLists(): ArrayList<TaskList> {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedPrefs.all
        val taskLists = ArrayList<TaskList>()

        for (taskList in contents) {
            val taskItems = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, taskItems)
            taskLists.add(list)
        }

        return taskLists
    }

}