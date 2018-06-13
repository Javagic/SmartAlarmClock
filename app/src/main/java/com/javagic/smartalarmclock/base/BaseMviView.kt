package com.javagic.smartalarmclock.base

import org.devlee.kotlin.mvi.view.MviView
import org.devlee.kotlin.mvi.view.state.ViewState

interface BaseMviView<in VS : ViewState> : MviView<VS> {

    fun onDestroy()

    fun onBackPressed()
}