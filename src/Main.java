import controleur.ControleurAcceuil;
import log.LogFormat;
import log.LoggerPoo;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        try {
            FileHandler fh = new FileHandler("LogPoo.log",true);
            LoggerPoo.LOGGER.setUseParentHandlers(false);
            LoggerPoo.LOGGER.addHandler(fh);
            fh.setFormatter(new LogFormat());

            ControleurAcceuil.init();

        } catch (IOException e) {
            LoggerPoo.LOGGER.log(Level.SEVERE,"Erreur : "+e.getMessage()+" "+e);
        }

    }
}