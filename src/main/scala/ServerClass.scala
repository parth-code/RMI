import java.rmi.registry.{LocateRegistry, Registry}
import java.rmi.{RemoteException, server}

import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.LoggerFactory

object ServerClass extends Compute{
    val config: Config = ConfigFactory.load("application.conf")
    config.checkValid(ConfigFactory.defaultReference(), "RMI")
    val logger = LoggerFactory.getLogger("RMI")
    @throws(classOf[RemoteException])
    def main(args: Array[String]): Unit = {
      try {
        val port = config.getInt("host")
        val name:String = config.getString("taskname")
        val compute = ServerClass

        // exports the supplied remote object so that it can receive invocations of its remote methods from remote clients
        // 0 specifies anonymous port

        val stub = server.UnicastRemoteObject.exportObject(compute: Compute, port)
        logger.debug(config.getString("debug.success.one"))
        //Since this is on a local machine and default port 1099 is used, getRegistry has no parameters passed to it.

        val registry: Registry = LocateRegistry.createRegistry(port)

        //binding to registry

        registry.bind(name, stub)
        logger.debug(config.getString("debug.success.two"))
      }
      catch {
        case e: RemoteException => {
          logger.error(config.getString("error.two"))
          logger.error(e.getMessage)
          e.printStackTrace()
        }
      }
    }

  override def checkObjects(obj1: Object, obj2:Object): (String, String, String)={//i:Int, j:Int, k:Int): String={
    val success = config.getString("success")
    val failure = config.getString("failure")
    // Two objects that reference the same server object(sender) reference the same object on the recieving side
    if(obj1 == obj2){
      return (obj1.toString, obj2.toString, success)
    }
    else {
      return (obj1.toString, obj2.toString, failure)
    }
  }
}