package com.daffa.weekone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.daffa.weekone.adapter.OnItemCheckListener
import com.daffa.weekone.adapter.TodoAdapter
import com.daffa.weekone.data.ApiClient
import com.daffa.weekone.data.Todo
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private val apiService = ApiClient.getClient()
    private val listTodo = mutableListOf<Todo>()
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        todoAdapter = TodoAdapter(listTodo)
        setClickListener()
        rvTodo.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        apiService.getTodos().enqueue(object : Callback<List<Todo>>{
            override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
                Log.d("Hasil", response.body()!!.toString())
                listTodo.clear()
                if (response.body()!=null){
                    listTodo.addAll(response.body()!!)
                }
                todoAdapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
                Log.d("Hasil", "Error")
            }
        })

    }

    private fun setClickListener(){
        todoAdapter.setItemCheckListener(
            object : OnItemCheckListener{
                override fun onItemClickListener(data: Todo) {
                    try {
                        Log.d("Hasil", "Terkirim ${data.toString()}")
                        val intent = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra("data",data)
                        startActivity(intent)
                    }catch (e: Exception){

                    }
                }
            }
        )
    }
}