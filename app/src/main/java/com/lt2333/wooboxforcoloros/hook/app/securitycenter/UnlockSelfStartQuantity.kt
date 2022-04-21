package com.lt2333.wooboxforcoloros.hook.app.securitycenter

import android.content.Context
import com.lt2333.wooboxforcoloros.util.findClass
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.hookAfterMethod
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers

object UnlockSelfStartQuantity : HookRegister() {
    override fun init() {
        hasEnable("unlock_self_start_quantity") {
            "com.oplus.safecenter.startupapp.a".findClass(getDefaultClassLoader())
                .hookAfterMethod("b",Context::class.java) {
                    XposedHelpers.setStaticIntField("com.oplus.safecenter.startupapp.a".findClass(getDefaultClassLoader()), "d", 999)
                }
        }
    }
}