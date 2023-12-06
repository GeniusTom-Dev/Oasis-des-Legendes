package fr.tmm.modele;

import java.util.ArrayList;

public class Log {

    private static Log INSTANCE;

    private ArrayList<String> logs = new ArrayList<>();

    /**
     * Get the unique instance of the class Log
     * @return the instance
     */
    public static Log getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Log();
        }
        return INSTANCE;
    }

    /**
     * Add a log to the log list
     * @param text : the new log
     */
    public void addLog(String text) {
        this.logs.add(0, text);
        if (this.logs.size() > 20) {
            this.logs.subList(0,20);
        }
    }

    public ArrayList<String> getLogs() {
        return this.logs;
    }

    /**
     * Get the last log
     * @return the last log
     */
    public String getLastLog() {
        if (this.logs.isEmpty()) {
            return null;
        }
        return this.logs.get(0);
    }

    /**
     * Print all the logs in the console
     */
    public void showLogs() {
        System.out.println("LOGS : ");
        for (String log : logs) {
            System.out.println(log);
        }
    }
}
