package app;

import java.util.Scanner;

public class App {
    private String nameProgram = "Console app";
    private String versionProgram = "0.0.2";
    private Scanner scanner;
    private boolean debug = false;

    private String scanner(String colorInput) {
        this.scanner = new Scanner(System.in, "utf-8");
        switch (colorInput) {
            case "yellow":
                System.out.print(ColorTerminal.bold() + ColorTerminal.yellow() + ">> " + ColorTerminal.end());
                break;
            case "white":
                System.out.print(ColorTerminal.bold() + ColorTerminal.white() + ">> " + ColorTerminal.end());
                break;
            case "blue":
                System.out.print(ColorTerminal.bold() + ColorTerminal.blue() + ">> " + ColorTerminal.end());
                break;
            default:
                System.out.print(ColorTerminal.bold() + ColorTerminal.white() + ">> " + ColorTerminal.end());
                break;
        }
        String userInput = scanner.nextLine();
        return userInput;
    }

    public void userInput() {
        System.out.println(this.fullNameProgram());
        
        boolean gettingInput = true;

        while (gettingInput) {
            String userInput = this.scanner("yellow");
            this.choiceUserInTerminal(userInput);   
        }
    }

    private void choiceUserInTerminal(String userInput) {
        switch (userInput) {
            case "exit":
                this.scanner.close();
                System.exit(0);
                break;
            case "clear":
                this.clearTerminal();           
                break;
            case "debug":
                this.changeDebug();
                break;
            case "help":
                this.helpInfo();
                break;
            case "write":
                this.write();
                break;
            default:
                System.out.println(ColorTerminal.red() + "Неопознанный ввод, введите 'exit', чтобы завершить программу." + ColorTerminal.end());
        }

        if (debug) {
            Log.log("Ввод пользователя", userInput);
        }
    }

    private void helpInfo() {
        System.out.println(ColorTerminal.yellow() + "help" + ColorTerminal.end() + " - справочная информация по командам.");
        System.out.println(ColorTerminal.yellow() + "write" + ColorTerminal.end() + " - режим записи.");
        System.out.println(ColorTerminal.yellow() + "clear" + ColorTerminal.end() + " - очистка терминала (поддержка только Windows).");
        System.out.println(ColorTerminal.yellow() + "debug" + ColorTerminal.end() + " - поменять режим отладки.");
        System.out.println(ColorTerminal.yellow() + "exit" + ColorTerminal.end() + " - завершение процесса.");
    }

    private void clearTerminal() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(ColorTerminal.red() + "Ошибка очистки терминала!" + ColorTerminal.end());
            if (debug) {
                System.out.println(ColorTerminal.green() + "Рекомендация: отжаться 10 раз." + ColorTerminal.end());
            }
        }
        System.out.println(this.fullNameProgram());
    }

    private void write() {
        System.out.print(ColorTerminal.blue() + "Имя файла " + ColorTerminal.end());
        String nameFile = this.scanner("blue");
        System.out.print(ColorTerminal.blue() + "Текст " + ColorTerminal.end());
        String text = this.scanner("blue");
        
        File file = new File(this.debug);
        file.writeFile(nameFile, text);
    }

    private String fullNameProgram() {
        return nameProgram + " " + versionProgram;
    }

    public void changeDebug() {
        if (this.debug == true) {
            this.debug = false;
            Log.log("Отладка", "режим отладки выключен!");
        } else {
            this.debug = true;
            Log.log("Отладка", "режим отладки включён!");
        }
    }

    public String getFullNameProgram() {
        return this.fullNameProgram();
    }

    public boolean getDebug() {
        return this.debug;
    }
}
