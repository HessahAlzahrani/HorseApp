package com.example.horseapp.addPromotion.uiLayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.horseapp.dataLayer.HorsesDataModel
import com.example.horseapp.dataLayer.HorsesDataSource

class HorsesViewModel: ViewModel(){

    //var = listlivedata:listdatamodel
var allItemfromdatasuorse = MutableLiveData<MutableList<HorsesDataModel>>()

    //initialize function inside the class
    init {
        getAllHorsesfun()
    }

    fun getAllHorsesfun(){
        allItemfromdatasuorse.value = HorsesDataSource.resultItemHours

    }



    fun addHorsefun (horsesDataModel: HorsesDataModel){
        allItemfromdatasuorse.value?.add(horsesDataModel)

    }


}