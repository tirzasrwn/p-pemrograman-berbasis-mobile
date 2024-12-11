package id.tirzasrwn.amphibians

import android.app.Application
import id.tirzasrwn.amphibians.data.AppContainer
import id.tirzasrwn.amphibians.data.DefaultAppContainer

class AmphibiansApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
