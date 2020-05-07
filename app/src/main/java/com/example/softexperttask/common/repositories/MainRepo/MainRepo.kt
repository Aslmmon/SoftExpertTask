package com.example.softexperttask.common.repositories.MainRepo

import com.example.foodawi.common.repositories.MainRepo.IMain
import com.example.softexperttask.common.rest.MainApi

class MainRepo(var mainApi: MainApi) : IMain {
    override  fun getCars(pageNumber: Number)  = mainApi.getCars(pageNumber)

}