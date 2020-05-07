package com.example.softexperttask.common.rest
import com.example.softexperttask.common.model.CarsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET("/api/v1/cars}")
     fun getCars(@Query("page") pageNumber: Number): Single<CarsResponse>


}