package ragavi.com.retrofit25
import retrofit2.Call
import retrofit2.http.GET

interface WineApiService {
    @GET("wines/reds")
    fun getRedWines(): Call<List<Wine>>

    @GET("coffee/hot")
    fun getHotCoffees(): Call<List<Coffee>>
}