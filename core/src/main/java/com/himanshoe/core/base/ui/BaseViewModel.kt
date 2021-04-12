package com.himanshoe.core.base.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himanshoe.core.base.mvi.MviEffect
import com.himanshoe.core.base.mvi.MviEvent
import com.himanshoe.core.base.mvi.MviState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


/**
 * Created by Himanshu Singh on 12-04-2021.
 * hello2himanshusingh@gmail.com
 */
abstract class BaseViewModel<Event : MviEvent, State : MviState, Effect : MviEffect> : ViewModel() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    val currentState: State
        get() = uiState.value

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()


    init {
        subscribeEvents()
    }

    /**
     * Start listening to Event
     */
    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect {
                handleEvent(it)
            }
        }
    }

    /**
     * Handle each event
     */
    abstract fun handleEvent(event: Event)


    /**
     * Set new Event
     */
    fun setEvent(event: Event) {
        val newEvent = event
        viewModelScope.launch { _event.emit(newEvent) }
    }


    /**
     * Set new Ui State
     */
    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    /**
     * Set new Effect
     */
    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }
}