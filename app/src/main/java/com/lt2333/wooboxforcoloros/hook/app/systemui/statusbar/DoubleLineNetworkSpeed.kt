package com.lt2333.wooboxforcoloros.hook.app.systemui.statusbar

import android.content.Context
import android.net.TrafficStats
import android.util.TypedValue
import android.widget.FrameLayout
import android.widget.TextView
import cn.fkj233.ui.activity.dp2px
import com.lt2333.wooboxforcoloros.util.*
import com.lt2333.wooboxforcoloros.util.xposed.base.HookRegister
import de.robv.android.xposed.XposedHelpers
import java.text.DecimalFormat

object DoubleLineNetworkSpeed : HookRegister() {

    private var mLastTotalUp: Long = 0
    private var mLastTotalDown: Long = 0

    private var lastTimeStampTotalUp: Long = 0
    private var lastTimeStampTotalDown: Long = 0

    private val getDualSize = XSPUtils.getInt("status_bar_network_speed_dual_row_size", 6)
    private val getDualWidth = XSPUtils.getInt("status_bar_network_speed_dual_row_width", 35)

    override fun init() {
        hasEnable("status_bar_dual_row_network_speed") {
            "com.oplusos.systemui.statusbar.widget.NetworkSpeedView".hookAfterMethod(
                getDefaultClassLoader(), "onFinishInflate"
            ) {
                val mSpeedNumber =
                    XposedHelpers.getObjectField(it.thisObject, "mSpeedNumber") as TextView
                val mSpeedUnit =
                    XposedHelpers.getObjectField(it.thisObject, "mSpeedUnit") as TextView
                mSpeedNumber.setTextSize(TypedValue.COMPLEX_UNIT_DIP, getDualSize.toFloat())
                mSpeedUnit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, getDualSize.toFloat())
            }
            "com.oplusos.systemui.statusbar.widget.NetworkSpeedView".hookBeforeMethod(
                getDefaultClassLoader(),
                "updateNetworkSpeed",
                String::class.java,
                Array<String>::class.java
            ) {
                val mView = it.thisObject as FrameLayout
                val context = mView.context
                val mSpeedNumber =
                    XposedHelpers.getObjectField(it.thisObject, "mSpeedNumber") as TextView
                val mSpeedUnit =
                    XposedHelpers.getObjectField(it.thisObject, "mSpeedUnit") as TextView
                if (it.args[1] != null && (it.args[1] as Array<String>).size >= 2) {
                    mSpeedNumber.text = getTotalUpSpeed(context)
                    mSpeedUnit.text = getTotalDownloadSpeed(context)
                }
                val layoutParams = mView.layoutParams
                layoutParams.isNonNull {
                    layoutParams.width = dp2px(context, getDualWidth.toFloat())
                }
                it.result = null
            }
        }
    }

    //获取总的上行速度
    private fun getTotalUpSpeed(context: Context): String {
        var totalUpSpeed = 0F

        val currentTotalTxBytes = TrafficStats.getTotalTxBytes()
        val nowTimeStampTotalUp = System.currentTimeMillis()

        //计算上传速度
        val bytes =
            (currentTotalTxBytes - mLastTotalUp) * 1000 / (nowTimeStampTotalUp - lastTimeStampTotalUp).toFloat()
        var unit = ""

        if (bytes >= 1048576) {
            totalUpSpeed =
                DecimalFormat("0.0").format(bytes / 1048576).toFloat()
            unit = "MB/s"
        } else {
            totalUpSpeed =
                DecimalFormat("0.0").format(bytes / 1024).toFloat()
            unit = "KB/s"
        }

        //保存当前的流量总和和上次的时间戳
        mLastTotalUp = currentTotalTxBytes
        lastTimeStampTotalUp = nowTimeStampTotalUp

        return if (totalUpSpeed >= 100) {
            "" + totalUpSpeed.toInt() + unit
        } else {
            "" + totalUpSpeed + unit
        }
    }

    //获取总的下行速度
    private fun getTotalDownloadSpeed(context: Context): String {
        var totalDownSpeed = 0F
        val currentTotalRxBytes = TrafficStats.getTotalRxBytes()
        val nowTimeStampTotalDown = System.currentTimeMillis()

        //计算下行速度
        val bytes =
            (currentTotalRxBytes - mLastTotalDown) * 1000 / (nowTimeStampTotalDown - lastTimeStampTotalDown).toFloat()

        var unit = ""

        if (bytes >= 1048576) {
            totalDownSpeed =
                DecimalFormat("0.0").format(bytes / 1048576).toFloat()
            unit = "MB/s"
        } else {
            totalDownSpeed =
                DecimalFormat("0.0").format(bytes / 1024).toFloat()
            unit = "KB/s"
        }
        //保存当前的流量总和和上次的时间戳
        mLastTotalDown = currentTotalRxBytes
        lastTimeStampTotalDown = nowTimeStampTotalDown

        return if (totalDownSpeed >= 100) {
            "" + totalDownSpeed.toInt() + unit
        } else {
            "" + totalDownSpeed + unit
        }

    }

}