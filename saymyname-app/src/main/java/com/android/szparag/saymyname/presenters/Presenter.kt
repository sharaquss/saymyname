package com.android.szparag.saymyname.presenters

import com.android.szparag.saymyname.views.contracts.View

/**
 * Created by Przemyslaw Jablonski (github.com/sharaquss, pszemek.me) on 7/4/2017.
 */
interface Presenter {


  fun attach(view: View)

  fun onAttached()

  fun onViewReady()

  fun detach()
  fun onBeforeDetached()

}