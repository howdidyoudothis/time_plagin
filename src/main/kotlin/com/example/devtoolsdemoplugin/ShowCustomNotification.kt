package com.example.devtoolsdemoplugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.example.devtoolsdemoplugin.CustomNotificationDialog as Dialog

class ShowCustomNotification : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val dialog = Dialog(e.project)
        dialog.title = "Show Custom Notification"
        dialog.show()
    }
}