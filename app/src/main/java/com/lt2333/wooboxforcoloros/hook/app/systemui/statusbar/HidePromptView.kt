package com.lt2333.wooboxforcoloros.hook.app.systemui.statusbar

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object HidePromptView : HookRegister() {
    override fun init() = hasEnable("hide_prompt_view") {
        findMethod("com.oplusos.systemui.statusbar.widget.SystemPromptView") {
            name == "updateViewVisible"
        }.hookReturnConstant(null)

        findMethod("com.oplusos.systemui.statusbar.widget.SystemPromptView") {
            name == "setViewVisibleByDisable"
        }.hookBefore {
            it.args[0] = true
        }
    }
}