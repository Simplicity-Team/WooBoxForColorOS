package com.lt2333.wooboxforcoloros.hook.app.launcher

import android.content.Context
import com.lt2333.wooboxforcoloros.util.findClass
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.hookAfterConstructor
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers

object RemoveUpdateDot : HookRegister() {
    override fun init() {
        hasEnable("launcher_remove_update_dot") {
            "com.oplus.quickstep.applock.OplusLockManager".findClass(getDefaultClassLoader())
                .hookAfterConstructor(Context::class.java) {
                    XposedHelpers.setIntField(it.thisObject, "mLockAppLimit", 999)
                }
        }
    }
}