package com.lt2333.wooboxforcoloros.hook.app.systemui.qs

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object RemoveFooterSecurityWarn : HookRegister() {
    override fun init() = hasEnable("remove_footer_security_warn") {
        findMethod("com.oplusos.systemui.qs.widget.OplusQSSecurityText") {
            name == "handleRefreshState"
        }.hookReturnConstant(null)
    }
}