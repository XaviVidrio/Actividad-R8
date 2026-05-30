package ragavi.com.retrofit25

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnLoadWines: Button
    private lateinit var btnLoadCoffee: Button // Nuevo botón

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Vincular vistas
        recyclerView = findViewById(R.id.recyclerViewWines)
        btnLoadWines = findViewById(R.id.btnLoad)
        // btnLoadCoffee = findViewById(R.id.btnLoadCoffee) // Asegúrate de agregarlo a tu XML

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Escuchadores
        btnLoadWines.setOnClickListener {
            loadWines()
        }

        /* btnLoadCoffee.setOnClickListener {
            loadCoffees()
        }
        */
    }

    private fun loadWines() {
        RetrofitClient.instance.getRedWines()
            .enqueue(object : Callback<List<Wine>> {
                override fun onResponse(call: Call<List<Wine>>, response: Response<List<Wine>>) {
                    if (response.isSuccessful) {
                        val wines = response.body() ?: emptyList()
                        recyclerView.adapter = WineAdapter(wines)
                    } else {
                        Toast.makeText(this@MainActivity, "Error en vinos", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<List<Wine>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Falla: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
    }

    // NUEVA FUNCIÓN PARA LA OTRA API
    private fun loadCoffees() {
        RetrofitClient.instance.getHotCoffees()
            .enqueue(object : Callback<List<Coffee>> {
                override fun onResponse(call: Call<List<Coffee>>, response: Response<List<Coffee>>) {
                    if (response.isSuccessful) {
                        val coffees = response.body() ?: emptyList()
                        // Aquí necesitarías un CoffeeAdapter para mostrar los datos del café
                        // recyclerView.adapter = CoffeeAdapter(coffees)
                        Toast.makeText(this@MainActivity, "Cafés cargados: ${coffees.size}", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<List<Coffee>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error Café: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
    }
}