package com.voodoo.sdk.internal.datasource

import com.voodoo.sdk.model.TrackEvent
import com.voodoo.sdk.internal.network.response.AdResponse
import com.voodoo.sdk.internal.network.response.TrackBody
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.head
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AdApiDataSource(
    private val clientApi: HttpClient,
) : ApiDataSource {

    override suspend fun getAdvertisement(url: String): Result<AdResponse> = runCatching {
        clientApi.get<AdResponse>(url = url)
    }

    override suspend fun postTrackEvent(url: String, event: TrackEvent): Result<Unit> =
        runCatching {
            clientApi.head(urlString = url) {
                setBody(TrackBody(event = event))
            }
        }
}

suspend inline fun <reified R> HttpClient.get(url: String): R =
    get(url) {
        contentType(ContentType.Application.Json)
    }.body()
