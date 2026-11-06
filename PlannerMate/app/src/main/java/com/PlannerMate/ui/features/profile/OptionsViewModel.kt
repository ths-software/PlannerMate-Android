package com.PlannerMate.ui.features.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class OptionsViewModel @Inject constructor() : ViewModel() {

    fun onOptionsEvent(optionsEvent: OptionsEvent)
    {
        when(optionsEvent){
            is OptionsEvent.OnClickBack -> {
                optionsEvent.onNavigateProfile
            }
        }

    }


}