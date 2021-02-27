package com.example.testroommvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testroommvvm.R
import com.example.testroommvvm.db.AppDatabase
import com.example.testroommvvm.models.TodoModel
import com.example.testroommvvm.viewmodel.TodoViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_todo.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: TodoListAdapter
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = TodoListAdapter()
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        todoViewModel = TodoViewModel()
        readData()

        btAdd.setOnClickListener {
            val dialog = AlertDialog.Builder(this).create()
            val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_todo, null, false)
            dialog.setView(view)
            view.btAddTodo.setOnClickListener {
                if (!view.enterText.text.isNullOrBlank()) {
                    addData(
                        view.enterText.text.toString(),
                        "${view.datePicker.dayOfMonth}/${view.datePicker.month}/${view.datePicker.year}"
                    )
                    dialog.dismiss()
                } else {
                    view.enterText.error = "Please, enter todo item"
                }
            }
            view.btCancel.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    private fun readData() {
        todoViewModel.todoList()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<TodoModel>>() {
                override fun onSuccess(t: List<TodoModel>) {
                    if (t.isNotEmpty()) {
                        adapter.loadData(t)
                    }
                }

                override fun onError(e: Throwable) {
                    Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun addData(todoContent: String, todoDate: String) {
        AppDatabase.database?.todoList()?.addTodo(
            TodoModel(0, todoContent, todoDate)
        )?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    Toast.makeText(this@MainActivity, "Added successfully", Toast.LENGTH_SHORT).show()
                    readData()
                }

                override fun onError(e: Throwable) {
                    Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            })
    }
}