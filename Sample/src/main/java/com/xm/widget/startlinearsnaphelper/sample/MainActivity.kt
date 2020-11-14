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
        rvSnapHelper1.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rvSnapHelper1.adapter = MyAdapter()
        StartLinearSnapHelper().attachToRecyclerView(rvSnapHelper1)

        // reverseLayout true
        rvSnapHelper2.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, true)
        rvSnapHelper2.adapter = MyAdapter()
        StartLinearSnapHelper().attachToRecyclerView(rvSnapHelper2)

        // orientation Vertical
        rvSnapHelper3.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvSnapHelper3.adapter = MyAdapter()
        StartLinearSnapHelper().attachToRecyclerView(rvSnapHelper3)
    }
}