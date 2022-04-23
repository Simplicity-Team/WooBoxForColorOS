package com.lt2333.wooboxforcoloros.hook.app.systemui.statusbar

import android.widget.TextView
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers

object HideBatteryPercentageIcon : HookRegister() {
    override fun init() = hasEnable("hide_battery_percentage_icon") {
        findMethod("com.oplusos.systemui.statusbar.widget.StatBatteryMeterView") {
            name == "updatePercentText"
        }.hookAfter {
            val textView =
                XposedHelpers.getObjectField(it.thisObject, "batteryPercentText") as TextView
            textView.text = (textView.text as String).replace("%", "")
        }
    }
}