package gr.hua.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SimpleLoggable implements Loggable, Cloneable {
    
    private String sMessage;
    private String sSender;
    private String lTimeStamp;

    public SimpleLoggable(final String sMessageToLog, final Object sLogSender) {
        sMessage = sMessageToLog;
        sSender = sLogSender.getClass().getName();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        lTimeStamp = dateFormat.format(Calendar.getInstance().getTime());
    }
    
    private SimpleLoggable(SimpleLoggable s) {
        sMessage = s.sMessage;
        sSender = s.sSender;
        lTimeStamp = s.lTimeStamp;
    }

    @Override
    public String log() {
        StringBuilder log = new StringBuilder();
        log.append(lTimeStamp.toString()).append(PropertiesLoader.NEW_LINE);
        log.append(sSender).append(PropertiesLoader.NEW_LINE);
        log.append(sMessage);
        return log.toString();
    }

    @Override
    public SimpleLoggable clone() {
        return new SimpleLoggable(this);
    }
}
