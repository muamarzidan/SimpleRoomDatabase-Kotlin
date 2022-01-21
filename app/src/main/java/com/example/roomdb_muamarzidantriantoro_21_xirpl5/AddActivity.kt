package com.example.roomdb_muamarzidantriantoro_21_xirpl5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.roomdb_muamarzidantriantoro_21_xirpl5.room.Anime
import com.example.roomdb_muamarzidantriantoro_21_xirpl5.room.AnimeDB
import com.example.roomdb_muamarzidantriantoro_21_xirpl5.room.Constant
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddActivity : AppCompatActivity() {

    val db by lazy { AnimeDB(this) }
    private var animeId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setupView()
        setupListener()
        animeId = intent.getIntExtra("intent_id", 0)

        //Toast.makeText(this, animeId.toString(), Toast.LENGTH_SHORT).show()
    }

    fun setupView(){
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                btn_update.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btn_save.visibility = View.GONE
                btn_update.visibility = View.GONE
                getAnime()
            }
            Constant.TYPE_UPDATE -> {
                btn_save.visibility = View.GONE
                getAnime()
            }
        }
    }

    fun setupListener(){
        btn_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.AnimeDao().addAnime(
                    Anime(0,et_title.text.toString(), et_desk.text.toString())
                )
                finish()
            }
        }
        btn_update.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.AnimeDao().updateAnime(
                    Anime(animeId,et_title.text.toString(),
                        et_desk.text.toString())
                )
                finish()
            }
        }
    }

    fun getAnime(){
        animeId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val animes = db.AnimeDao().getAnime( animeId ) [0]
            et_title.setText( animes.title )
            et_desk.setText( animes.desc )
        }
    }

    //override fun onSupportNavigateUp(): Boolean {
        //onBackPressed()
        //return super.onSupportNavigateUp()}
}