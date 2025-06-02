package com.klivvr.city.domain

import com.klivvr.core.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: CityIRepo, @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): List<CityDomainModel> = withContext(dispatcher) {
        repository.getCities()
    }
}
