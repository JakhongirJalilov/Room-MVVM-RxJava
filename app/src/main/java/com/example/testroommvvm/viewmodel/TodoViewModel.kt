package com.example.testroommvvm.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.testroommvvm.db.AppDatabase
import com.example.testroommvvm.models.TodoModel
import io.reactivex.Observable
import io.reactivex.Single


/**
 * @author jakhongirjalilov
 * @version 2.0
 * @data 2/27/21
 * @project Test Room MVVM
 */
class TodoViewModel : ViewModel() {
    fun todoList(): Single<List<TodoModel>> {
        return AppDatabase.database?.todoList()?.getAllTodo()!!
    }
}