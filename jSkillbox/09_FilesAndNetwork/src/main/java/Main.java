import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        // Пути к начальной и целевой папке
        String sourceFolderPath = null;
        String targetFolderPath = null;
        // Бесконечный цикл ввода и копирования
        for(;;){
            // Если исходная папка не задана
            if(sourceFolderPath == null){
                System.out.println("Введите исходную папку");
            }
            // если конечная папка задана
            else if(targetFolderPath == null){
                System.out.println("Введите конечную папку");
            }

            String path = scanner.nextLine().trim();
            if(path.length() == 0){
                System.out.println("Введена пустая строка");
                continue;
            }
            // Если исходная папка не задана
            if(sourceFolderPath == null){
                sourceFolderPath = path;
            }
            // Если целевая папка не задана
            else if(targetFolderPath == null){
                targetFolderPath = path;
            }
            // если все пути заданы
            if(sourceFolderPath != null && targetFolderPath != null){
                // Получаем пути
                Path sourceDirectory = Paths.get(sourceFolderPath);
                Path targetDirectory = Paths.get(targetFolderPath);
                // цикл обхода дерева
                try {
                    Files.walk(sourceDirectory).forEach(sourcePath -> {
                        Path targetPath = targetDirectory.resolve(sourceDirectory.relativize(sourcePath));
                        System.out.printf("Copying %s to %s%n", sourcePath, targetPath);
                        try {
                            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // обнуляем значения путей для дальнейшего копирования новых папок или ввода заново в случае ошибок
                sourceFolderPath = null;
                targetFolderPath = null;
            }
        }
    }
}
