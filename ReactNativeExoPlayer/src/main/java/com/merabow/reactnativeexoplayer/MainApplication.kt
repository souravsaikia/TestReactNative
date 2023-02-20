package com.merabow.reactnativeexoplayer

import android.app.Application
import com.facebook.react.ReactApplication
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.shell.MainReactPackage
import com.merabow.reactnativeexoplayer.MyPackage
import java.util.*

class MainApplication : Application(), ReactApplication {
    private val mReactNativeHost: ReactNativeHost = object : ReactNativeHost(this) {
        override fun getPackages(): List<ReactPackage> {
            return Arrays.asList(
                MainReactPackage(),
                MyPackage()
            )
        }

        override fun getUseDeveloperSupport(): Boolean {
            return false
        }
    }

    override fun getReactNativeHost(): ReactNativeHost {
        return mReactNativeHost
    }
}