package fr.tmm.modele;

import java.util.ArrayList;

public class Log {

    private static Log INSTANCE;

    private ArrayList<String> logs = new ArrayList<>();

    public static Log getInstance() {
        if (INSTANCE == null) {
            // TODO -> demander les infos pour creer le maitre du zoo et le nom du zoo
            INSTANCE = new Log();
        }
        return INSTANCE;
    }

    public void addLog(String text) {
        this.logs.add(0, text);
        if (this.logs.size() > 20) {
            this.logs.subList(0,20);
        }
    }

    public ArrayList<String> getLogs() {
        return this.logs;
    }

    public String getLastLog() {
        return this.logs.get(0);
    }
}
