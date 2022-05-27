package com.lt2333.wooboxforcoloros.hook.app.systemui.statusbar

import com.github.kyuubiran.ezxhelper.utils.*
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers

object StatusBarClockRedOne : HookRegister() {
    override fun init() = hasEnable("remove_dropdown_status_bar_clock_redone"){
        findMethod("com.oplusos.systemui.ext.BaseClockExt"){
            name == "setTextWithRedOneStyle" && paramCount == 2
        }.hookBefore {
            XposedHelpers.setBooleanField(it.thisObject,"mIsDateTimePanel",false)
        }
    }
}