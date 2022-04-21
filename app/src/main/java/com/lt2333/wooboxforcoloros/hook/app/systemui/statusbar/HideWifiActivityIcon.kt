package com.lt2333.wooboxforcoloros.hook.app.systemui.statusbar

import android.view.View
import android.widget.ImageView
import com.lt2333.wooboxforcoloros.util.getObjectField
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.hookAfterMethod
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object HideWifiActivityIcon : HookRegister() {

    override fun init() {
        "com.oplusos.systemui.statusbar.OplusStatusBarWifiView".hookAfterMethod(
            getDefaultClassLoader(),
            "initViewState"
        ) {
            //隐藏WIFI箭头
            hasEnable("hide_wifi_activity_icon") {
                (it.thisObject.getObjectField("mWifiActivity") as ImageView).visibility =
                    View.GONE
            }
        }

        "com.oplusos.systemui.statusbar.OplusStatusBarWifiView".hookAfterMethod(
            getDefaultClassLoader(),
            "updateState",
            "com.oplusos.systemui.ext.StatusBarSignalPolicyExt.Companion.WifiIconStateEx"
        ) {
            //隐藏WIFI箭头
            hasEnable("hide_wifi_activity_icon") {
                (it.thisObject.getObjectField("mWifiActivity") as ImageView).visibility =
                    View.GONE
            }
        }
    }

}