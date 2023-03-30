package com.example.chemp_podject.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chemp_podject.Home
import com.example.chemp_podject.R
import com.example.chemp_podject.databinding.ItemPoiskBinding

//Адаптер обрабатывает и связывает данные со списком
class AdapterPoisk (private val listener: Home): RecyclerView.Adapter<AdapterPoisk.PoiskHolder>() {
    val poiskModelList = ArrayList<BlockModel>()

    //PoiskHolder является своеобразным контейнером для всех элементов, входящих в список
    class PoiskHolder(item: View) : RecyclerView.ViewHolder(item){
        private var bindingPoisk = ItemPoiskBinding.bind(item)

        fun bind (poisk: BlockModel, listener: Listener){
            bindingPoisk.textPoiskName.text = poisk.name
            bindingPoisk.textPoiskPrice.text = poisk.price + " ₽"
            bindingPoisk.textPoiskTime.text = poisk.time_result
            bindingPoisk.listPoisk.setOnClickListener(){
                listener.Click(poisk)
            }
        }
    }

    //getItemCount() возвращает количество элементов списка
    override fun getItemCount(): Int {
        return poiskModelList.size
    }
    //Метод onCreateViewHolder, в котором будет происходить создание ViewHolder.
    //Данный метод принимает в себя parent и viewType (используется в том случае, если в списке будут
    //разные типы элементов списка);
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoiskHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_poisk, parent, false)
        return PoiskHolder(view)
    }
    //Метод onBindViewHolder, в котором будет происходить отрисовка всех элементов в объекте списка
    //(у нас это все данные в блоках, категориях и тд):
    override fun onBindViewHolder(holder: PoiskHolder, position: Int) {
        holder.bind(poiskModelList[position], listener)//Получение листа из списка данных по позиции
    }
    fun addPoisk(poisk: BlockModel){
        poiskModelList.add(poisk)
        notifyDataSetChanged()
    }
    interface Listener{
        fun Click(poisk: BlockModel)
    }
}