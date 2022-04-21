package com.lt2333.wooboxforcoloros.hook.app

import com.lt2333.wooboxforcoloros.hook.app.systemui.notification.RemoveDevModeIsOn
import com.lt2333.wooboxforcoloros.hook.app.systemui.notification.RemoveFinishedCharging
import com.lt2333.wooboxforcoloros.hook.app.systemui.notification.RemoveUSBDebugging
import com.lt2333.wooboxforcoloros.hook.app.systemui.qs.QSCustom
import com.lt2333.wooboxforcoloros.hook.app.systemui.statusbar.*
import com.lt2333.wooboxforcoloros.util.xposed.base.AppRegister
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage

object SystemUI : AppRegister() {
    override val packageName: List<String> = listOf("com.android.systemui")
    override val processName: List<String> = emptyList()
    override val logTag: String = "WooBox"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        XposedBridge.log("WooBox: 成功 Hook " + javaClass.simpleName)
        autoInitHooks(
            lpparam,

            //快速设置
            QSCustom, //快速设置自定义

            //状态栏
            StatusBarDoubleTapToSleep, //双击状态栏锁屏
            HidePromptView, //隐藏胶囊提示
            HideBatteryPercentageIcon, //隐藏电量百分比号
            DoubleLineNetworkSpeed, //双排网速
            HideWifiActivityIcon, //隐藏WIFI箭头
            HideMobileActivityIcon, //隐藏移动箭头
            StatusBarNetworkSpeedRefreshSpeed, //网速秒刷

            //通知类
            RemoveUSBDebugging, //移除USB调试已开启通知
            RemoveFinishedCharging, //移除已充满通知
            RemoveDevModeIsOn, //移除开发者模式已开启通知
        )
    }
}