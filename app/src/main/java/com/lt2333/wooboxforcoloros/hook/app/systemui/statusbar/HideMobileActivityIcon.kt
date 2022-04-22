package com.lt2333.wooboxforcoloros.hook.app.systemui.statusbar

import android.view.View
import android.widget.ImageView
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.getObject
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.lt2333.wooboxforcoloros.util.hasEnable
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister

object HideMobileActivityIcon : HookRegister() {
    override fun init() = hasEnable("hide_mobile_activity_icon") {
        //隐藏移动箭头
        findMethod("com.oplusos.systemui.statusbar.OplusStatusBarMobileView") {
            name == "initViewState"
        }.hookAfter {
            (it.thisObject.getObject("mDataActivity") as ImageView).visibility = View.GONE
        }
        findMethod("com.oplusos.systemui.statusbar.OplusStatusBarMobileView") {
            name == "updateMobileViewState"
        }.hookAfter {
            (it.thisObject.getObject("mDataActivity") as ImageView).visibility = View.GONE
        }
    }
}