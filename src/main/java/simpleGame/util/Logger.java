package simpleGame.util;

import org.fusesource.jansi.AnsiConsole;

public class Logger {

    public static void log(String string) {
        AnsiConsole.systemUninstall();
        AnsiConsole.systemInstall();
        System.out.println(string);
        AnsiConsole.systemUninstall();
    }

}
