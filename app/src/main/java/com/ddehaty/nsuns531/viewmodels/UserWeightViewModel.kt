package com.ddehaty.nsuns531.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ddehaty.nsuns531.User
import com.ddehaty.nsuns531.db.NsunsDatabase
import com.ddehaty.nsuns531.db.WeightRepository
import kotlinx.coroutines.launch

class UserWeightViewModel(application: Application) : AndroidViewModel(application) {

    val weights: LiveData<List<User>> = WeightRepository(NsunsDatabase(application)).getAllUserWeights()

    fun deleteWeight(id: Int){
        viewModelScope.launch {
            WeightRepository(NsunsDatabase(getApplication())).deleteWeightUser(id)
            println("MAZEM NA $id")
        }
    }

}