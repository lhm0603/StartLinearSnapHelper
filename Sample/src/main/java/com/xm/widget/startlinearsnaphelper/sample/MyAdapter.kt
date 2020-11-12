package com.xm.widget.startlinearsnaphelper.sample

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xm.widget.startlinearsnaphelper.sample.MyAdapter.MyViewHolder

class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
    private val colors: IntArray =
        intArrayOf(Color.RED, Color.BLUE, Color.GRAY, Color.YELLOW, Color.GREEN, Color.rgb(255, 255, 0), Color.rgb(123,
            123, 123), Color.rgb(2,1 , 222), Color.rgb(12, 33, 52), Color.rgb(123, 78, 87))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_test, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTest?.text = "I known $position"
        holder.itemView.setBackgroundColor(colors[position])
    }

    override fun getItemCount() = colors.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTest: TextView? = null

        init {
            tvTest = itemView.findViewById(R.id.tvTest)
        }
    }
}