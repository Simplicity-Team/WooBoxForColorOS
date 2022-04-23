package com.lt2333.wooboxforcoloros.hook.app.systemui.notification

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object RemoveDevModeIsOn : HookRegister() {
    override fun init() = hasEnable("remove_dev_mode_is_on") {
        findMethod("com.oplusos.systemui.statusbar.policy.SystemPromptController") {
            name == "updateDeveloperMode"
        }.hookReturnConstant(null)
    }
}