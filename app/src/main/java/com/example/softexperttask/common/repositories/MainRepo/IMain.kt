package com.example.foodawi.common.repositories.MainRepo

import com.example.foodawi.common.bases.ResponseWrapper
import com.example.softexperttask.common.model.CarsResponse
import io.reactivex.Single

interface IMain {

     fun getCars(pageNumber: Number): Single<CarsResponse>
}