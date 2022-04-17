package com.lt2333.wooboxforcoloros.hook.app.systemui

import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.hookBeforeMethod
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object HidePromptView : HookRegister() {

    override fun init() {
        hasEnable("hide_prompt_view") {
            "com.oplusos.systemui.statusbar.widget.SystemPromptView".hookBeforeMethod(
                getDefaultClassLoader(),
                "updateViewVisible"
            ) {
                it.result = null
            }

            "com.oplusos.systemui.statusbar.widget.SystemPromptView".hookBeforeMethod(
                getDefaultClassLoader(),
                "setViewVisibleByDisable", Boolean::class.java
            ) {
                it.args[0] = true
            }
        }
    }
}