package com.lt2333.wooboxforcoloros.hook.app.systemui.lockscreen

import com.github.kyuubiran.ezxhelper.utils.loadClass
import com.github.kyuubiran.ezxhelper.utils.putStaticObject
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object RemoveRedOne : HookRegister() {
    override fun init() = hasEnable("remove_red_one") {
        loadClass("com.oplusos.systemui.keyguard.clock.RedTextClock").putStaticObject("NUMBER_ONE", "")
    }
}