public class SimpleTest {


    public static void main(String[] args) {
        random();


    }

    private static void random(){
        int height = 10 - 1;
        int length = 10 - 1;
        for (int i = 0; i < 5000; i++) {
            int n = (int)((Math.random() * (Math.min(height, length))));
            if (n >= height){
                System.out.println("hara a");
            }
            else if (n < 0){
                System.out.println("hara b");
            }
            else {
                System.out.println(n);
            }
        }
    }
}
