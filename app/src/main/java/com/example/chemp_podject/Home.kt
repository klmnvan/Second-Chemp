package com.example.chemp_podject

import Person
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
    private val adapterNews = AdapterNews()
    private val adapterBlock = AdapterBlock(this)
    private val adapterCategory = AdapterCategory(this)
    private val adapterPoisk = AdapterPoisk(this)
    private var categoryList: List<String>? = null

    object Home
    {
        var mainPositionCategory = 0
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
            startActivity(Intent(this@Home, Basket::class.java))
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
        }
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
    private fun initBlock(block: List<BlockModel>) {
        with(binding!!) {
            listBlock.layoutManager = GridLayoutManager(this@Home, 1)
            listBlock.adapter = adapterBlock
            val listBlock: List<BlockModel> = block
            if (listBlock.isNotEmpty()) {
                for (element in listBlock) {
                    adapterBlock.addBlock(element)
                }
            }
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
        Person.listOrder += block
        //intentBasket!!.putExtra("order", Person.listOrder as java.io.Serializable)
    }

    override fun deleteOrder(block: BlockModel) {
        Person.listOrder = Person.listOrder - block
    }

    override fun Click(category: String, position: Int) {
        adapterBlock.blockModelList.clear()
        var allBlockSort: List<BlockModel> = allBlock.filter { it.category == category }
        initBlock(allBlockSort)
    }

}