package com.lt2333.wooboxforcoloros.hook.app.android

import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.hookBeforeMethod
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object DisableFlagSecure : HookRegister() {

    override fun init() {
        "com.android.server.wm.WindowState".hookBeforeMethod(getDefaultClassLoader(), "isSecureLocked") {
            hasEnable("disable_flag_secure") {
                it.result = false
            }
        }
    }

}