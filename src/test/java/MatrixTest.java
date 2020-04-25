
public class MatrixTest {


    public void doIt(A a){
        a.printIt();
    }
    public void doIt(B b){
        b.printThem();
    }

    public static void main(String[] args) {

        MatrixTest matrixTest = new MatrixTest();

        matrixTest.doIt(new C());
        matrixTest.doIt(new B());

    }
}


interface A{
    void printIt();
}


class B implements A{

    @Override
    public void printIt() {
        System.out.println("B::printIt - BBBBB");
    }

    public void printThem() {
        System.out.println("B::printThem");
    }
}

class C implements A{

    @Override
    public void printIt() {
        System.out.println("C::printIt - CCCCC");
    }
}


