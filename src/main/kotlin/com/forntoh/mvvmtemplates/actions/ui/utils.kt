package com.forntoh.mvvmtemplates.actions.ui

import java.awt.Dimension
import javax.swing.*

fun frame(title: String, body: (JFrame) -> Unit) = with(JFrame(title)) {

    size = Dimension(512, 512)

    contentPane = JPanel().apply { border = BorderFactory.createEmptyBorder(32, 32, 32, 32) }
    contentPane.layout = BoxLayout(contentPane, BoxLayout.Y_AXIS)

    body(this)

    pack()
    setLocationRelativeTo(null)

    isVisible = true
}

fun JFrame.button(text: String, action: () -> Unit) = JButton(text).apply {
    addActionListener {
        action()
    }
    isEnabled = true
    contentPane.add(this)
}

fun JFrame.textField(title: String, defaultValue: String = "") = JTextField(defaultValue, 54).apply {
    alignmentX = 0f
    val label = JLabel(title)
    label.alignmentX = 0f
    label.labelFor = this
    label.border = BorderFactory.createEmptyBorder(0, 4, 6, 0)
    contentPane.add(label)
    contentPane.add(this)
    contentPane.add(Box.createVerticalStrut(20))
}