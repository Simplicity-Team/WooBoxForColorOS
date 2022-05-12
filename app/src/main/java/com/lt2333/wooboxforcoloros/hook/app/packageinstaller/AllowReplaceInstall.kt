package com.lt2333.wooboxforcoloros.hook.app.packageinstaller

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookReplace
import com.lt2333.wooboxforcoloros.util.XSPUtils
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object AllowReplaceInstall : HookRegister() {
    override fun init() = hasEnable("allow_replace_install") {
        val list: String = when(XSPUtils.getString("PackageInstallCommit","null")){
            "7bc7db7","e1a2c58","a222497" -> "Q"
            "75fe984","532ffef" -> "P"
            "38477f0" -> "R"
            else -> "isReplaceInstall"
        }
        //Allow replace install,Low/same version warning
        //search ->  ? 1 : 0; -> this Method
        findMethod("com.android.packageinstaller.oplus.OPlusPackageInstallerActivity") {
            name == list && returnType == Boolean::class.java
        }.hookReplace { false }
    }
}