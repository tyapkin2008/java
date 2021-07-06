public class Loader {

    private static Thread[] threads = new Thread[10];
    private static NumberWriter[] numberWriters = new NumberWriter[10];
    private static String filePath = "D:\\javacourse\\skillbox\\16\\java_basics\\16_Performance\\CarNumberGenerator\\res";

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        for (int i = 0; i < 10 ; i++ ) {
            numberWriters[i] = new NumberWriter(i, filePath, letters, start);
            threads[i] = new Thread(numberWriters[i], "Thread " + i);
            threads[i].start();
        }
        System.out.println("Thread Main time: " + (System.currentTimeMillis() - start) + " ms");
    }
}