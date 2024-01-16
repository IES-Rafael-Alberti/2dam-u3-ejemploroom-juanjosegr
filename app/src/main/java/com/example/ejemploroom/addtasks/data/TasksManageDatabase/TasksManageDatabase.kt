package com.example.ejemploroom.addtasks.data.TasksManageDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ejemploroom.addtasks.data.TaskDao
import com.example.ejemploroom.addtasks.data.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TasksManageDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao

}