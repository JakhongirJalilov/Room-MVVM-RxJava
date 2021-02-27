package com.example.testroommvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testroommvvm.db.dao.TodoListDao
import com.example.testroommvvm.models.TodoModel

/**
 * @author jakhongirjalilov
 * @version 2.0
 * @data 1/14/21
 * @project cccandroidtest
 */

@Database(
    entities = [
        TodoModel::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoList(): TodoListDao

    companion object {
        var database: AppDatabase? = null
        fun init(context: Context) {
            database = Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "todo_db"
            ).build()
        }
    }
}