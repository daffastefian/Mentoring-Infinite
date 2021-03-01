package com.daffa.weekone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daffa.weekone.R
import com.daffa.weekone.data.Todo
import kotlinx.android.synthetic.main.item_detail_todo.view.*

class TodoAdapter(private val data:List<Todo>): RecyclerView.Adapter<TodoHolder>() {
    private lateinit var onItemCheckListener: OnItemCheckListener

    fun setItemCheckListener(onItemCheckListener: OnItemCheckListener){
        this.onItemCheckListener = onItemCheckListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        return TodoHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_detail_todo,parent,false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            onItemCheckListener.onItemClickListener(data[holder.adapterPosition])
        }
    }
}
class TodoHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun bind(data: Todo){
        itemView.apply {
            tvTitle.text = data.title
            if (data.complete){
                tvStatus.text = context.getString(R.string.completed)
            }else{
                tvStatus.text = context.getString(R.string.not_completed)
            }
        }
    }
}

interface OnItemCheckListener{
    fun onItemClickListener(data: Todo)
}
