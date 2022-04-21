package com.lt2333.wooboxforcoloros.hook.app.launcher

import android.content.Context
import android.widget.TextView
import com.lt2333.wooboxforcoloros.util.findClass
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.hookAfterConstructor
import com.lt2333.wooboxforcoloros.util.hookBeforeMethod
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers
import java.lang.reflect.Field

object RemoveUpdateDot : HookRegister() {
    override fun init() {
        hasEnable("launcher_remove_update_dot") {
            "com.android.launcher3.OplusBubbleTextView".findClass(getDefaultClassLoader())
                .hookBeforeMethod(
                    "applyLabel",
                    "com.android.launcher3.model.data.ItemInfoWithIcon",
                    Boolean::class.java,
                    Boolean::class.java
                ) {
                    val field: Field = "com.android.launcher3.model.data.ItemInfo".findClass(
                        getDefaultClassLoader()
                    ).getDeclaredField("title")
                    field.isAccessible = true
                    val title = field[it.args.get(0)] as CharSequence
                    (it.thisObject as TextView).text = title
                    it.result = null
                }
        }
    }
}