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
            // TODO -> demander les infos pour creer le maitre du zoo et le nom du zoo
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
     * @return the lag log
     */
    public String getLastLog() {
        return this.logs.get(0);
    }
}
