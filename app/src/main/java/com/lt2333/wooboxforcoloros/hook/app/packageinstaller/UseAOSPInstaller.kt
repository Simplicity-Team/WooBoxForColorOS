package com.lt2333.wooboxforcoloros.hook.app.packageinstaller

import android.os.Bundle
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.github.kyuubiran.ezxhelper.utils.loadClass
import com.github.kyuubiran.ezxhelper.utils.putStaticObject
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object UseAOSPInstaller : HookRegister() {
    override fun init() = hasEnable("use_aosp_installer") {
        //search -> DeleteStagedFileOnResult
        findMethod("com.android.packageinstaller.DeleteStagedFileOnResult") {
            name == "onCreate" && parameterTypes[0] == Bundle::class.java
        }.hookBefore {
            loadClass("com.android.packageinstaller.oplus.common.FeatureOption").putStaticObject(
                "sIsClosedSuperFirewall",
                true
            )
        }
    }
}