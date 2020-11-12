package com.xm.widget

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * 针对RecyclerView的布局管理器为LinearLayoutManager的Item吸附Start(Top)或End(bottom)对齐位置
 *
 * @author XiaoMing 461415832@qq.com
 * @see 2020-11-11
 */
class StartLinearSnapHelper : LinearSnapHelper() {
    private companion object {
        const val DIRECTION = 2

        const val ORIENTATION_HORIZONTALLY = 0

        const val ORIENTATION_VERTICALLY = 1

        val outDirections = IntArray(DIRECTION)
    }

    private var recyclerView: RecyclerView? = null

    private val isReverse: Boolean
        get() {
            val layoutManager = recyclerView?.layoutManager
            return layoutManager is LinearLayoutManager && layoutManager.reverseLayout
        }

    private var verticalHelper: OrientationHelper? = null

    private var horizontalHelper: OrientationHelper? = null

    override fun attachToRecyclerView(recyclerView: RecyclerView?) {
        super.attachToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun calculateDistanceToFinalSnap(layoutManager: RecyclerView.LayoutManager, targetView: View): IntArray? {
        if (layoutManager.canScrollHorizontally()) {
            outDirections[ORIENTATION_HORIZONTALLY] = distanceToStart(targetView, getHorizontalHelper(layoutManager))
        } else {
            outDirections[ORIENTATION_HORIZONTALLY] = 0
        }

        if (layoutManager.canScrollVertically()) {
            outDirections[ORIENTATION_VERTICALLY] = distanceToStart(targetView, getVerticalHelper(layoutManager))
        } else {
            outDirections[ORIENTATION_VERTICALLY] = 0
        }
        return outDirections
    }

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager?): View? {
        if (layoutManager !is LinearLayoutManager) {
            return super.findSnapView(layoutManager)
        }
        return if (layoutManager.canScrollHorizontally()) {
            getStartView(layoutManager, getHorizontalHelper(layoutManager))
        } else {
            getStartView(layoutManager, getVerticalHelper(layoutManager))
        }
    }

    private fun distanceToStart(targetView: View, helper: OrientationHelper): Int {
        if (isReverse) {
            // 反向布局
            val totalSpace = helper.totalSpace
            return helper.getDecoratedEnd(targetView) - totalSpace - helper.startAfterPadding
        }
        return helper.getDecoratedStart(targetView) - helper.startAfterPadding
    }

    private fun getStartView(layoutManager: RecyclerView.LayoutManager, helper: OrientationHelper): View? {
        if (layoutManager !is LinearLayoutManager) {
            return super.findSnapView(layoutManager)
        }
        val firstChild = layoutManager.findFirstVisibleItemPosition()
        val isLastItem = layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.getItemCount() - 1
        if (firstChild == RecyclerView.NO_POSITION || isLastItem) {
            // 找不到第一个Item的位置(没有Item)，或者最后一个Item完全可见，返回 null，表示不需要调整
            return null
        }
        val child = layoutManager.findViewByPosition(firstChild)
        val decoratedEnd = helper.getDecoratedEnd(child)
        val decoratedMeasurement = helper.getDecoratedMeasurement(child)
        if (isReverse) {
            // 反向布局
            val totalSpace = helper.totalSpace
            val startAfterPadding = helper.startAfterPadding
            return if (decoratedEnd - (totalSpace + startAfterPadding) < decoratedMeasurement shr 1) {
                // 第一个可见Item的可见部分大于Item的size的一半，则调整对齐该Item
                child
            } else {
                // 否则调整下一个Item对齐
                layoutManager.findViewByPosition(firstChild + 1)
            }
        }
        return if (decoratedEnd >= decoratedMeasurement shr 1 && decoratedEnd > 0) {
            // 第一个可见Item的可见部分大于Item的size的一半，则调整对齐该Item
            child
        } else {
            // 否则调整下一个Item对齐
            layoutManager.findViewByPosition(firstChild + 1)
        }
    }

    private fun getVerticalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        if (verticalHelper == null) {
            verticalHelper = OrientationHelper.createVerticalHelper(layoutManager)
        }
        return verticalHelper!!
    }

    private fun getHorizontalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        if (horizontalHelper == null) {
            horizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager)
        }
        return horizontalHelper!!
    }
}