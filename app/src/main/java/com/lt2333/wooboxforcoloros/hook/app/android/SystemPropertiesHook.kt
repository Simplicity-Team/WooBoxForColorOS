package com.lt2333.wooboxforcoloros.hook.app.android

import com.lt2333.wooboxforcoloros.util.XSPUtils
import com.lt2333.wooboxforcoloros.util.hookBeforeMethod
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object SystemPropertiesHook : HookRegister() {
    override fun init() {
        val mediaStepsSwitch = XSPUtils.getBoolean("media_volume_steps_switch", false)
        val mediaSteps = XSPUtils.getInt("media_volume_steps", 30)

        "android.os.SystemProperties".hookBeforeMethod(
            getDefaultClassLoader(),
            "getInt",
            String::class.java,
            Int::class.java
        ) {
            when (it.args[0] as String) {
                "ro.config.media_vol_steps" -> if (mediaStepsSwitch) it.result = mediaSteps
            }
        }

    }
}