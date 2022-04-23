package com.lt2333.wooboxforcoloros.hook.app.systemui.features

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object ChargingRipple : HookRegister() {
    override fun init() = hasEnable("enable_charging_ripple") {
        findMethod("com.android.systemui.statusbar.FeatureFlags") {
            name == "isChargingRippleEnabled"
        }.hookReturnConstant(true)
    }
}