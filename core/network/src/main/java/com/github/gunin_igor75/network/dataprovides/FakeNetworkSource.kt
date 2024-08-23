package com.github.gunin_igor75.network.dataprovides

import android.content.Context
import com.github.gunin_igor75.common.base.utils.Utils
import com.github.gunin_igor75.network.NetworkSource
import com.github.gunin_igor75.network.dto.DataContainer
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class FakeNetworkSource(
    private val context: Context,

    ) : NetworkSource<DataContainer> {

    override suspend fun fetchData(): Response<DataContainer> {
        return try {
            val data = Utils.getData(
                context = context,
                path = PATH,
                clazz = DataContainer::class.java
            )
            Response.success(data)
        } catch (e: Exception) {
            val message = e.message ?: MESSAGE
            val errorResponseBode = message.toResponseBody(MEDIA_TYPE.toMediaTypeOrNull())
            Response.error(CODE_ERROR, errorResponseBode)
        }
    }


    private companion object {
        const val PATH = "offers.json"
        const val MESSAGE = "What you were looking for isn't here."
        const val MEDIA_TYPE = "text/plain"
        const val CODE_ERROR = 400
    }
}
