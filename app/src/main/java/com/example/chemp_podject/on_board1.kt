package com.example.chemp_podject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.chemp_podject.databinding.ActivityOnBoard1Binding
import com.example.chemp_podject.models.OnBoardModel
import java.lang.Math.abs
import java.util.*

//Нужно наследоваться от GestureDetector.OnGestureListener для обработки свайпов
class on_board1 : AppCompatActivity(), GestureDetector.OnGestureListener {
    private var binding: ActivityOnBoard1Binding? = null
    lateinit var queue: Queue<OnBoardModel>
    lateinit var gestureDetector: GestureDetector
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoard1Binding.inflate(layoutInflater)
        setContentView(binding!!.root)
        gestureDetector = GestureDetector(this@on_board1, this@on_board1)
        queue =
            LinkedList(listOf(
                OnBoardModel("Пропустить","Анализы", "Экспресс сбор и получение проб",
                    getDrawable(R.drawable.icon_analizi)!!, getDrawable(R.drawable.point_style_blue)!!,
                    getDrawable(R.drawable.point_style_stroke)!!, getDrawable(R.drawable.point_style_stroke)!!),
                OnBoardModel("Пропустить","Уведомления", "Вы быстро узнаете о результатах",
                    getDrawable(R.drawable.icon_yved)!!, getDrawable(R.drawable.point_style_stroke)!!,
                    getDrawable(R.drawable.point_style_blue)!!, getDrawable(R.drawable.point_style_stroke)!!),
                OnBoardModel("Завершить","Мониторинг", "Наши врачи всегда наблюдают за вашими показателями здоровья",
                    getDrawable(R.drawable.icon_monitor)!!, getDrawable(R.drawable.point_style_stroke)!!,
                    getDrawable(R.drawable.point_style_stroke)!!, getDrawable(R.drawable.point_style_blue)!!),

            ))
        enterOnBoard(queue!!.poll())
        init()
    }

    /**
     * отдельный метод, в который кладем события (в нашем случае нажатие на кнопку)
     */
    fun init(){
        with(binding!!){
            textTopscreen.setOnClickListener(){
                startActivity(Intent(this@on_board1, Input_and_register::class.java))
                finish()
            }
        }
    }


    /**
     * enterOnBoard - отдельный метод, с помощью которого мы берем данные из заполненного листа
     * (LinkedList) и помещаем их в нужные объекты layout
     */
    fun enterOnBoard(onBoardModel: OnBoardModel){
        with(binding!!){
            textTopscreen.text = onBoardModel.button
            textName.text = onBoardModel.title
            textDes.text = onBoardModel.description
            picture.setImageDrawable(onBoardModel.image)
            pointFirst.setImageDrawable(onBoardModel.p1)
            pointSecond.setImageDrawable(onBoardModel.p2)
            pointThird.setImageDrawable(onBoardModel.p3)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (gestureDetector.onTouchEvent(event!!)){
            true
        } else{
            return super.onTouchEvent(event)
        }
    }
    override fun onDown(e: MotionEvent): Boolean {
        return false
    }

    override fun onShowPress(e: MotionEvent) {
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        return false
    }

    override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent) {
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        val diffY = e2.y - e1.y
        val diffX = e2.x - e1.x
        if(abs(diffX)>abs(diffY)){
            if(abs(diffX)>100 && abs(velocityX) > 100){
                if(diffX<0){
                    if(queue.size != 0){
                        enterOnBoard(queue.poll())
                    }
                }
            }
        }
        return true
    }

}
