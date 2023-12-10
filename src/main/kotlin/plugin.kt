import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.SwingUtilities
import kotlin.concurrent.thread
import kotlin.system.exitProcess

class TimerPlugin {
    private lateinit var timerLabel: JLabel
    private lateinit var startButton: JButton
    private lateinit var stopButton: JButton
    private var timerThread: Thread? = null
    private var startTime: Long = 0

    fun startTimer() {
        if (timerThread != null && timerThread!!.isAlive) {
            return
        }

        startTime = System.currentTimeMillis()
        timerThread = thread(start = true) {
            while (true) {
                val elapsedTime = System.currentTimeMillis() - startTime
                val hours = (elapsedTime / (1000 * 60 * 60)).toInt() % 24
                val minutes = (elapsedTime / (1000 * 60) % 60).toInt()
                val seconds = (elapsedTime / 1000 % 60).toInt()

                val timeText = String.format("%02d:%02d:%02d", hours, minutes, seconds)

                SwingUtilities.invokeLater {
                    timerLabel.text = timeText
                }

                Thread.sleep(1000)
            }
        }
    }

    fun stopTimer() {
        timerThread?.interrupt()
        timerThread = null
    }

    fun run() {
        SwingUtilities.invokeLater {
            val frame = JFrame("Timer Plugin")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            frame.layout = FlowLayout()

            timerLabel = JLabel("00:00:00")
            timerLabel.preferredSize = Dimension(200, 50)
            frame.add(timerLabel)

            startButton = JButton("Start")
            startButton.addActionListener {
                startTimer()
            }
            frame.add(startButton)

            stopButton = JButton("Stop")
            stopButton.addActionListener {
                stopTimer()
            }
            frame.add(stopButton)

            frame.pack()
            frame.isVisible = true
        }
    }
}

fun main() {
    val plugin = TimerPlugin()
    plugin.run()
}