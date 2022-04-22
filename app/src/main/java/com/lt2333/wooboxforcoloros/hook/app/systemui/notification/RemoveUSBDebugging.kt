package com.lt2333.wooboxforcoloros.hook.app.systemui.notification

import android.content.Context
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object RemoveUSBDebugging : HookRegister() {
    override fun init() = hasEnable("remove_usb_debugging") {
        findMethod("com.oplusos.systemui.notification.usb.UsbService") {
            name == "isAdbDebugEnable" && parameterTypes[0] == Context::class.java
        }.hookReturnConstant(false)
    }
}