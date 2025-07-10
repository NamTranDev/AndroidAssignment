package nam.tran.home.assignment.jetpack.compose.data.network

import com.google.gson.*
import nam.tran.home.assignment.jetpack.compose.model.response.BaseResponse
import java.lang.reflect.Type

class BaseResponseDeserializer : JsonDeserializer<BaseResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): BaseResponse {
        return BaseResponse()
    }
}