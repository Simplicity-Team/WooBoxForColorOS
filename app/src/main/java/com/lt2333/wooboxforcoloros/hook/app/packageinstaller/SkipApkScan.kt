package com.lt2333.wooboxforcoloros.hook.app.packageinstaller

import com.github.kyuubiran.ezxhelper.utils.*
import com.lt2333.wooboxforcoloros.util.XSPUtils
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object SkipApkScan : HookRegister() {
    override fun init() = hasEnable("skip_apk_scan") {
        val list: Array<String> = when(XSPUtils.getString("PackageInstallCommit","null")){
            "7bc7db7","e1a2c58" -> { arrayOf("L","C","K") }
            "75fe984","532ffef" -> { arrayOf("L","D","i") }
            "38477f0" -> { arrayOf("M","D","k") }
            "a222497" -> { arrayOf("M","E","j") }
            else -> { arrayOf("isStartAppDetail","checkToScanRisk","initiateInstall") }
        }
        //skip appdetail,search isStartAppDetail
        //search -> count_canceled_by_app_detail -4 -> MethodName
        findMethod("com.android.packageinstaller.oplus.OPlusPackageInstallerActivity") {
            name == list[0].toString()
        }.hookAfter {
            it.result = false
        }

        //skip app scan, search method checkToScanRisk
        //search -> "button_type", "install_old_version_button" -5 -> MethodName
        //replace to initiateInstall
        //search -> "button_type", "install_old_version_button" -11 -> MethodName
        findMethod("com.android.packageinstaller.oplus.OPlusPackageInstallerActivity") {
            name == list[1].toString()
        }.hookReplace {
            it.thisObject.invokeMethod(list[2].toString())
        }
    }
}