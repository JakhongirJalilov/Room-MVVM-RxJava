package com.example.testroommvvm.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testroommvvm.R
import com.example.testroommvvm.models.TodoModel
import kotlinx.android.synthetic.main.item_todo.view.*

/**
 * @author jakhongirjalilov
 * @version 2.0
 * @data 2/27/21
 * @project Test Room MVVM
 */
class TodoListAdapter : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {
    private var data = mutableListOf<TodoModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
    )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val d = data[position]
        holder.itemView.apply {
            todoText.text = d.content
            txDate.text = d.date
        }
    }

    fun loadData(data: List<TodoModel>) {
        this.data = data.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}