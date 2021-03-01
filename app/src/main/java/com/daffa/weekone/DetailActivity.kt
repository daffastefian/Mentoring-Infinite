package com.daffa.weekone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daffa.weekone.data.Todo
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private lateinit var data: Todo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        data = intent.getSerializableExtra("data") as Todo

        tvId.text = data.id
        tvUser.text = data.userId
        tvTitle.text = data.title
        tvCompleted.text = data.complete.toString()
    }
}