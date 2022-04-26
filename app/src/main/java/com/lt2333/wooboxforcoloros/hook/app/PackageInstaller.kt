package com.lt2333.wooboxforcoloros.hook.app

import com.lt2333.wooboxforcoloros.hook.app.packageinstaller.AllowReplaceInstall
import com.lt2333.wooboxforcoloros.hook.app.packageinstaller.SkipApkScan
import com.lt2333.wooboxforcoloros.hook.app.packageinstaller.UseAOSPInstaller
import com.lt2333.wooboxforcoloros.util.xposed.base.AppRegister
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage

object PackageInstaller : AppRegister() {
    override val packageName: List<String> = listOf("com.android.packageinstaller")
    override val processName: List<String> = emptyList()
    override val logTag: String = "WooBox"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        XposedBridge.log("WooBox: 成功 Hook " + javaClass.simpleName)
        autoInitHooks(
            lpparam,

            SkipApkScan,//跳过Apk扫描
            AllowReplaceInstall,//允许替换安装(降级)
            UseAOSPInstaller,//使用原生安装器
        )
    }
}