package com.example.tasksapp.model.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    @ColumnInfo(name="task_id")
    @PrimaryKey(autoGenerate = true)
    var Id:Long?,

    @ColumnInfo(name="task_title")
    @Nullable
    var title:String?,

    @ColumnInfo(name="task_description")
    var description:String?,

    @ColumnInfo(name ="task_done")
    var isDone:Boolean
)