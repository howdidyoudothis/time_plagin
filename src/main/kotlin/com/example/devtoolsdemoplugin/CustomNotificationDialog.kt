package com.example.devtoolsdemoplugin

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import java.awt.Dimension
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JRadioButton
import javax.swing.JTextField

class CustomNotificationDialog(private var project: Project?) : DialogWrapper(project) {
    private var contentPanel: JPanel? = null
    private var content: JTextField? = null
    private var informationRadioButton: JRadioButton? = null
    private var warningRadioButton: JRadioButton? = null
    private var errorRadioButton: JRadioButton? = null
    private var IDEUpdateRadioButton: JRadioButton? = null

    init {
        init()
    }

    override fun createCenterPanel(): JComponent? {
        return contentPanel
    }

    override fun doOKAction() {
        val text = content!!.text
        val notification = when {
            informationRadioButton!!.isSelected -> Notification("devtools-demo", text, NotificationType.INFORMATION)
            warningRadioButton!!.isSelected -> Notification("devtools-demo", text, NotificationType.WARNING)
            errorRadioButton!!.isSelected -> Notification("devtools-demo", text, NotificationType.ERROR)
            IDEUpdateRadioButton!!.isSelected -> Notification("devtools-demo", text, NotificationType.IDE_UPDATE)
            else -> Notification("devtools-demo", text, NotificationType.INFORMATION)
        }
        Notifications.Bus.notify(notification)
        super.doOKAction()
    }
}