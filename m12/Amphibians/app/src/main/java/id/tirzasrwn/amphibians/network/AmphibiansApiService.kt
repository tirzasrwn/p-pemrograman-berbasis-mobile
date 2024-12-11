package id.tirzasrwn.amphibians.network

import id.tirzasrwn.amphibians.model.Amphibian
import retrofit2.http.GET

// Retrofit
interface AmphibiansApiService {
    // 3
    // http get request to base_url/amphibians
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}
