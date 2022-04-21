package com.lt2333.wooboxforcoloros.hook.app.systemui

import android.view.View
import android.widget.ImageView
import com.lt2333.wooboxforcoloros.util.getObjectField
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.hookAfterMethod
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object HideMobileActivityIcon : HookRegister() {

    override fun init() {
        //隐藏移动箭头
        hasEnable("hide_mobile_activity_icon") {
            "com.oplusos.systemui.statusbar.OplusStatusBarMobileView".hookAfterMethod(
                getDefaultClassLoader(),
                "initViewState"
            ) {
                (it.thisObject.getObjectField("mDataActivity") as ImageView).visibility = View.GONE }
        }
        //隐藏移动箭头
        hasEnable("hide_mobile_activity_icon") {
            "com.oplusos.systemui.statusbar.OplusStatusBarMobileView".hookAfterMethod(
                getDefaultClassLoader(),
                "updateMobileViewState",
                "com.android.systemui.statusbar.phone.StatusBarSignalPolicy.MobileIconState"
            ) {
                (it.thisObject.getObjectField("mDataActivity") as ImageView).visibility = View.GONE }
        }
    }

}