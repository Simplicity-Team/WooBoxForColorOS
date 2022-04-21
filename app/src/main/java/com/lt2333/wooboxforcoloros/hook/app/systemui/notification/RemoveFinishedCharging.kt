package com.lt2333.wooboxforcoloros.hook.app.systemui.notification

import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.hookBeforeMethod
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object RemoveFinishedCharging : HookRegister() {

    override fun init() {
        hasEnable("remove_finished_charging") {
            "com.oplusos.systemui.notification.power.OplusPowerNotificationWarnings".hookBeforeMethod(
                getDefaultClassLoader(),
                "showChargeErrorDialog",
                Int::class.java
            ) {
                if (it.args[0] as Int == 7) it.result = null
            }
        }
    }
}