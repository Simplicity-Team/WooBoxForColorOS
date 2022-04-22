package com.lt2333.wooboxforcoloros.hook.app

import com.lt2333.wooboxforcoloros.hook.app.packageinstaller.*
import com.lt2333.wooboxforcoloros.util.xposed.base.AppRegister
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage
import com.lt2333.wooboxforcoloros.hook.app.packageinstaller.ReplaceInstaller as ReplaceInstaller

object PackageInstaller : AppRegister() {
    override val packageName: List<String> = listOf("com.android.packageinstaller")
    override val processName: List<String> = emptyList()
    override val logTag: String = "WooBox"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        XposedBridge.log("WooBox: 成功 Hook " + javaClass.simpleName)
        autoInitHooks(
            lpparam,

            ReplaceInstaller,//替换原生安装器
        )
    }
}