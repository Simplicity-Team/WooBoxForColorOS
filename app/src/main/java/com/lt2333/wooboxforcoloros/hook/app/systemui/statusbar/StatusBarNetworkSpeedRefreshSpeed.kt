package com.lt2333.wooboxforcoloros.hook.app.systemui.statusbar

import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.hookBeforeMethod
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object StatusBarNetworkSpeedRefreshSpeed : HookRegister() {

    override fun init() {
        "com.oplusos.systemui.statusbar.controller.NetworkSpeedController".hookBeforeMethod(
            getDefaultClassLoader(),
            "postUpdateNetworkSpeedDelay",
            Long::class.java
        ) {
            hasEnable("status_bar_network_speed_refresh_speed") {
                it.args[0] = 1000L
            }
        }
    }

}