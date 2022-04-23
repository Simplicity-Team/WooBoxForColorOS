package com.lt2333.wooboxforcoloros.hook.app.android

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object RemoveVPNActivatedNotification : HookRegister() {
    override fun init() {
        findMethod("com.android.server.connectivity.OplusVpnHelper") {
            name == "showNotification" && parameterCount == 6
        }.hookBefore {
            hasEnable("remove_vpn_activated") {
                it.result = null
            }
        }
    }
}