package com.lt2333.wooboxforcoloros.hook.app.systemui.qs

import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.hookBeforeMethod
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object RemoveFooterSecurityWarn : HookRegister() {

    override fun init() {
        hasEnable("remove_footer_security_warn") {
            "com.oplusos.systemui.qs.widget.OplusQSSecurityText".hookBeforeMethod(
                getDefaultClassLoader(),
                "shouldBeVisible",
            ) {
                //未展开时的列数
                it.result = false
            }
        }
    }
}