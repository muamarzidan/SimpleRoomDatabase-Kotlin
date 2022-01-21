package com.example.roomdb_muamarzidantriantoro_21_xirpl5

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.withContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdb_muamarzidantriantoro_21_xirpl5.room.Anime
import com.example.roomdb_muamarzidantriantoro_21_xirpl5.room.AnimeDB
import com.example.roomdb_muamarzidantriantoro_21_xirpl5.room.Constant
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    val db by lazy { AnimeDB(this) }
    lateinit var animeAdapter: AnimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupLisener()
        setupRecyclerView()

    }

    override fun onStart() {
        super.onStart()
        loadAnime()
    }

    fun loadAnime(){
        CoroutineScope(Dispatchers.IO).launch {
            val animes = db.AnimeDao().getAnimes()
            Log.d("MainActivity", "dbresponse $animes")
            withContext(Dispatchers.Main) {
                animeAdapter.setData(animes)
            }
        }
    }


    fun setupLisener() {
        add_anime.setOnClickListener {
            intentAdd(0, Constant.TYPE_CREATE)
        }
    }

    fun intentAdd(animeId: Int, intentType: Int){
        startActivity(
            Intent(applicationContext, AddActivity::class.java)
                .putExtra("intent_id", animeId)
                .putExtra("intent_type", intentType)
        )
    }

    private fun setupRecyclerView(){
        animeAdapter = AnimeAdapter(arrayListOf(), object : AnimeAdapter.OnAdapterListener{
            override fun onClick(anime: Anime) {
                //Toast.makeText(applicationContext, anime.title, Toast.LENGTH_SHORT).show()
                //for read
                intentAdd(anime.id,Constant.TYPE_READ)
            }

            override fun onUpdate(anime: Anime) {
                intentAdd(anime.id,Constant.TYPE_UPDATE)
            }

            override fun onDelete(anime: Anime) {
                deleteDialog(anime)
            }

        })
         rv_anime.apply{
             layoutManager = LinearLayoutManager(applicationContext)
             adapter = animeAdapter
         }
    }

    private fun deleteDialog(anime: Anime) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yang bener loh mau hapus anime ${anime.title}?")
            setNegativeButton("Cancel") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Delete") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.AnimeDao().deleteAnime(anime)
                    loadAnime()
                }
            }

        }
        alertDialog.show()
    }
}