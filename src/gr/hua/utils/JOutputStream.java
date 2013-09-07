package gr.hua.utils;

import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTextArea;

class JOutputStream extends OutputStream {

    private JTextArea log;

    public JOutputStream(JTextArea LOG_AREA) {
        log = LOG_AREA;
    }

    @Override
    public void write(int b) throws IOException {
        log.append(((char) b) + "");
        log.setCaretPosition(log.getDocument().getLength());
    }
}
