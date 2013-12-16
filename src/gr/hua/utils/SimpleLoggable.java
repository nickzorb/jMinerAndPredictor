/* 
 * Copyright (C) 2013 Harokopio University <Nikolaos Zormpas>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
