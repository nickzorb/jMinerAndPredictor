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

import gr.hua.gui.MainMenu;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.JTextArea;

public final class Logger {

    private static class LoggingThread extends Thread {

        private final PrintStream outputStream;
        private final ConcurrentLinkedQueue<String> queue;
        private final Object lock;

        public LoggingThread(final PrintStream output,
                final ConcurrentLinkedQueue<String> input, final Object oLock) {
            outputStream = output;
            queue = input;
            lock = oLock;
        }

        private void write() {
            String sMessage;
            while ((sMessage = queue.poll()) != null) {
                outputStream.println(sMessage);
            }
        }

        @Override
        public void run() {
            while (true) {
                write();
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException ex) {
                        write();
                        return;
                    }
                }
            }
        }
    }
    private static final String LOG_FILE = "history.log";
    private static final String ERROR_LOG_FILE = "error.log";
    private static final PrintStream OUT;
    private static final PrintStream LOG;
    private static final PrintStream ERROR_LOG;
    private static final ConcurrentLinkedQueue<String> LOG_QUEUE;
    private static final ConcurrentLinkedQueue<String> ERROR_QUEUE;
    private static final ConcurrentLinkedQueue<String> ALL_QUEUE;
    private static final LoggingThread NORMAL_LOGGER;
    private static final LoggingThread ERROR_LOGGER;
    private static final LoggingThread ALL_LOGGER;
    private static final Object NORMAL_LOCK = new Object();
    private static final Object ERROR_LOCK = new Object();
    private static final Object ALL_LOCK = new Object();

    static {
        try {
            OUT = new PrintStream(new JTAOutputStream((JTextArea) MainMenu.COMPONENTS.get(MainMenu.LOG_AREAS).get(0)));
            LOG = new PrintStream(new FileOutputStream(LOG_FILE, true));
            ERROR_LOG =
                    new PrintStream(new FileOutputStream(ERROR_LOG_FILE, true));
            LOG_QUEUE = new ConcurrentLinkedQueue();
            ERROR_QUEUE = new ConcurrentLinkedQueue();
            ALL_QUEUE = new ConcurrentLinkedQueue();
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
        NORMAL_LOGGER = new LoggingThread(LOG, LOG_QUEUE, NORMAL_LOCK);
        ERROR_LOGGER = new LoggingThread(ERROR_LOG, ERROR_QUEUE, ERROR_LOCK);
        ALL_LOGGER = new LoggingThread(OUT, ALL_QUEUE, ALL_LOCK);
        NORMAL_LOGGER.start();
        ERROR_LOGGER.start();
        ALL_LOGGER.start();
    }

    private Logger() {
        throw new RuntimeException("This logger should never be initialized",
                new RuntimeException());
    }

    public static void log(final Loggable l) {
        log(l.log());
    }

    private static void log(final String s) {
        if (s == null) {
            return;
        }
        LOG_QUEUE.add(s);
        ALL_QUEUE.add(s);
        if (NORMAL_LOGGER.getState() == Thread.State.WAITING) {
            synchronized (NORMAL_LOCK) {
                NORMAL_LOCK.notify();
            }
        }
        if (ALL_LOGGER.getState() == Thread.State.WAITING) {
            synchronized (ALL_LOCK) {
                ALL_LOCK.notify();
            }
        }
    }

    public static void logError(final Loggable l) {
        logError(l.log());
    }

    private static void logError(final String s) {
        if (s == null) {
            return;
        }
        ERROR_QUEUE.add(s);
        ALL_QUEUE.add(s);
        if (ERROR_LOGGER.getState() == Thread.State.WAITING) {
            synchronized (ERROR_LOCK) {
                ERROR_LOCK.notify();
            }
        }
        if (ALL_LOGGER.getState() == Thread.State.WAITING) {
            synchronized (ALL_LOCK) {
                ALL_LOCK.notify();
            }
        }
    }

    public static void logException(final Exception ex) {
        logError(ex.getLocalizedMessage());
    }

    public static void close() {
        NORMAL_LOGGER.interrupt();
        ERROR_LOGGER.interrupt();
        ALL_LOGGER.interrupt();
    }
}