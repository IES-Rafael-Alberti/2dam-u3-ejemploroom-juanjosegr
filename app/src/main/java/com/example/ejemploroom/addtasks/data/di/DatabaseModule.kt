package com.example.ejemploroom.addtasks.data.di

import android.content.Context
import androidx.room.Room
import com.example.ejemploroom.addtasks.data.TaskDao
import com.example.ejemploroom.addtasks.data.TasksManageDatabase.TasksManageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    private val databaseName = "TaskDatabase"
    @Provides
    fun provideTaskDao(tasksManageDatabase: TasksManageDatabase): TaskDao {
        return tasksManageDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun provideTasksManageDatabase(@ApplicationContext appContext: Context): TasksManageDatabase{
        return Room.databaseBuilder(appContext, TasksManageDatabase::class.java, databaseName).build()
    }

}