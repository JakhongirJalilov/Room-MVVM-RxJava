package com.example.testroommvvm

import android.app.Application
import com.example.testroommvvm.db.AppDatabase

/**
 * @author jakhongirjalilov
 * @version 2.0
 * @data 2/26/21
 * @project Test Room MVVM
 */
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
    }
}