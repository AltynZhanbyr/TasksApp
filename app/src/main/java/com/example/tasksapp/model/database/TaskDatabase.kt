package com.example.tasksapp.model.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tasksapp.model.dao.TaskDao
import com.example.tasksapp.model.entity.Task

@Database(entities = [Task::class], version = 4, exportSchema = true, autoMigrations = [AutoMigration(from = 3, to = 4)])
abstract class TaskDatabase: RoomDatabase() {
    abstract fun getTaskDao(): TaskDao

    companion object{
        fun getDB(context: Context):TaskDatabase{
            return Room.databaseBuilder(
                context,
                TaskDatabase::class.java,
                "tasks_database"
            ).build()
        }
    }
}