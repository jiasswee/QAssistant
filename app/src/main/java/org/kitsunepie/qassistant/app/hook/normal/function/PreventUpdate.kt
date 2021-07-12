package org.kitsunepie.qassistant.app.hook.normal.function

import com.github.kyuubiran.ezxhelper.utils.findMethodByCondition
import com.github.kyuubiran.ezxhelper.utils.isStatic
import org.kitsunepie.qassistant.annotations.NormalHookEntry
import org.kitsunepie.qassistant.app.hook.base.BaseSwitchHook
import org.kitsunepie.qassistant.app.util.ClassPointer
import org.kitsunepie.qassistant.app.util.clazz
import org.kitsunepie.qassistant.app.util.hookBefore

@NormalHookEntry
object PreventUpdate : BaseSwitchHook() {
    override val needReboot: Boolean = true

    override fun init() {
        findMethodByCondition(ClassPointer.UpgradeController.clazz!!) {
            it.name == "a" && !it.isStatic && it.parameterTypes.isEmpty()
        }.hookBefore(this) {
            it.result = null
        }
    }

    override val title: String = "屏蔽更新提示"
}