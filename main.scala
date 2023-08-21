import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object Main extends App {
  def getStrAscii(intent: Int): Char = {
    val index = intent / 32
    val ascii = Array(' ', '.', '+', '=', '@', '$', '#', '%')
    ascii(index)
  }

  def getImage(dir: String, scale: Int): Unit = {
    val img = ImageIO.read(new File(dir))
    val width = img.getWidth
    val height = img.getHeight

    for (y <- 0 until height by (scale * 2)) {
      for (x <- 0 until width by scale) {
        if (y % (scale * 2) == 0 && x % scale == 0) {
          val plx = new java.awt.Color(img.getRGB(x, y))
          var intent = (plx.getRed + plx.getGreen + plx.getBlue) / 3
          if (plx.getAlpha == 0) {
            intent = 0
          }
          print(getStrAscii(intent))
        }
      }
      if (y % (scale * 2) == 0) {
        println()
      }
    }
  }

  getImage("418c74d3758a045bd29a3da57893ab6c.jpg", 10)
}
