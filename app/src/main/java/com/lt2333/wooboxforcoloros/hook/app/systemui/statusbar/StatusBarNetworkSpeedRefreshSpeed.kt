package com.lt2333.wooboxforcoloros.hook.app.systemui.statusbar

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object StatusBarNetworkSpeedRefreshSpeed : HookRegister() {
    override fun init() = hasEnable("status_bar_network_speed_refresh_speed") {
        findMethod("com.oplusos.systemui.statusbar.controller.NetworkSpeedController") {
            name == "postUpdateNetworkSpeedDelay" && parameterTypes[0] == Long::class.java
        }.hookBefore {
            it.args[0] = 1000L
        }
    }
}