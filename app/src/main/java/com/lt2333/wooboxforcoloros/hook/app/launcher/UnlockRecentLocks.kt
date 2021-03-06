package com.lt2333.wooboxforcoloros.hook.app.launcher

import com.github.kyuubiran.ezxhelper.utils.findConstructor
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.github.kyuubiran.ezxhelper.utils.putObject
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers

object UnlockRecentLocks : HookRegister() {
    override fun init() = hasEnable("unlock_recent_task_locks_quantity") {
        findConstructor("com.oplus.quickstep.applock.OplusLockManager") {
            parameterCount == 1
        }.hookAfter {
            XposedHelpers.setIntField(it.thisObject, "mLockAppLimit", 999)
            it.thisObject.putObject("mLockAppLimit", 999)
        }
    }
}