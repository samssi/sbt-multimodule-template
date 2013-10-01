package jetty

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext
import org.apache.log4j.{PatternLayout, ConsoleAppender, Level, Logger}
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext

object Jetty {
  lazy val server = new Server(8080)
  lazy val warLocation = "application/src/main/webapp"
  lazy val descriptor = "application/src/main/webapp/WEB-INF/web.xml"

  def appContext: WebAppContext = {
    val context = new WebAppContext()
    context.setContextPath("/")
    context.setWar(warLocation)
    context.setDescriptor(descriptor)
    context
  }

  def startLogging() {
    val rootLogger = Logger.getRootLogger
    rootLogger.setLevel(Level.INFO)
    rootLogger.addAppender(new ConsoleAppender(new PatternLayout("%d %p [%c] - %m%n")))
  }

  def start() {
    startLogging()
    server.setHandler(appContext)
    try {
      server.start()
      server.join()
    } catch {
      case e: Exception => {
        e.printStackTrace()
        System.exit(1)
      }
    }
  }
  def stop() {
    server.stop();
  }
}

object JettyStarter {
  def main(args: Array[String]) {
    Jetty.start()
  }
}