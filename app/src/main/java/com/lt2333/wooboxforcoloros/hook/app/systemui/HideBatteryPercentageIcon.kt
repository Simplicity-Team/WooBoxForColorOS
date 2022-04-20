package com.lt2333.wooboxforcoloros.hook.app.systemui

import android.widget.TextView
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.hookAfterMethod
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers

object HideBatteryPercentageIcon : HookRegister() {

    override fun init() {
        hasEnable("hide_battery_percentage_icon") {
            "com.oplusos.systemui.statusbar.widget.StatBatteryMeterView".hookAfterMethod(
                getDefaultClassLoader(),
                "updatePercentText"
            ) {
                val textView =
                    XposedHelpers.getObjectField(it.thisObject, "batteryPercentText") as TextView
                textView.text = (textView.text as String).replace("%", "")
            }
        }
    }
}