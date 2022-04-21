package com.lt2333.wooboxforcoloros.hook.app.systemui.notification

import android.content.Context
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.hookBeforeMethod
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object RemoveUSBDebugging : HookRegister() {
    override fun init() {
        hasEnable("remove_usb_debugging") {
            "com.oplusos.systemui.notification.usb.UsbService".hookBeforeMethod(
                getDefaultClassLoader(),
                "isAdbDebugEnable",
                Context::class.java
            ) {
                it.result = false
            }
        }
    }
}