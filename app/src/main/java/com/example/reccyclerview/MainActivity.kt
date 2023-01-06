package com.example.reccyclerview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reccyclerview.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var superHeros: List<SuperHero> = listOf(


    SuperHero("Batman", "Marvel", "Bruce Wayne", "https://image.api.playstation.com/vulcan/img/rnd/202010/2621/H9v5o8vP6RKkQtR77LIGrGDE.png"),
    SuperHero("Spiderman", "Marvel", "Peter Parker", "https://www.muycomputer.com/wp-content/uploads/2021/08/Spider-Man.jpg"),
    SuperHero("Superman", "Marvel", "Clark Kent", "https://imagenes.20minutos.es/files/image_656_370/files/fp/uploads/imagenes/2021/07/06/christopher-reeve-en-superman-richard-donner-1978.r_d.816-319.jpeg"),
    SuperHero("Batman", "Marvel", "Bruce Wayne", "https://image.api.playstation.com/vulcan/img/rnd/202010/2621/H9v5o8vP6RKkQtR77LIGrGDE.png"),
    SuperHero("Spiderman", "Marvel", "Peter Parker", "https://www.muycomputer.com/wp-content/uploads/2021/08/Spider-Man.jpg"),
    SuperHero("Superman", "Marvel", "Clark Kent", "https://imagenes.20minutos.es/files/image_656_370/files/fp/uploads/imagenes/2021/07/06/christopher-reeve-en-superman-richard-donner-1978.r_d.816-319.jpeg"),
    SuperHero("Batman", "Marvel", "Bruce Wayne", "https://image.api.playstation.com/vulcan/img/rnd/202010/2621/H9v5o8vP6RKkQtR77LIGrGDE.png"),
    SuperHero("Spiderman", "Marvel", "Peter Parker", "https://www.muycomputer.com/wp-content/uploads/2021/08/Spider-Man.jpg"),
    SuperHero("Superman", "Marvel", "Clark Kent", "https://imagenes.20minutos.es/files/image_656_370/files/fp/uploads/imagenes/2021/07/06/christopher-reeve-en-superman-richard-donner-1978.r_d.816-319.jpeg"),
    SuperHero("Batman", "Marvel", "Bruce Wayne", "https://image.api.playstation.com/vulcan/img/rnd/202010/2621/H9v5o8vP6RKkQtR77LIGrGDE.png"),
    SuperHero("Spiderman", "Marvel", "Peter Parker", "https://www.muycomputer.com/wp-content/uploads/2021/08/Spider-Man.jpg"),
    SuperHero("Superman", "Marvel", "Clark Kent", "https://imagenes.20minutos.es/files/image_656_370/files/fp/uploads/imagenes/2021/07/06/christopher-reeve-en-superman-richard-donner-1978.r_d.816-319.jpeg")
)


class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: DogAdapter
    private val dogImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svDogs.setOnQueryTextListener(this)
        initRecycler()


    }

    fun initRecycler(){

        adapter = DogAdapter(dogImages)
        binding.rvDogs.layoutManager = LinearLayoutManager (this)
        binding.rvDogs.adapter = adapter

    }


// Esta instancia de Retrofit que vamos a crear será la que tenga el resto de la url del endpoint, se encargará de convertir
// el JSON a DogResponse y tendrá toda la configuración para hacer la llamada del API.
    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
//Ahora crearemos una función que recibirá una String por parámetro, ya que al final de la app esta función será llamada por el buscador y
// le mandará la raza de perro que ha buscado.
//Lo primero que hacemos dentro del método es llamar a CoroutineScope(Dispatchers.IO).launch{}
// esto hará que all lo que esté dentro de esas llaves de genere en un hilo asíncrono.
    @SuppressLint("NotifyDataSetChanged")
    private fun searchByName(query:String){
        //T0d0 lo lanzado aquí se realiza en un hilo secundario
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getDogsByBreeds("$query/images")
            val puppies = call.body()
            //Para poder salir de esa corrutina utilizaremos runOnUiThread{} y all lo que esté entre esas llaves se hará en el hilo principal aunque esté dentro de una corrutina.
            runOnUiThread {
                if (call.isSuccessful) {
                    val images = puppies?.images ?: emptyList()
                    dogImages.clear()
                    dogImages.addAll(images)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
                hideKeyboard()
            }
        }
        }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchByName(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true

    }


}


