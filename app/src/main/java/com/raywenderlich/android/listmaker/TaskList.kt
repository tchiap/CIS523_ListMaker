package com.raywenderlich.android.listmaker

import android.os.Parcel
import android.os.Parcelable

class TaskList(val name: String, val tasks: ArrayList<String> = ArrayList()) : Parcelable {




    // Lesson 24
    // constructor -- calls primary constructor

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    )

    companion object CREATOR:  Parcelable.Creator<TaskList> {
        override fun createFromParcel(source: Parcel):  TaskList = TaskList(source)


        override fun newArray(size: Int): Array<TaskList?> = arrayOfNulls(size)

    }



    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeStringList(tasks)
    }


}