package com.example.chemp_podject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.chemp_podject.databinding.ActivityOnBoard1Binding
import java.lang.Math.abs
import java.util.*

//Нужно наследоваться от GestureDetector.OnGestureListener для обработки свайпов
class on_board1 : AppCompatActivity(), GestureDetector.OnGestureListener {
    private var binding: ActivityOnBoard1Binding? = null

    //Изначально index задаем 0, т.к. у нас это первый кружочек
    private var index = 0

    //Создаем объект GestureDetector
    lateinit var gestureDetector: GestureDetector

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoard1Binding.inflate(layoutInflater)
        setContentView(binding!!.root)
        // Инициализируем переменную gestureDetector
        gestureDetector = GestureDetector(this@on_board1, this@on_board1)
        binding!!.textTopscreen.setOnClickListener()
        {

            val textTopscreen = Intent(this@on_board1, Input_and_register::class.java)
            startActivity(textTopscreen)
        }
        //вызываем метод
        init()
    }

    /**
     * init - отдельный метод в который кладем события (нажатие на кнопку, изменение текста и т.п.)
     */
    private fun init() {
        with(binding!!) {
            pointFirst.setOnClickListener() //Нажатие на первый кружочек
            {
                changedText(0)
            }
            pointSecond.setOnClickListener() //Нажатие на второй кружочек
            {
                changedText(1)
            }
            pointThird.setOnClickListener() //Нажатие на третий кружочек
            {
                changedText(2)
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
            /**
             * @param point - индекс кружочка в отрисовке
             */
    fun changedText(point: Int) {
        with(binding!!) {
            /*
            В зависимости от точки меняем текст, цвет и т.п.
             */
            when (point) {

                0 -> {
                    textView2.text = "Анализы"
                    textView3.text = "Экспресс сбор и получение проб"
                    textTopscreen.text = "Пропустить"
                    pointFirst.background = getDrawable(R.drawable.shape_circle_blue)
                    pointSecond.background = getDrawable(R.drawable.shape_circle)
                    pointThird.background = getDrawable(R.drawable.shape_circle)
                    picture.setImageDrawable(getDrawable(R.drawable.icon_analizi))
                    index = 0
                }
                1 -> {
                    textView2.text = "Уведомления"
                    textView3.text = "Вы быстро узнаете о результатах"
                    textTopscreen.text = "Пропустить"
                    pointFirst.background = getDrawable(R.drawable.shape_circle)
                    pointSecond.background = getDrawable(R.drawable.shape_circle_blue)
                    pointThird.background = getDrawable(R.drawable.shape_circle)
                    picture.setImageDrawable(getDrawable(R.drawable.icon_yved))
                    index = 1
                }
                2 -> {
                    textView2.text = "Мониторинг"
                    textView3.text = "Наши врачи всегда наблюдают \nза вашими показателями здоровья"
                    textTopscreen.text = "Завершить"
                    pointFirst.background = getDrawable(R.drawable.shape_circle)
                    pointSecond.background = getDrawable(R.drawable.shape_circle)
                    pointThird.background = getDrawable(R.drawable.shape_circle_blue)
                    picture.setImageDrawable(getDrawable(R.drawable.icon_monitor))
                    index = 2
                }
            }
        }
    }

    //Все методы создаются по умолчанию, мы изменяем только onFling
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (gestureDetector.onTouchEvent(event!!)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

    override fun onDown(p0: MotionEvent): Boolean {
        return false
    }

    override fun onShowPress(p0: MotionEvent) {

    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        return false
    }

    override fun onScroll(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float): Boolean {
        return false
    }

    override fun onLongPress(p0: MotionEvent) {
        TODO("Not yet implemented")
    }

    override fun onFling(
        e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float
    ): Boolean {
        try {
            /*
            e1 - начальная точка касания (параметр x - ось Ox, параметр y - ось Oy)
            e2 - конечная точка касания
            diffY - движение по вертикали
            diffX = движение по горизонтали
            */
            val diffY = e2.y - e1.y
            val diffX = e2.x - e1.x
            if (abs(diffX) > abs(diffY)) {
                if (abs(diffX) > 100 && abs(velocityX) > 100) {
                    if (diffX > 0) {
                        /*
                        Свайп слева на право, уменьшаем индекс, если он выходит за границы, то возвращаем в два (в конец)
                        */
                        index--
                        if (index == -1) index = 2
                        changedText(index)
                    } else {
                        /*
                      Свайп справо на лево, увеличиваем индекс, если он выходит за границы, то возвращаем в ноль (в начало)
                      */
                        index++
                        if (index == 3) index = 0
                        changedText(index)
                    }
                }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return true
    }

}
