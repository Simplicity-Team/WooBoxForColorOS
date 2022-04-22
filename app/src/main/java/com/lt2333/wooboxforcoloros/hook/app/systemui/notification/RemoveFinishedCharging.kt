package com.lt2333.wooboxforcoloros.hook.app.systemui.notification

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object RemoveFinishedCharging : HookRegister() {
    override fun init() = hasEnable("remove_finished_charging") {
        findMethod("com.oplusos.systemui.notification.power.OplusPowerNotificationWarnings") {
            name == "showChargeErrorDialog" && parameterTypes[0] == Int::class.java
        }.hookBefore {
            if (it.args[0] as Int == 7) it.result = null
        }
    }
}