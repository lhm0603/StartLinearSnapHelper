package com.xm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xm.widget.XmLinearSnapHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvSnapHelper.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rvSnapHelper.adapter = MyAdapter()
        XmLinearSnapHelper().attachToRecyclerView(rvSnapHelper)
    }
}