package name.dmx.androiddevguid

import android.app.Application
import cn.bmob.v3.Bmob

/**
 * Created by dmx on 2017/12/12.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Bmob.initialize(this, "c33ec77b6d3a654581d46b99c6d84f8d")
    }
}