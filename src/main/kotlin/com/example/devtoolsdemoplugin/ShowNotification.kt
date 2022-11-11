package com.example.devtoolsdemoplugin

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class ShowNotification : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val notification = Notification("devtools-demo", "Test content", NotificationType.INFORMATION)
        Notifications.Bus.notify(notification)
    }
}