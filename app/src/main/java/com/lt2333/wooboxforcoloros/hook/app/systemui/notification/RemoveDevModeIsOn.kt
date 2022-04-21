package com.lt2333.wooboxforcoloros.hook.app.systemui.notification

import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.hookBeforeMethod
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object RemoveDevModeIsOn : HookRegister() {
    override fun init() {
        hasEnable("remove_dev_mode_is_on") {
            "com.oplusos.systemui.statusbar.policy.SystemPromptController".hookBeforeMethod(
                getDefaultClassLoader(),
                "updateDeveloperMode"
            ) {
                it.result = null
            }
        }
    }
}