package com.javagic.smartalarmclock.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import org.devlee.kotlin.mvi.presenter.BaseMviPresenter
import org.devlee.kotlin.mvi.view.state.ViewState


abstract class BaseMviActivity<V : BaseMviView<VS>, VS : ViewState>(@LayoutRes private val layout: Int) : AppCompatActivity() {

    protected abstract val mviView: V
    protected abstract val mviPresenter: BaseMviPresenter<V, VS>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
    }

    override fun onStart() {
        super.onStart()
        mviPresenter.attachView(mviView)
    }

    override fun onStop() {
        super.onStop()
        mviPresenter.detachView()
    }

    override fun onDestroy() {
        mviView.onDestroy()
        mviPresenter.destroy()
        super.onDestroy()
    }

    override fun onBackPressed() {
        mviView.onBackPressed()
        super.onBackPressed()
    }

}