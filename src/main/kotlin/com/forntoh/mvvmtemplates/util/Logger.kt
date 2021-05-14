package com.forntoh.mvvmtemplates.util

import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JTextArea

object Logger {
    private val textArea = JTextArea().apply {
        with(JFrame("Debug")) {
            contentPane.add(this@apply, BorderLayout.CENTER)
            size = Dimension(720, 512)
            setLocationRelativeTo(null)
            isVisible = true
        }
    }

    fun log(message: String) {
        textArea.text = "${textArea.text}\n$message"
    }
}