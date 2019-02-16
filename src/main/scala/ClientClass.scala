import java.rmi.registry.{LocateRegistry, Registry}

import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory

object ClientClass {
  val config = ConfigFactory.load("application.conf")
  config.checkValid(ConfigFactory.defaultReference(), "RMI")
  val logger = LoggerFactory.getLogger("RMI")
  def main(args: Array[String]): Unit = {
    try {
      logger.debug(config.getString("debug.success.three"))
      val port = config.getInt("host")
      val name = config.getString("taskname")

      //Looking up registry at port
      val registry:Registry = LocateRegistry.getRegistry(port)
      //Locating object by reference on registry
      val comp:Compute = registry.lookup(name).asInstanceOf[Compute]
      val comp2:Compute = registry.lookup(name).asInstanceOf[Compute]
      logger.debug(config.getString("debug.success.four"))
      val (object1, object2, result) = comp.checkObjects(comp, comp2)
      println(object1)
      println(object2)
      println(result)
      logger.debug(config.getString("debug.success.five"))
    }
    catch {
      case e:Exception =>
        logger.error(config.getString("error.one"))
        logger.error(e.getMessage)
        e.printStackTrace()
    }
  }
}
