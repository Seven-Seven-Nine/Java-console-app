package app;

import java.io.FileWriter;

public class File {
    private boolean debug;

    public File(boolean debug) {
        this.debug = debug;
    }

    public void writeFile(String file, String text) {
        try {
            if (this.debug) Log.log("Запись файла", "Создание класса записи.");
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write(text);
            fileWriter.flush();
            if (this.debug) Log.log("Запись файла", "Запись текста в файл " + file);
            fileWriter.close();
            if (this.debug) Log.log("Запись файла", "Закрытие записи файла.");
        } catch (Exception e) {
            System.out.println(ColorTerminal.red() + "Ошибка записи файла!" + ColorTerminal.end());
        }
    }
}
