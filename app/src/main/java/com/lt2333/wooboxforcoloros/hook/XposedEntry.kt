package com.lt2333.wooboxforcoloros.hook

import com.lt2333.wooboxforcoloros.BuildConfig
import com.lt2333.wooboxforcoloros.hook.app.Android
import com.lt2333.wooboxforcoloros.hook.app.SystemUI
import com.lt2333.wooboxforcoloros.hook.app.android.corepatch.CorePatch
import com.lt2333.wooboxforcoloros.util.xposed.EasyXposedInit
import com.lt2333.wooboxforcoloros.util.xposed.base.AppRegister
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.XSharedPreferences
import de.robv.android.xposed.callbacks.XC_LoadPackage

class XposedEntry : EasyXposedInit() {
    private var prefs = XSharedPreferences(BuildConfig.APPLICATION_ID, "config")

    override val registeredApp: List<AppRegister> = listOf(
        Android, //Android
        SystemUI, //系统界面
    )

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        if (prefs.getBoolean("main_switch", true)) {
            super.handleLoadPackage(lpparam)
        }
    }

    override fun initZygote(startupParam: IXposedHookZygoteInit.StartupParam?) {
        super.initZygote(startupParam)
        CorePatch().initZygote(startupParam)
    }

}