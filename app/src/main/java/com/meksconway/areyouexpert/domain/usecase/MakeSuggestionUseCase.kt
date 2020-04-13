package com.meksconway.areyouexpert.domain.usecase

import com.meksconway.areyouexpert.domain.repository.MakeSuggestionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MakeSuggestionUseCase
@Inject constructor(private val repository: MakeSuggestionRepository){

}