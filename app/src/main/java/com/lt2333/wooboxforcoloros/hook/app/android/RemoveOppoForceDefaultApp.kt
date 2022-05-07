package com.lt2333.wooboxforcoloros.hook.app.android

import android.content.ComponentName
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReturnConstant
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object RemoveOppoForceDefaultApp : HookRegister() {
    override fun init() = hasEnable("remove_oppo_default_app") {
        findMethod("com.android.server.pm.OplusOsPackageManagerHelper") {
            name == "isOplusForceApp" && parameterTypes[0] == ComponentName::class.java
        }.hookReturnConstant(false)
    }
}