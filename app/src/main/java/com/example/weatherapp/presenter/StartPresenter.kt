import com.example.weatherapp.model.ApiServiceImpl
import com.example.weatherapp.model.data.WeatherData
import com.example.weatherapp.ui.base.fragments.all.start_fragment.IStartView
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class StartPresenter(
    private val view: IStartView
) {
    private val apiService = ApiServiceImpl()

    fun start(country: String) {
        view.showLoading()
        apiService.getCityData(country).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                view.onStartFailure(e.message ?: "Unknown error occurred")
                view.hideLoading()
            }


            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { jsonString ->
                    val data = Gson().fromJson(jsonString, WeatherData::class.java)
                    onWeatherDataReceived(data)
                    view.onStartSuccess()
                }
                view.hideLoading()
            }
        })
    }

    fun onWeatherDataReceived(data: WeatherData) {
        view.navigateToDetailsFragment(data)
    }
}
