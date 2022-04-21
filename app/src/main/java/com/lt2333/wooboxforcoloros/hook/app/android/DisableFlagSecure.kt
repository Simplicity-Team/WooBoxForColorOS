package com.lt2333.wooboxforcoloros.hook.app.android

import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.hookBeforeMethod
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object DisableFlagSecure : HookRegister() {

    override fun init() {
        hasEnable("disable_flag_secure") {
            "com.android.server.wm.WindowState".hookBeforeMethod(getDefaultClassLoader(), "isSecureLocked") {
                it.result = false
            }
        }
    }

}