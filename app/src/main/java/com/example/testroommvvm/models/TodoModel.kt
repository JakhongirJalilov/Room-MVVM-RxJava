package com.example.testroommvvm.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author jakhongirjalilov
 * @version 2.0
 * @data 2/26/21
 * @project Test Room MVVM
 */
@Entity(tableName = "todo_list_table")
data class TodoModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "date") val date: String
)