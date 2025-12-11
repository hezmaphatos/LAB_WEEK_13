package com.example.lab_week_13

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_week_13.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    init {
        fetchPopularMovies()
    }

    // define the LiveData
    private val _popularMovies = MutableStateFlow(
        emptyList<Movie>()
    )
    val popularMovies: StateFlow<List<Movie>> = _popularMovies
    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    // fetch movies from the API
    private fun fetchPopularMovies() {
        // launch a coroutine in viewModelScope
        // Dispatchers.IO means that this coroutine will run on a shared pool of threads
        viewModelScope.launch(Dispatchers.IO) {

            movieRepository.fetchMovies().catch {
                // catch is a terminal operator that catches exceptions from the Flow
                _error.value = "An exception occurred: ${it.message}"
            }.collect { movies ->
                // Sorted by popularity (descending)
                val sorted = movies.sortedBy { it.popularity }

                // collect is a terminal operator that collects the values from the Flow
                // the results are emitted to the StateFlow
                _popularMovies.value = sorted
            }
        }
    }


}