package com.example.kotlin_drawingapp

import com.example.kotlin_drawingapp.model.Rectangle
import com.example.kotlin_drawingapp.model.RectangleBorder
import com.example.kotlin_drawingapp.model.RectangleFactory

class Plane {
    private val rectangleList = mutableListOf<Rectangle>()
    private val rectangleBorderList = mutableListOf<RectangleBorder>()

    fun getRectangleCount(): Int {
        return rectangleList.size
    }

    fun createRectangle() {
        rectangleList.add(RectangleFactory.create())
    }

    fun createRectangleBorder(rectangle: Rectangle) {
        val border = RectangleBorder(rectangle.size, rectangle.point)
        if (!rectangleBorderList.contains(border)) {
            rectangleBorderList.add(border)
        }
    }

    fun clearRectangleBorder() {
        rectangleBorderList.clear()
    }

    fun getAllRectangle(): List<Rectangle> {
        return rectangleList.toList()
    }

    fun getAllRectangleBorder(): List<RectangleBorder> {
        return rectangleBorderList.toList()
    }

    fun modifyRectangle(index: Int, rectangle: Rectangle) {
        rectangleList[index] = rectangle
    }

    fun getRectangleByIndex(index: Int): Rectangle {
        return rectangleList[index]
    }

    fun getRectangleByPosition(x: Int, y: Int): Rectangle? {
        for (index in rectangleList.size-1 downTo 0) {
            if (rectangleList[index].point.x >= x && rectangleList[index].point.x + x <= x &&
                rectangleList[index].point.y >= y && rectangleList[index].point.y + y <= y
            ) {
                return rectangleList[index]
            }
        }

        return null
    }
}
