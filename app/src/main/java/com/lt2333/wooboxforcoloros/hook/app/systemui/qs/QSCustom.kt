package com.lt2333.wooboxforcoloros.hook.app.systemui.qs

import android.content.res.Configuration
import android.view.ViewGroup
import com.lt2333.wooboxforcoloros.util.XSPUtils
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.hookAfterMethod
import com.lt2333.wooboxforcoloros.util.hookBeforeMethod
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers

object QSCustom : HookRegister() {

    override fun init() {
        hasEnable("qs_custom_switch") {
            val mRows = XSPUtils.getInt("qs_custom_rows", 4)
            val mRowsHorizontal = XSPUtils.getInt("qs_custom_rows_horizontal", 2)
            val mColumns = XSPUtils.getInt("qs_custom_columns", 4)
            val mColumnsUnexpanded = XSPUtils.getInt("qs_custom_columns_unexpanded", 6)

            "com.android.systemui.qs.QuickQSPanel".hookBeforeMethod(
                getDefaultClassLoader(),
                "getNumQuickTiles",
            ) {
                //未展开时的列数
                it.result = mColumnsUnexpanded
            }

            "com.android.systemui.qs.TileLayout".hookAfterMethod(
                getDefaultClassLoader(),
                "updateColumns"
            ) {
                //展开时的列数
                XposedHelpers.setObjectField(it.thisObject, "mColumns", mColumns)
            }
            "com.android.systemui.qs.TileLayout".hookAfterMethod(
                getDefaultClassLoader(),
                "updateResources"
            ) {
                //展开时的行数
                val viewGroup = it.thisObject as ViewGroup
                val mConfiguration: Configuration = viewGroup.context.resources.configuration
                if (mConfiguration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    XposedHelpers.setObjectField(viewGroup, "mMaxAllowedRows", mRows)
                } else {
                    XposedHelpers.setObjectField(viewGroup, "mMaxAllowedRows", mRowsHorizontal)
                }
                viewGroup.requestLayout()
            }
        }
    }
}