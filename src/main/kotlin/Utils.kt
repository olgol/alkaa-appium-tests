import io.appium.java_client.AppiumDriver
import org.openqa.selenium.OutputType
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO
import kotlin.random.Random

class Utils {
    companion object {
        fun generateRandomYear(): Int {
            val min = 1000
            val max = 2999
            return (min..max).random()
        }

        fun getRandomInt(): Int {
            return (0..9999).random()
        }

        fun getRandomStr(): String {
                val maxLength: Int = 5
                val length = Random.nextInt(1, maxLength + 1)
                val chars = ('a'..'z') + ('A'..'Z')
                return (1..length)
                    .map { chars.random() }
                    .joinToString("")
        }

        fun getPixelColor(driver: AppiumDriver, x: Int, y: Int): java.awt.Color? {
            // Сделать скриншот
            val screenshotBytes = driver.getScreenshotAs(OutputType.BYTES)
            val image: BufferedImage = ImageIO.read(ByteArrayInputStream(screenshotBytes))
            // Получить цвет пикселя
            return Color(image.getRGB(x, y))
        }
    }
}