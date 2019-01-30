package com.javagic.smartalarmclock.music

import android.app.Activity
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.javagic.smartalarmclock.R
import com.javagic.smartalarmclock.base.BaseActivity
import com.javagic.smartalarmclock.utils.ItemDecorator
import com.javagic.smartalarmclock.utils.ext.viewModel
import kotlinx.android.synthetic.main.activity_select_music.*
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator
import me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter


class SelectMusicActivity : BaseActivity<SelectMusicViewModel>() {
  private var player = MediaPlayer().apply {
    isLooping = true//TODO not working
    setAudioAttributes(AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_MEDIA)
        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
        .build())
  }

  override fun provideViewModel(): SelectMusicViewModel = viewModel()
  private val musicAdapter = MusicAdapter {
    viewModel.selectedItem.postValue(it)
  }

  override fun onBackPressed() {
    player.release()
    setResult(Activity.RESULT_OK, Intent().apply {
      putExtra(EXTRA_MUSIC_ITEM, viewModel.selectedItem.value)
    })
    finish()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_select_music)
    rvMusic.apply {
      layoutManager = LinearLayoutManager(this@SelectMusicActivity)
      adapter = musicAdapter
      addItemDecoration(ItemDecorator(itemOffset = 15))
      VerticalOverScrollBounceEffectDecorator(RecyclerViewOverScrollDecorAdapter(this))
    }
    viewModel.music.observe {
      musicAdapter.submitList(it)
    }
    viewModel.selectedItem.observe {
      player.apply {
        reset()
        setDataSource(this@SelectMusicActivity, Uri.parse(it.uri))
        prepare()
        start()
      }
    }
  }

  override fun onStop() {
    super.onStop()
    player.release()
  }
}