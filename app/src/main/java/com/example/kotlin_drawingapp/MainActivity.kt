package com.example.kotlin_drawingapp

import android.graphics.PointF
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_drawingapp.model.Color
import com.example.kotlin_drawingapp.model.draw.DrawObject
import com.example.kotlin_drawingapp.model.draw.DrawView
import com.example.kotlin_drawingapp.model.source.memory.PlaneDataSource
import com.example.kotlin_drawingapp.model.source.DrawingRepository

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter
    private lateinit var drawView: DrawView
    private lateinit var tvRgb: TextView
    private lateinit var seekBarAlpha: SeekBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val planeRepository = DrawingRepository(PlaneDataSource())
        presenter = MainPresenter(planeRepository, this)
        drawView = findViewById(R.id.drawView)
        tvRgb = findViewById(R.id.tv_background_color)
        seekBarAlpha = findViewById(R.id.seekBar)
        val btnGenerateRectangle = findViewById<Button>(R.id.btn_generate_rect)
        btnGenerateRectangle.setOnClickListener {
            presenter.createDrawObject(DrawObject.Category.RECTANGLE)
        }

        drawView.setOnTouchListener(object : DrawView.OnTouchListener {
            override fun onClick(point: PointF) {
                presenter.selectDrawObject(point.x, point.y)
            }
        })

        seekBarAlpha.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (progress > 0) {
                    presenter.setCurrentSelectedDrawObjectAlpha(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    override fun showDrawObject(drawObject: List<DrawObject>) {
        drawView.draw(drawObject)
    }

    override fun showDrawObjectInfo(color: Color, alpha: Int) {
        tvRgb.text = String.format("%X", color.getRgb())
        seekBarAlpha.progress = alpha
    }
}