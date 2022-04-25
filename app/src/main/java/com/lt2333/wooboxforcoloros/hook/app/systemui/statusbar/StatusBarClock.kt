package com.lt2333.wooboxforcoloros.hook.app.systemui.statusbar

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object StatusBarClock : HookRegister() {
    override fun init() = hasEnable("dropdown_status_bar_clock_display_seconds"){
        findMethod("com.android.systemui.statusbar.policy.Clock"){
            name == "setShowSecondsAndUpdate" && parameterTypes[0] == Boolean::class.java
        }.hookBefore {
            it.args[0] = true
        }
    }
}