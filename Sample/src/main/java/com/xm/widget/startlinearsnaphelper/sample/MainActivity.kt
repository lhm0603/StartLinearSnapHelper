package com.xm.widget.startlinearsnaphelper.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xm.widget.StartLinearSnapHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvSnapHelper.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rvSnapHelper.adapter = MyAdapter()
        StartLinearSnapHelper().attachToRecyclerView(rvSnapHelper)
    }
}