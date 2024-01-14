package com.ezzy.data.domain.usecase

import com.ezzy.data.domain.model.Property
import com.ezzy.data.domain.repository.ListingsRepository
import com.ezzy.data.utils.StateWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetAllListingUseCase @Inject constructor(
    private val listingsRepository: ListingsRepository
) {

    suspend operator fun invoke(): Flow<StateWrapper<List<Property>>> {
        return withContext(Dispatchers.IO) {
            flow {
                try {
                    val listings = listingsRepository.getAllListings()
                    emit(StateWrapper.Success(listings))
                } catch (th: Throwable) {
                    emit(StateWrapper.Failure(th.message ?: "An Error Occurred"))
                }

            }.onStart {
                emit(StateWrapper.Loading)
            }.catch {
                emit(StateWrapper.Failure(it.message ?: "An Error Occurred"))
            }
        }
    }

}