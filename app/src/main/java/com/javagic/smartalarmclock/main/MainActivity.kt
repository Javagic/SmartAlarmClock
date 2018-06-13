package com.javagic.smartalarmclock.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.base.BaseMviActivity

import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : BaseMviActivity<ILoginView, LoginViewState>(R.layout.login_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private val clRootView by view<View>(R.id.clRootView)
    private val navigator by lazy { appComponent.navigator }
    private val progressDialog by lazy { progressDialog("") }
    private val updateProgressDialog by lazy { progressDialog("",false) }

    override val mviView by lazy { LoginView(this, clRootView, progressDialog,updateProgressDialog) { finish() } }
    override val mviPresenter by lazy { appComponent.login.component.loginPresenter }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
    }

    override fun onStart() {
        super.onStart()
        navigator.loginOpened()
    }

    override fun onStop() {
        if (progressDialog.isShowing) progressDialog.dismiss()
        if (updateProgressDialog.isShowing) updateProgressDialog.dismiss()
        super.onStop()
    }

}
