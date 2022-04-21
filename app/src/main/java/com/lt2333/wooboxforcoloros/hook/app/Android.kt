package com.lt2333.wooboxforcoloros.hook.app

import com.lt2333.wooboxforcoloros.hook.app.android.*
import com.lt2333.wooboxforcoloros.hook.app.android.corepatch.CorePatch
import com.lt2333.wooboxforcoloros.util.xposed.base.AppRegister
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage

object Android : AppRegister() {
    override val packageName: List<String> = listOf("android")
    override val processName: List<String> = emptyList()
    override val logTag: String = "WooBox"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        XposedBridge.log("WooBox: 成功 Hook " + javaClass.simpleName)
        //核心破解
        CorePatch().handleLoadPackage(lpparam)

        autoInitHooks(
            lpparam,
            DisableFlagSecure, //允许截图
            DeleteOnPostNotification, //上层显示
            SystemPropertiesHook, //SystemPropertiesHook
            AllowUntrustedTouches, //允许不受信任的触摸
            RemoveVPNActivatedNotification, //移除VPN已激活通知
        )
    }

}