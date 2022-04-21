package com.lt2333.wooboxforcoloros.hook.app.android

import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.hookBeforeMethod
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object RemoveVPNActivatedNotification : HookRegister() {

    override fun init() {
        hasEnable("remove_vpn_activated") {
            "com.android.server.connectivity.OplusVpnHelper".hookBeforeMethod(
                getDefaultClassLoader(),
                "showNotification",
                String::class.java,
                Int::class.java,
                Int::class.java,
                String::class.java,
                "android.app.PendingIntent",
                "com.android.internal.net.VpnConfig"
            ) {
                it.result = null
            }
        }
    }

}