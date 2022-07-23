package com.example.superheroapi.data

import com.example.superheroapi.data.network.ApiResponseHandler
import com.example.superheroapi.data.network.SuperHeroService
import com.example.superheroapi.data.network.model.*
import com.example.superheroapi.data.utils.*
import com.example.superheroapi.domain.model.*
import com.example.superheroapi.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import javax.inject.Inject

class SuperHeroRepositoryImpl @Inject constructor(
    private val api: SuperHeroService
) : MovieRepository {

    override suspend fun getSuperHeroFromApi(id: Int): DataState<SuperHeroItem> {
        val networkResult = safeApiCall(Dispatchers.IO) { api.getSuperHero(id) }
        val response = object : ApiResponseHandler<SuperHeroItem, Response<SuperHero>>(
            response = networkResult,
//            stateEvent = null
        ) {
            override suspend fun handleSuccess(resultObj: Response<SuperHero>): DataState<SuperHeroItem> {
                return DataState.Data(
                    response = StateMessage(
                        Response(
                            message = "get from network",
                            messageType = MessageType.Success
                        )
                    ),
                    data = resultObj.body()?.toDomain(),
                )
            }


        }.getResult()

        return response!!

    }

    override suspend fun getSuperHerosFromApi(): DataState<List<SuperHeroItem>> {
        val networkResult = safeApiCall(Dispatchers.IO) { api.getSuperHeros() }


        val response = object : ApiResponseHandler<List<SuperHeroItem>, Response<SuperHeros>>(
            response = networkResult,
//            stateEvent = null
        ) {
            override suspend fun handleSuccess(resultObj: Response<SuperHeros>): DataState<List<SuperHeroItem>> {
                return DataState.Data(
                    response = StateMessage(
                        Response(
                            message = "get from network",
                            messageType = MessageType.Success
                        )
                    ),
                    data = resultObj.body()?.map {
                        it.toDomain()
                    },
                )
            }


        }.getResult()

        return response!!

    }


    override suspend fun getPowerStatsFromApi(id: Int): DataState<PowerStatsItem> {
        val networkResult = safeApiCall(Dispatchers.IO) { api.getPowerStats(id) }
        val response = object : ApiResponseHandler<PowerStatsItem, Response<PowerStats>>(
            response = networkResult,
//            stateEvent = null
        ) {
            override suspend fun handleSuccess(resultObj: Response<PowerStats>): DataState<PowerStatsItem> {
                return DataState.Data(
                    response = StateMessage(
                        Response(
                            message = "get from network",
                            messageType = MessageType.Success
                        )
                    ),
                    data = resultObj.body()?.toDomain(),
                )
            }


        }.getResult()

        return response!!
    }

    override suspend fun getAppearanceFromApi(id: Int): DataState<AppearanceItem> {
        val networkResult = safeApiCall(Dispatchers.IO) { api.getAppearance(id) }
        val response = object : ApiResponseHandler<AppearanceItem, Response<Appearance>>(
            response = networkResult,
//            stateEvent = null
        ) {
            override suspend fun handleSuccess(resultObj: Response<Appearance>): DataState<AppearanceItem> {
                return DataState.Data(
                    response = StateMessage(
                        Response(
                            message = "get from network",
                            messageType = MessageType.Success
                        )
                    ),
                    data = resultObj.body()?.toDomain(),
                )
            }


        }.getResult()

        return response!!
    }

    override suspend fun getBiographyFromApi(id: Int): DataState<BiographyItem> {
        val networkResult = safeApiCall(Dispatchers.IO) { api.getBiography(id) }
        val response = object : ApiResponseHandler<BiographyItem, Response<Biography>>(
            response = networkResult,
//            stateEvent = null
        ) {
            override suspend fun handleSuccess(resultObj: Response<Biography>): DataState<BiographyItem> {
                return DataState.Data(
                    response = StateMessage(
                        Response(
                            message = "get from network",
                            messageType = MessageType.Success
                        )
                    ),
                    data = resultObj.body()?.toDomain(),
                )
            }


        }.getResult()

        return response!!
    }

    override suspend fun getWorkFromApi(id: Int): DataState<WorkItem> {
        val networkResult = safeApiCall(Dispatchers.IO) { api.getWork(id) }
        val response = object : ApiResponseHandler<WorkItem, Response<Work>>(
            response = networkResult,
//            stateEvent = null
        ) {
            override suspend fun handleSuccess(resultObj: Response<Work>): DataState<WorkItem> {
                return DataState.Data(
                    response = StateMessage(
                        Response(
                            message = "get from network",
                            messageType = MessageType.Success
                        )
                    ),
                    data = resultObj.body()?.toDomain(),
                )
            }


        }.getResult()

        return response!!
    }


}



