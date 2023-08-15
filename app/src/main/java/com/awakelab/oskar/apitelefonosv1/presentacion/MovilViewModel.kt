package com.awakelab.oskar.apitelefonosv1.presentacion

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.awakelab.oskar.apitelefonosv1.data.Repository
import com.awakelab.oskar.apitelefonosv1.data.local.MovilDataBase
import com.awakelab.oskar.apitelefonosv1.data.remote.DetalleRetroFit
import com.awakelab.oskar.apitelefonosv1.data.remote.MovilRetroFit
import kotlinx.coroutines.launch

class MovilViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository

    fun movilLiveData() = repository.obtenerMovilEntity()

    init {
        val api = MovilRetroFit.getRetrofitMovil()
        val dao = MovilDataBase.getDatabase(application).getMovilDao()
        val dApi = DetalleRetroFit.getRetrofitDetalle()
        repository = Repository(api, dao,dApi)
    }

    fun getAllMoviles() = viewModelScope.launch {
        repository.obtenerMoviles()
    }


}