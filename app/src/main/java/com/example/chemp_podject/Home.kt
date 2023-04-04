package com.example.chemp_podject

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.GestureDetector
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chemp_podject.Home.Home.listOrder
import com.example.chemp_podject.api.*
import com.example.chemp_podject.databinding.ActivityHomeBinding
import com.example.chemp_podject.databinding.ItemButtonBinding
import com.example.chemp_podject.fragments.ItemListDialogFragment
import com.example.chemp_podject.models.PolzovatModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class Home : AppCompatActivity(), AdapterBlock.Listener, AdapterPoisk.Listener,
    AdapterCategory.Listener {
    private var binding: ActivityHomeBinding? = null
    lateinit var person: PolzovatModel
    private val adapterNews = AdapterNews()
    private val adapterBlock = AdapterBlock(this)
    private val adapterCategory = AdapterCategory(this)
    private val adapterPoisk = AdapterPoisk(this)
    private var categoryList: List<String>? = null
    //var listOrder: List<BlockModel> = ArrayList()

    object Home
    {
        var listOrder: List<BlockModel> = ArrayList()
        var mainPositionCategory = 0
        var listIndexOrder: List<Int> = emptyList()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        getData()
        initSearch()
        goActivity()

        //putPerson()
        /*val GestureDetector = Intent(this @Home, AlterMap::class.java)
        binding!!.LayoutMenuPolzovat.setOnClickListener(){
            val topScreen = Intent(this @Home, AlterMap::class.java)
        }*/
    }

    //Принимаем пользователя
 /*   fun putPerson() {
        person  = Person.person as PolzovatModel
        var y = 5
    //person = intent.getSerializableExtra("person") as PolzovatModel
    }*/
    var intentBasket: Intent? = null
    private fun goActivity() {
        binding!!.MenuIconPolzovat.setOnClickListener() {
            startActivity(Intent(this@Home, AlterMap::class.java))
            finish()
        }
        binding!!.buttonGoBasket.setOnClickListener(){
            startActivity(intentBasket)
            finish()
        }
    }

    private fun initSearch() {
        binding!!.iconLupa.setOnClickListener() {
            with(binding!!) {
                buttonKrestic.visibility = View.VISIBLE
                buttonOtmena.visibility = View.VISIBLE
                Menu.visibility = View.GONE
                PoiskListener.visibility = View.VISIBLE
                Polosa.visibility = View.VISIBLE
            }
        }
        binding!!.buttonOtmena.setOnClickListener() {
            with(binding!!) {
                buttonKrestic.visibility = View.GONE
                buttonOtmena.visibility = View.GONE
                Menu.visibility = View.VISIBLE
                PoiskListener.visibility = View.GONE
                Polosa.visibility = View.GONE
                strokePoisk.clearFocus()
            }
        }
        binding!!.buttonKrestic.setOnClickListener() {
            binding!!.strokePoisk.text = binding!!.strokePoisk.text.dropLast(1) as Editable?
            binding!!.strokePoisk.setSelection(binding!!.strokePoisk.length())
        }/*
        binding!!.listCategory.get(0).setOnClickListener(){
            bindingItemCategory.ButtonCatalog.background = getDrawable(R.drawable.button_home_blue_style)
        }*/
        //
    }

    private fun init(data: List<NewsModel>) {
        with(binding!!) {
            listNews.layoutManager = GridLayoutManager(this@Home, data.size)
            listNews.adapter = adapterNews

            val listCharacter: List<NewsModel> = data
            if (listCharacter.isNotEmpty()) {
                for (element in listCharacter) {
                    adapterNews.addNews(element)
                }
            }
        }
    }

    /*block: List<BlockModel>, blockpoisk: List<BlockModel>*/
    private fun initBlock(block: List<BlockModel>) {
        with(binding!!) {
            listBlock.layoutManager = GridLayoutManager(this@Home, 1)
            listBlock.adapter = adapterBlock
            val listBlock: List<BlockModel> = block
            if (listBlock.isNotEmpty()) {
                for (element in listBlock) {
                    adapterBlock.addBlock(element)
                    /*adapterPoisk.addPoisk(element)*/
                }
            }
            /*if(listPoisk.isNotEmpty()){
                for(element in listPoisk){
                    adapterPoisk.addPoisk(element)
                }
            }*/
        }
    }

    private fun initPoisk(blockPoisk: List<BlockModel>, enteredString: String) {
        with(binding!!) {
            listPoisk.layoutManager = GridLayoutManager(this@Home, 1)
            listPoisk.adapter = adapterPoisk
            Log.d(TAG, blockPoisk.size.toString())
            if (enteredString.isNotEmpty()) {
                val newList =
                    blockPoisk.filter { it.name.contains(enteredString, ignoreCase = true) }
                Log.d(TAG, newList.toString())
                if (newList.isNotEmpty()) {
                    for (element in newList) {
                        adapterPoisk.addPoisk(element)
                        /*if(blockPoisk.filter { it.name.contains(enteredString) }.toString() =) {
                            adapterPoisk.addPoisk(element)
                        }*/
                    }
                }
            }
        }
    }

    lateinit var allBlock: List<BlockModel>
    private fun initCategory(category: List<BlockModel>) {
        with(binding!!) {
            categoryList = category.map { it.category }.toSet().toList()
            listCategory.layoutManager = GridLayoutManager(this@Home, category.size)
            listCategory.adapter = adapterCategory

            if (categoryList!!.isNotEmpty()) {
                for (element in categoryList!!) {
                    adapterCategory.addCategogory(element)
                }
            }
        }
    }

    private fun getData() {
        val api = Retrofit.Builder()
            .baseUrl("https://medic.madskill.ru")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)
        val apiBlock = Retrofit.Builder()
            .baseUrl("https://medic.madskill.ru")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequestBlock::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.getNews().awaitResponse()
                val response2 = apiBlock.getBlock().awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    runOnUiThread { init(data) }
                    Log.d(TAG, data.toString())
                }
                if (response2.isSuccessful) {
                    val data = response2.body()!!
                    runOnUiThread { initCategory(data) }
                    categoryList = data.map { it.category}.toSet().toList()
                    allBlock = data
                    val firstBlock = data.filter { it.category == categoryList!![0] }
                    runOnUiThread { initBlock(firstBlock) }
                    /*runOnUiThread { initPoisk(data) }*/
                    Log.d(TAG, data.toString())
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.d(TAG, e.toString())
                }
            }
        }

        //Данный метод используется с SearchView
        /*binding!!.strokePoisk.setOnQueryTextListener(object: OnQueryTextListener{
            //onQueryTextSubmit - эта штука работает тогда, когда пользователь отправил запрос на поиск,
            //например нажал на кнопку искать(экономит количество запросов на сервер)
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            //onQueryTextChange - эта штука работает всегда, когда пользователь вводит или стирает символы,
            //то есть запрос отправляется каждый раз, когда изменяется строка
            //Что мы делаем здесь: передаем введенный текст(SearchText) на сервер (с помощью getSearchBlock
            //из ApiRequestBlock), чтобы он выдал нам данные, в которых есть совпадения с введённой строкой
            //в переменную response
            //
            override fun onQueryTextChange(SearchText: String?): Boolean {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        //было val searchList = apiBlock.getSearchBlock(SearchText)
                        //потом нажали на подсказку и строка изменилась, здесь проверка
                        //пустое ли поле, которое мы ввели, если нет, то отправляется запрос на сервер
                        val response = SearchText.toString()?.let { apiBlock.getSearchBlock(it).awaitResponse() }
                        //в теории должны получить данные с совпадениями в response
                        if (response != null) {
                            if(response.isSuccessful){
                                val data = response.body()
                                runOnUiThread {
                                    if (data != null) {
                                        //а тут мы эти данные передаем в initPoisk, где с помощью
                                        //адаптера выводим их на экран
                                        initPoisk(data)
                                    }
                                }
                                Log.d(TAG, data.toString())
                            }
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            Log.d(TAG, e.toString())
                        }
                    }
                }
                return true
            }
        })*/

        //Реализуем другой способ, повесим событие на editText, в котором на каждом изменении
        //текста будет вызваться метод, который будет фильтровать массив, полученный из getBlock()
        //по введенной строке. Что нам обязательно нужно: передавать в функцию (fun) введенную строку
        //и массив данных из getBlock() и уже в ней мы будем фильтровать массив по вводу, при этом
        //фильтрованный массив - уже новая переменная, и выводить этот новый массив с помощью адаптера
        binding!!.strokePoisk.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                adapterPoisk.poiskModelList.clear()
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val response = apiBlock.getBlock().awaitResponse()
                        if (response.isSuccessful) {
                            val data = response.body()!!
                            runOnUiThread { initPoisk(data, s.toString()) }
                            Log.d(TAG, data.toString())
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            Log.d(TAG, e.toString())
                        }
                    }
                }
            }
        })
    }

    override fun Click(block: BlockModel) {
        val itemListDialogFragment = ItemListDialogFragment()
        val bundle = Bundle()
        bundle.putSerializable("key", block)
        itemListDialogFragment.arguments = bundle
        itemListDialogFragment.show(supportFragmentManager, "pop")
    }


    override fun Order(block: BlockModel) {
        intentBasket = Intent(this@Home, Basket::class.java)
        listOrder += block
        intentBasket!!.putExtra("order", listOrder as java.io.Serializable)
    }

    override fun deleteOrder(block: BlockModel) {
        listOrder = listOrder - block
    }

    /*override fun OrderPrice() {
        binding!!.textPriceButton.setText("${Basket.SumOrder.summa}  ₽")
    }*/

    override fun Click(category: String, position: Int) {
        adapterBlock.blockModelList.clear()
        var allBlockSort: List<BlockModel> = allBlock.filter { it.category == category }
        initBlock(allBlockSort)
    }

}