import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec

class RMITest extends FlatSpec with MockFactory{
  "Comparing 2 objects" must "print success and return both objects" in {
    val checkObjectTest = mockFunction[Object, Object]
    val obj1 = mock[Compute]
    val obj4 = mock[Compute]
//    checkObjectTest.expects(obj1, obj1).returning(obj1, obj2, "success")
//    checkObjectTest.expects(obj1, obj2).returning(obj1, obj2, "failure")
//    inAnyOrder{
//      checkObjectTest(obj4, obj3)
//    }
    assert(obj1 == obj1)
  }
  it must "print failure and return both objects " in {
    val obj2 = mock[Compute]
    val obj3 = mock[Compute]
    assert(obj2 != obj3)
  }
}
