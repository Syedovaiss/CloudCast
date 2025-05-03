package com.ovais.cloudcast.core.domain.usecase

fun interface SuspendParameterizedUseCase<Param, ReturnType> {
    suspend operator fun invoke(param: Param): ReturnType
}

fun interface SuspendUseCase<ReturnType> {
    suspend operator fun invoke(): ReturnType
}
