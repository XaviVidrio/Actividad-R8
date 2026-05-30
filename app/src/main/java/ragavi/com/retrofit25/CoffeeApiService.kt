package ragavi.com.retrofit25

import retrofit2.Call
import retrofit2.http.GET

interface CoffeeApiService {
    @GET("coffee/hot") // Este es el pedazo final de la URL
    fun getHotCoffees(): Call<List<Coffee>>
}