import java.rmi.{Remote, RemoteException}

trait Compute extends Remote {
  @throws(classOf[RemoteException])
  def checkObjects(obj1:Object, obj2:Object):(String, String, String)//i: Int, j: Int, k:Int): String
}
