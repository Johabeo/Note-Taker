package com.example.project0note_taker
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CarViewModel(private val repository: CarRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allCars: LiveData<List<Car>> = repository.allCars.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(car: Car) = viewModelScope.launch {
        repository.insert(car)
    }
}

class CarViewModelFactory(private val repository: CarRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CarViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}