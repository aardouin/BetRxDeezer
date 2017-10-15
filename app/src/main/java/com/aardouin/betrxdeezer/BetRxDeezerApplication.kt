package com.aardouin.betrxdeezer

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by WOPATA on 14/10/2017.
 */
class BetRxDeezerApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }

}