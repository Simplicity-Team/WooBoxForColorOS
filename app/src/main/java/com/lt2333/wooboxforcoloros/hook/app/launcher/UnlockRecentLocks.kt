package com.lt2333.wooboxforcoloros.hook.app.launcher

import android.widget.TextView
import com.lt2333.wooboxforcoloros.util.findClass
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.hookBeforeMethod
import com.lt2333.wooboxforcoloros.util.replaceMethod
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister
import java.lang.reflect.Field

object UnlockRecentLocks : HookRegister() {
    override fun init() {
        hasEnable("unlock_recent_task_locks_quantity") {
            "com.android.launcher3.OplusBubbleTextView".findClass(getDefaultClassLoader())
                .hookBeforeMethod(
                    "applyLabel",
                    "com.android.launcher3.model.data.ItemInfoWithIcon",
                    Boolean::class.java,
                    Boolean::class.java
                ) {
                    val field: Field = "com.android.launcher3.model.data.ItemInfo".findClass(getDefaultClassLoader()).getDeclaredField("title")
                    field.isAccessible = true
                    val title = field[it.args.get(0)] as CharSequence
                    (it.thisObject as TextView).text = title
                    it.result = null
                }
        }
    }
}