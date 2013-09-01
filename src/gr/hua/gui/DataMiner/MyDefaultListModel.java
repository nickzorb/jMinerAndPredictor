package gr.hua.gui.DataMiner;

import java.util.Arrays;
import javax.swing.DefaultListModel;

public class MyDefaultListModel extends DefaultListModel<JListItem> {

    @Override
    public JListItem[] toArray() {
        return Arrays.copyOf(super.toArray(), size(), JListItem[].class);
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (JListItem item : toArray()) {
            sb.append(item.toString());
            sb.append(',').append(' ');
        }
        sb.deleteCharAt(sb.length());
        sb.deleteCharAt(sb.length());
        sb.append(']');
        return sb.toString();
    }
}
