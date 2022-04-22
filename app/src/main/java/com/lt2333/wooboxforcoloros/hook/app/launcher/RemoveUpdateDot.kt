package com.lt2333.wooboxforcoloros.hook.app.launcher

import android.widget.TextView
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.github.kyuubiran.ezxhelper.utils.loadClass
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister
import java.lang.reflect.Field

object RemoveUpdateDot : HookRegister() {
    override fun init() = hasEnable("launcher_remove_update_dot") {
        findMethod("com.android.launcher3.OplusBubbleTextView") {
            name == "applyLabel" && parameterCount == 3
        }.hookBefore {
            val field: Field =
                loadClass("com.android.launcher3.model.data.ItemInfo").getDeclaredField("title")
            field.isAccessible = true
            val title = field[it.args.get(0)] as CharSequence
            (it.thisObject as TextView).text = title
            it.result = null
        }
    }
}