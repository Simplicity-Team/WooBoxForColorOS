package com.lt2333.wooboxforcoloros.hook.app.packageinstaller

import android.os.Bundle
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.github.kyuubiran.ezxhelper.utils.loadClass
import com.github.kyuubiran.ezxhelper.utils.putStaticObject
import com.lt2333.wooboxforcoloros.util.XSPUtils
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object UseAOSPInstaller : HookRegister() {
    override fun init() = hasEnable("use_aosp_installer") {
        val list: Array<String> = when (XSPUtils.getString("PackageInstallCommit","null")) {
            "7bc7db7", "e1a2c58", "75fe984", "532ffef", "38477f0", "a222497" -> {
                arrayOf(
                    "com.android.packageinstaller.DeleteStagedFileOnResult",
                    "com.android.packageinstaller.oplus.common.j",
                    "f"
                )
            }
            else -> {
                arrayOf(
                    "com.android.packageinstaller.DeleteStagedFileOnResult",
                    "com.android.packageinstaller.oplus.common.FeatureOption",
                    "sIsClosedSuperFirewall"
                )
            }
        }
        //use AOSP installer,search -> DeleteStagedFileOnResult
        findMethod(list[0]) {
            name == "onCreate" && parameterTypes[0] == Bundle::class.java
        }.hookBefore {
            loadClass(list[1]).putStaticObject(
                list[2],
                true
            )
        }
    }
}