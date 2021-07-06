import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class NumberWriter implements Runnable {

    long start;
    int index;
    String path;
    char[] letters;
    PrintWriter writer;

    public NumberWriter(int index, String path, char[] letters, long start) throws FileNotFoundException {
        this.index = index;
        this.letters = letters;
        this.path = path;
        this.writer = new PrintWriter(path + "/numbers" + index + ".txt");
        this.start = start;
    }

    @Override
    public void run() {
        int localIndex = this.index * 10;
        for (int regionCode = localIndex; regionCode <= localIndex + 9; regionCode++) {
            StringBuilder builder = new StringBuilder();
            for (int number = 1; number < 1000; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            builder.append(firstLetter);
                            builder.append(padNumber(number, 3));
                            builder.append(secondLetter);
                            builder.append(thirdLetter);
                            builder.append(padNumber(regionCode, 2));
                            builder.append('\n');
                            if (builder.length() > 1024) {
                                writer.write(builder.toString());
                                builder = new StringBuilder();
                            }
                        }
                    }
                }
                writer.write(builder.toString());
            }
            writer.flush();
            writer.close();
        }
        System.out.println("Thread " + index + " time: " + (System.currentTimeMillis() - start) + " ms");
    }

    private  String padNumber(int number, int numberLength) {
        StringBuilder numberString = new StringBuilder();
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; i++) {
            numberString.append('0');
        }
        numberString.append(numberStr);
        return numberString.toString();
    }
}


