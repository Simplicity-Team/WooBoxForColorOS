package com.lt2333.wooboxforcoloros.hook.app.launcher

import android.content.Context
import com.lt2333.wooboxforcoloros.util.*
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers

object UnlockRecentLocks : HookRegister() {
    override fun init() {
        hasEnable("unlock_recent_task_locks_quantity") {
            "com.oplus.quickstep.applock.OplusLockManager".findClass(getDefaultClassLoader())
                .hookAfterConstructor(Context::class.java) {
                    XposedHelpers.setIntField(it.thisObject, "mLockAppLimit", 999)
                }
        }
    }
}