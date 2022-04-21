package com.lt2333.wooboxforcoloros.hook.app.packageinstaller

import android.os.Bundle
import com.lt2333.wooboxforcoloros.util.findClass
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.hookBeforeMethod
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers

object ReplaceInstaller : HookRegister() {
    override fun init() {
        hasEnable("replace_google_installer") {
            //search -> DeleteStagedFileOnResult
            "com.android.packageinstaller.DeleteStagedFileOnResult".hookBeforeMethod(
                getDefaultClassLoader(),
                "onCreate",
                Bundle::class.java
            ){
                XposedHelpers.setStaticBooleanField(
                    "com.android.packageinstaller.oplus.common.FeatureOption".findClass(getDefaultClassLoader()),
                    "sIsClosedSuperFirewall",
                    true
                )
            }
        }
    }
}