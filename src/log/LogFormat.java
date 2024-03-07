package log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Format du log
 */
public class LogFormat extends Formatter {
    /**
     *
     * @param record the log record to be formatted.
     * @return
     */
    public String format(LogRecord record){
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder resultat = new StringBuilder();

        resultat.append(format.format(new Date()));
        resultat.append(" Niveau : ");
        resultat.append(record.getLevel());

        resultat.append(" / Classe : ");
        resultat.append(record.getSourceClassName());

        resultat.append(" / Methode : ");
        resultat.append(record.getSourceMethodName());

        resultat.append(" / Message : ");
        resultat.append(record.getMessage());

        resultat.append("\n");
        return resultat.toString();
    }
}
