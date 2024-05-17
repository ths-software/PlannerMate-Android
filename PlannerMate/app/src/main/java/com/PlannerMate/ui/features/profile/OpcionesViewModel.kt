package com.PlannerMate.ui.features.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class OpcionesViewModel @Inject constructor() : ViewModel() {

    fun onOpcionesEvent(opcionesEvent: OpcionesEvent)
    {
        when(opcionesEvent){
            is OpcionesEvent.OnClickBack -> {
                opcionesEvent.onNavigatePerfil
            }
        }

    }


}