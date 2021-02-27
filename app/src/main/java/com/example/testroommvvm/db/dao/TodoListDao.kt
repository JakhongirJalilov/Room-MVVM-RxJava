package com.example.testroommvvm.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.testroommvvm.models.TodoModel
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author jakhongirjalilov
 * @version 2.0
 * @data 2/26/21
 * @project Test Room MVVM
 */
@Dao
interface TodoListDao {

    @Insert
    fun addTodo(todoModel: TodoModel) : Completable

    @Query("SELECT * FROM todo_list_table")
    fun getAllTodo(): Single<List<TodoModel>>
}
