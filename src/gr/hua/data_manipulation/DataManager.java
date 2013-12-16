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
package gr.hua.data_manipulation;

import gr.hua.data_structures.basic.DataValue;
import gr.hua.data_structures.DataColumn;
import gr.hua.data_structures.DataRow;
import gr.hua.data_structures.basic.StringValue;
import gr.hua.gui.MainMenu;
import gr.hua.gui.Preprocessor.FNRDialog;
import gr.hua.utils.HistoryManager;
import gr.hua.utils.Logger;
import gr.hua.utils.PropertiesLoader;
import gr.hua.utils.SimpleLoggable;
import java.awt.BorderLayout;
import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author r0t0r
 */
public class DataManager implements ActionHandler {

    /**
     * Properties for reading files
     */
    public static final String R_LITERAL_START = "READ LITERAL START";
    public static final String R_LITERAL_END = "READ LITERAL END";
    public static final String R_NULL_VALUES = "READ NULL VALUES";
    public static final String R_ENCODING = "READ ENCODING";
    public static final String R_LINE_BREAK = "READ LINE SEPARATOR";
    public static final String R_COLUMN_BREAK = "READ COLUMN SEPARATOR";
    /**
     * Properties for writing files
     */
    public static final String W_LITERAL_START = "WRITE LITERAL START";
    public static final String W_LITERAL_END = "WRITE LITERAL END";
    public static final String W_NULL = "WRITE NULL";
    public static final String W_ENCODING = "WRITE ENCODING";
    public static final String W_LINE_BREAK = "WRITE LINE SEPARATOR";
    public static final String W_COLUMN_BREAK = "WRITE COLUMN SEPARATOR";
    /**
     * A list with all properties for looping through them
     */
    public static final String[] DATA_MINER_PROPERTIES = {R_LITERAL_START,
        R_LITERAL_END, R_NULL_VALUES, R_ENCODING, R_LINE_BREAK, R_COLUMN_BREAK,
        W_LITERAL_START, W_LITERAL_END, W_NULL, W_ENCODING, W_LINE_BREAK,
        W_COLUMN_BREAK};
    /**
     * Loaded Data (Current State).
     */
    private HistoryManager<ArrayList<DataColumn>> columns;
    private HistoryManager<ArrayList<DataRow>> rows;
    private HistoryManager<ArrayList<Action>> recentActions;
    private Properties properties;
    private boolean ready = false;
    private ArrayList<DataColumn> tempColumns;
    private ArrayList<DataRow> tempRows;
    private ArrayList<Action> tempActions;

    public DataManager() {
        rows = new HistoryManager();
        columns = new HistoryManager();
        recentActions = new HistoryManager();
        loadDefaultProperties();
    }

    public DataManager(Properties p) {
        properties = p;
        rows = new HistoryManager();
        columns = new HistoryManager();
        recentActions = new HistoryManager();
    }

    public final void loadDefaultProperties() {
        properties = new Properties();
        for (String s : DATA_MINER_PROPERTIES) {
            if (s != null) {
                properties.put(s, PropertiesLoader.getProperty(s));
            }
        }
    }

    public void setProperties(Properties p) {
        properties = p;
    }

    public Properties getProperties() {
        return properties;
    }

    public ArrayList<Action> getChanges() {
        return recentActions.getLatestState();
    }

    public void saveFile(File file) {
        if (!ready) {
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), properties.getProperty(W_ENCODING)))) {
            bw.write(columnsToFile());
            bw.write(rowsToFile());
        } catch (Exception e) {
            Logger.logException(e);
        }
    }

    private String columnsToFile() {
        String[] literalStart = properties.getProperty(W_LITERAL_START)
                .split(PropertiesLoader.STRING_SEPERATOR);
        String[] literalEnd = properties.getProperty(W_LITERAL_END)
                .split(PropertiesLoader.STRING_SEPERATOR);
        String[] columnSeparators = properties.getProperty(W_COLUMN_BREAK)
                .split(PropertiesLoader.STRING_SEPERATOR);
        String[] rowSeparators = properties.getProperty(W_LINE_BREAK)
                .split(PropertiesLoader.STRING_SEPERATOR);
        String[] nullPlaceholder = properties.getProperty(W_NULL)
                .split(PropertiesLoader.STRING_SEPERATOR);
        ArrayList<String> badValues = new ArrayList();
        badValues.addAll(Arrays.asList(columnSeparators));
        badValues.addAll(Arrays.asList(rowSeparators));
        StringBuilder res = new StringBuilder("");
        for (DataColumn d : columns.getLatestState()) {
            String tmp = d.getName();
            if (d == null) {
                tmp = nullPlaceholder[0];
            } else {
                if (tmp.contains(literalStart[0])) {
                    tmp = tmp.replace(literalStart[0], "");
                }
                if (tmp.contains(literalEnd[0])) {
                    tmp = tmp.replace(literalEnd[0], "");
                }
                for (String s : badValues) {
                    if (tmp.contains(s)) {
                        tmp = literalStart[0] + tmp + literalEnd[0];
                    }
                }
            }
            res.append(tmp).append(columnSeparators[0]);
        }
        res.deleteCharAt(res.length() - 1);
        res.append(rowSeparators[0]);
        return res.toString();
    }

    private String rowsToFile() {
        String[] literalStart = properties.getProperty(W_LITERAL_START)
                .split(PropertiesLoader.STRING_SEPERATOR);
        String[] literalEnd = properties.getProperty(W_LITERAL_END)
                .split(PropertiesLoader.STRING_SEPERATOR);
        String[] columnSeparators = properties.getProperty(W_COLUMN_BREAK)
                .split(PropertiesLoader.STRING_SEPERATOR);
        String[] rowSeparators = properties.getProperty(W_LINE_BREAK)
                .split(PropertiesLoader.STRING_SEPERATOR);
        String[] nullPlaceholder = properties.getProperty(W_NULL)
                .split(PropertiesLoader.STRING_SEPERATOR);
        ArrayList<String> badValues = new ArrayList();
        badValues.addAll(Arrays.asList(columnSeparators));
        badValues.addAll(Arrays.asList(rowSeparators));
        StringBuilder res = new StringBuilder("");
        for (DataRow row : rows.getLatestState()) {
            StringBuilder line = new StringBuilder("");
            for (String tmp : row.toArray()) {
                if (tmp == null) {
                    tmp = nullPlaceholder[0];
                } else {
                    if (tmp.contains(literalStart[0])) {
                        tmp = tmp.replace(literalStart[0], "");
                    }
                    if (tmp.contains(literalEnd[0])) {
                        tmp = tmp.replace(literalEnd[0], "");
                    }
                    for (String s : badValues) {
                        if (tmp.contains(s)) {
                            tmp = literalStart[0] + tmp + literalEnd[0];
                        }
                    }
                }
                line.append(tmp).append(columnSeparators[0]);
            }
            line.deleteCharAt(line.length() - 1);
            res.append(line).append(rowSeparators[0]);
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

    public DataValue get(int i, int j) {
        if (!ready) {
            return null;
        }
        if (i >= rows.getLatestState().size() || i == -1) {
            Logger.logError(new SimpleLoggable("Out of bounds. Requested line: "
                    + i + "out of " + rows.getLatestState().size(), this));
        } else if (j >= columns.getLatestState().size() || i == -1) {
            Logger.logError(new SimpleLoggable("Out of bounds. Requested column"
                    + ": " + j + "out of " + columns.getLatestState().size(),
                    this));
        } else {
            return rows.getLatestState().get(i).get(j);
        }
        return null;
    }

    public DataRow get(int i) {
        if (!ready) {
            return null;
        }
        if (i >= rows.getLatestState().size() || i == -1) {
            Logger.logError(new SimpleLoggable("Out of bounds. Requested line: "
                    + i + "out of " + rows.getLatestState().size(), this));
        } else {
            return rows.getLatestState().get(i);
        }
        return null;
    }

    public DataColumn getColumn(int i) {
        if (!ready) {
            return null;
        }
        if (i >= columns.getLatestState().size() || i == -1) {
            Logger.logError(new SimpleLoggable("Out of bounds. Requested column"
                    + ": " + i + "out of " + columns.getLatestState().size(),
                    this));
        } else {
            return columns.getLatestState().get(i);
        }
        return null;
    }

    public int findColumn(String name) {
        int counter = 0;
        for (DataColumn d : columns.getLatestState()) {
            if (d.getName().equals(name)) {
                return counter;
            }
            counter++;
        }
        return -1;
    }

    public int getNumberOfRows() {
        if (!ready) {
            return -1;
        }
        return rows.getLatestState().size();
    }

    public int getNumberOfColumns() {
        if (!ready) {
            return -1;
        }
        return columns.getLatestState().size();
    }

    public String[] getColumnNames() {
        if (!ready()) {
            return null;
        }
        String[] res = new String[columns.getLatestState().size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = columns.getLatestState().get(i).getName();
        }
        return res;
    }

    public boolean readyForMining() {
        return ready && evaluateData();
    }

    public int readFile(File f) {
        ready = true;
        if (!readData(f)) {
            ready = false;
            return -1;
        }
        save();
        if (!evaluateData()) {
            JOptionPane.showConfirmDialog(MainMenu.main, "Some errors occured"
                    + " when reading the file, please make sure to fix them"
                    + " before using the data miner.", "Errors while reading",
                    JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    private void save() {
        if (!ready) {
            return;
        }
        rows.save();
        columns.save();
        recentActions.save();
        for (Component c : MainMenu.COMPONENTS.get(MainMenu.DATA_AREAS)) {
            showData((JPanel) c);
            c.revalidate();
        }
    }

    public void revert() {
        if (!ready) {
            return;
        }
        rows.revert();
        columns.revert();
        recentActions.revert();
        for (Component c : MainMenu.COMPONENTS.get(MainMenu.DATA_AREAS)) {
            showData((JPanel) c);
            c.revalidate();
        }
    }

    public void redo() {
        if (!ready) {
            return;
        }
        rows.redo();
        columns.redo();
        recentActions.redo();
        for (Component c : MainMenu.COMPONENTS.get(MainMenu.DATA_AREAS)) {
            showData((JPanel) c);
            c.revalidate();
        }
    }

    public void validate() {
        for (DataColumn d : columns.getLatestState()) {
            d.revalidate();
        }
    }

    public void showData(JPanel container) {
        JTable table = new JTable();
        table.setEnabled(false);
        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addColumn("Row id");
        int i = 0;
        for (DataColumn d : columns.getLatestState()) {
            model.addColumn(d.getName());
        }
        model.addColumn("Errors");
        for (DataRow r : rows.getLatestState()) {
            ArrayList<String> curRow = new ArrayList();
            curRow.add(i++ + "");
            curRow.addAll(Arrays.asList(r.toArray()));
            model.addRow(curRow.toArray());
        }
        container.removeAll();
        container.add(table.getTableHeader(), BorderLayout.PAGE_START);
        container.add(table, BorderLayout.CENTER);
    }

    public String showColumnData(int i) {
        return columns.getLatestState().get(i).info();
    }

    public String showRowData(int i) {
        return rows.getLatestState().get(i).info();
    }

    public String showColumnData(String s) {
        for (DataColumn d : columns.getLatestState()) {
            if (d.getName().equals(s)) {
                return d.info();
            }
        }
        return null;
    }

    private boolean evaluateData() {
        boolean res = true;
        int size = columns.getLatestState().size();
        ArrayList<String> names = new ArrayList();
        for (DataColumn c : columns.getLatestState()) {
            if (names.contains(c.getName())) {
                Logger.logError(new SimpleLoggable("Some columns have the same "
                        + "name. Please make sure to correct that.", this));
                res = false;
            } else {
                names.add(c.getName());
            }
        }
        for (DataRow r : rows.getLatestState()) {
            if (r != null && r.size() != size) {
                Logger.logError(new SimpleLoggable("Row "
                        + rows.getLatestState().indexOf(r) + " has "
                        + r.size() + " fields instead of " + size, this));
                res = false;
            }
        }
        return res;
    }

    public boolean ready() {
        return ready;
    }

    private int[] getNextToken(StringBuilder target,
            String pattern, int start) {
        try {
            Pattern ls = Pattern.compile(pattern);
            Matcher matcher = ls.matcher(target);
            while (matcher.find()) {
                if (matcher.start() >= start) {
                    int[] res = {matcher.start(), matcher.end()};
                    return res;
                }
            }
        } catch (Exception e) {
            Logger.logError(new SimpleLoggable(
                    "Invalid regural expression for literal start " + pattern,
                    this));
        }
        int[] res = {-1, -1};
        return res;
    }

    private boolean readData(File file) {
        String literalStart = properties.getProperty(R_LITERAL_START);
        String literalEnd = properties.getProperty(R_LITERAL_END);
        String columnSeparators = properties.getProperty(R_COLUMN_BREAK);
        String rowSeparators = properties.getProperty(R_LINE_BREAK);
        String nullValues = properties.getProperty(R_NULL_VALUES);
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(file), properties.getProperty(R_ENCODING))) {
            StringBuilder fileContents = new StringBuilder();
            ArrayList<DataColumn> dataColumns = new ArrayList();
            ArrayList<DataRow> dataRows = new ArrayList();
            while (reader.ready()) {
                fileContents.append((char) reader.read());
            }
            String replacement = "#@#";
            while (fileContents.indexOf(replacement) != -1) {
                replacement += "@#";
            }
            ArrayList<String> literals = new ArrayList();
            if (!literalStart.equals("") || !literalEnd.equals("")) {
                int index = 0;
                do {
                    int[] nextLiteral = getNextToken(fileContents,
                            literalStart, index);
                    if (nextLiteral[0] == -1) {
                        break;
                    }
                    int[] endOfLiteral = getNextToken(fileContents,
                            literalEnd, nextLiteral[1]);
                    if (endOfLiteral[0] == -1) {
                        break;
                    }
                    index = endOfLiteral[1] + 1;
                    literals.add(fileContents.substring(nextLiteral[1] + 1,
                            endOfLiteral[0]));
                    fileContents.replace(nextLiteral[0], index,
                            replacement);
                } while (true);
            }
            String[] lines = fileContents.toString().split(rowSeparators);
            String[] tmpColumns = lines[0].split(columnSeparators, -1);
            for (String s : tmpColumns) {
                while (s.contains(replacement)) {
                    s = s.replaceFirst(replacement, literals.get(0));
                    literals.remove(0);
                }
                DataColumn d = new DataColumn(s);
                dataColumns.add(d);
            }
            for (int i = 1; i < lines.length; i++) {
                String[] values = lines[i].split(columnSeparators, -1);
                DataRow tmpRow = new DataRow();
                for (int j = 0; j < values.length; j++) {
                    if (values[j].matches(nullValues) || values[j].equals("") || values[j].equals("\r")) {
                        values[j] = null;
                    }
                    if (j < dataColumns.size()) {
                        int index = dataColumns.get(j).find(values[j]);
                        if (index >= 0) {
                            tmpRow.add(dataColumns.get(j).get(index));
                            dataColumns.get(j).increment(index);
                        } else {
                            StringValue d = new StringValue(values[j]);
                            dataColumns.get(j).add(d);
                            tmpRow.add(d);
                        }
                    } else {
                        StringValue d = new StringValue(values[j]);
                        tmpRow.add(d);
                        tmpRow.get(dataColumns.size()).setValue(tmpRow.get(dataColumns.size()).getValue() + values[j]);
                    }
                }
                dataRows.add(tmpRow);
            }
            columns.setAlteredState(dataColumns);
            rows.setAlteredState(dataRows);
            ArrayList<Action> actions = new ArrayList();
            actions.add(new Action("Oppened File", null, file.getName()));
            recentActions.setAlteredState(actions);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.logException(e);
            return false;
        }
        return true;
    }

    public void saveChanges() {
    }

    public void loadChanges() {
    }

    @Override
    public int applyAction(Action a) {
        if (!ready) {
            return -1;
        }
        tempActions = new ArrayList();
        for (Action act : recentActions.getLatestState()) {
            tempActions.add(act.clone());
        }
        tempColumns = new ArrayList();
        for (DataColumn d : columns.getLatestState()) {
            tempColumns.add(d.clone());
        }
        tempRows = new ArrayList();
        for (DataRow d : rows.getLatestState()) {
            tempRows.add(d.clone());
        }
        for (DataRow d : tempRows) {
            for (int i = 0; i < tempColumns.size(); i++) {
                String needle = d.get(i).isNull() ? null : d.get(i).getStringValue();
                d.replace(d.get(i), tempColumns.get(i).get(tempColumns.get(i).find(needle)));
            }
        }
        try {
            tempActions.add(a);
            switch (a.getMode()) {
                case Action.CCN:
                    String newName = JOptionPane.showInputDialog(MainMenu.main,
                            "Please provide a new name for the column:",
                            "New Name");
                    rename((int) a.getTarget(), newName);
                    break;
                case Action.CD:
                    String name = JOptionPane.showInputDialog(MainMenu.main,
                            "Please provide a name for the new column:",
                            "New Column");
                    duplicateColumn((int) a.getTarget(), name);
                    break;
                case Action.CFNRV:
                    ArrayList<String> cvalues = new ArrayList();
                    for (String s : tempColumns.get((int) a.getTarget()).getValues()) {
                        cvalues.add(s);
                    }
                    FNRDialog ctemp = new FNRDialog(MainMenu.main, true, cvalues.toArray(new String[10]));
                    fnrColumn((int) a.getTarget(), ctemp.open());
                    break;
                case Action.CR:
                    deleteColumn((int) a.getTarget());
                    break;
                case Action.CTR:
                    if (tempColumns.get((int) a.getTarget()).getType() == String.class) {
                        JOptionPane.showMessageDialog(MainMenu.main, "This can "
                                + "only be performed on numerical columns.");
                        return -1;
                    }
                    JPanel panel = new JPanel();
                    String[] ranges = new String[19];
                    for (int i = 2; i <= 20; i++) {
                        ranges[i - 2] = i + "";
                    }
                    JComboBox rangesCB = new JComboBox();
                    rangesCB.setModel(new DefaultComboBoxModel(ranges));
                    panel.add(rangesCB);
                    panel.add(Box.createHorizontalStrut(15));
                    JRadioButton freq = new JRadioButton("Equal frequency");
                    panel.add(freq);
                    panel.add(Box.createVerticalStrut(15));
                    JRadioButton size = new JRadioButton("Equal range", true);
                    panel.add(size);
                    ButtonGroup group = new ButtonGroup();
                    group.add(size);
                    group.add(freq);
                    int resp = JOptionPane.showConfirmDialog(null, panel,
                            "Number of buckets to use",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (resp == JOptionPane.OK_OPTION) {
                        int buckets = rangesCB.getSelectedIndex() + 2;
                        if (size.isSelected()) {
                            toRanges((int) a.getTarget(), buckets, false);
                        } else {
                            toRanges((int) a.getTarget(), buckets, true);
                        }
                    }
                    break;
                case Action.GFNRV:
                    ArrayList<String> values = new ArrayList();
                    for (DataColumn c : tempColumns) {
                        for (String s : c.getValues()) {
                            if (!values.contains(s)) {
                                values.add(s);
                            }
                        }
                    }
                    FNRDialog temp = new FNRDialog(MainMenu.main, true, values.toArray(new String[10]));
                    fnrGlobal(temp.open());
                    break;
                case Action.GREC:
                    Object[] cselection = new Object[26];
                    for (int i = 0; i <= 25; i++) {
                        cselection[i] = (i * 2.0 + 50.0) / 100.0;
                    }
                    double cLimit = (double) JOptionPane.showInputDialog(
                            MainMenu.main, "Please select the minnimum "
                            + "acceptable fill rate", "Delete empty",
                            JOptionPane.QUESTION_MESSAGE,
                            null, cselection, cselection[24]);
                    deleteEmptyC(cLimit);
                    break;
                case Action.GRER:
                    Object[] rselection = new Object[26];
                    for (int i = 0; i <= 25; i++) {
                        rselection[i] = (i * 2.0 + 50.0) / 100.0;
                    }
                    double rLimit = (double) JOptionPane.showInputDialog(
                            MainMenu.main, "Please select the minnimum "
                            + "acceptable fill rate", "Delete empty",
                            JOptionPane.QUESTION_MESSAGE,
                            null, rselection, rselection[24]);
                    deleteEmptyR(rLimit);
                    break;
                case Action.RD:
                    duplicateRow((int) a.getTarget());
                    break;
                case Action.RFNRV:
                    ArrayList<String> rvalues = new ArrayList();
                    for (String s : tempRows.get((int) a.getTarget()).toArray()) {
                        rvalues.add(s);
                    }
                    FNRDialog rtemp = new FNRDialog(MainMenu.main, true, rvalues.toArray(new String[10]));
                    fnrRow((int) a.getTarget(), rtemp.open());
                    break;
                case Action.RR:
                    deleteRow((int) a.getTarget());
                    break;
                default:
                    Logger.log(new SimpleLoggable("Uknown operation", this));
                    return -1;
            }
        } catch (Exception e) {
            Logger.logException(e);
            e.printStackTrace();
            tempActions.remove(a);
            return -1;
        }
        columns.setAlteredState(tempColumns);
        rows.setAlteredState(tempRows);
        recentActions.setAlteredState(tempActions);
        save();
        return 0;
    }

    private void deleteColumn(int i) {
        if (!ready) {
            return;
        }
        tempColumns.remove(i);
        for (DataRow r : tempRows) {
            r.remove(i);
        }
    }

    private void deleteRow(int i) {
        if (!ready) {
            return;
        }
        DataRow cur = tempRows.get(i);
        for (int j = 0; j < tempColumns.size(); j++) {
            if (j < cur.size()) {
                DataValue curValue = cur.get(j);
                DataColumn curC = tempColumns.get(j);
                curValue.alterPopulation(-1);
                if (curValue.getPopulation() == 0) {
                    curC.remove(curValue);
                }
            }
        }
        tempRows.remove(cur);
    }

    private void duplicateColumn(int d, String n) {
        DataColumn dup = tempColumns.get(d).clone();
        dup.setName(n);
        tempColumns.add(d + 1, dup);
        for (DataRow r : tempRows) {
            r.add(d + 1, dup.get(dup.find(r.get(d).isNull() ? null : r.get(d).getStringValue())));
        }
    }

    private void duplicateRow(int r) {
        DataRow dup = tempRows.get(r).clone();
        tempRows.add(r + 1, dup);
        for (int i = 0; i < dup.size(); i++) {
            String needle = dup.get(i).isNull() ? null : dup.get(i).getStringValue();
            dup.replace(dup.get(i), tempColumns.get(i).get(tempColumns.get(i).find(needle)));
            dup.get(i).alterPopulation(1);
        }
    }

    private void deleteEmptyC(double limit) {
        for (int i = tempColumns.size() - 1; i >= 0; i--) {
            if (tempColumns.get(i).nullPercentage() > 1 - limit) {
                deleteColumn(i);
            }
        }
    }

    private void deleteEmptyR(double limit) {
        for (int i = tempRows.size() - 1; i >= 0; i--) {
            if (tempRows.get(i).nullPercentage() > 1 - limit) {
                deleteRow(i);
            }
        }
    }

    private void rename(int c, String name) {
        tempColumns.get(c).setName(name);
    }

    private void fnrColumn(int c, LinkedList<Change> changes) {
        DataColumn cur = tempColumns.get(c);
        for (Change ch : changes) {
            int indx = cur.find(ch.getValueAffected());
            int indx2 = cur.find(ch.getNewValue());
            if (indx == -1) {
                continue;
            } else if (indx2 == -1) {
                cur.get(indx).setValue(ch.getNewValue());
            } else {
                cur.get(indx2).alterPopulation(cur.get(indx).getPopulation());
                for (DataRow r : tempRows) {
                    r.replace(cur.get(indx), cur.get(indx2));
                }
                cur.remove(cur.get(indx));
            }
        }
    }

    private void fnrRow(int r, LinkedList<Change> changes) {
        DataRow cur = tempRows.get(r);
        for (Change c : changes) {
            for (int i = 0; i < cur.size(); i++) {
                if (cur.get(i).getStringValue().equals(c.getValueAffected())) {
                    int indx = tempColumns.get(i).find(c.getNewValue());
                    DataValue repl;
                    if (indx == -1) {
                        repl = cur.get(i).clone();
                        repl.setPopulation(1);
                        repl.setValue(c.getNewValue());
                        tempColumns.get(i).add(repl);
                    } else {
                        repl = tempColumns.get(i).get(indx);
                        repl.alterPopulation(1);
                    }
                    cur.get(i).alterPopulation(-1);
                    if (cur.get(i).getPopulation() == 0) {
                        tempColumns.get(i).remove(cur.get(i));
                    }
                    cur.replace(cur.get(i), repl);
                }
            }
        }
    }

    private void fnrGlobal(LinkedList<Change> changes) {
        for (int i = 0; i < tempColumns.size(); i++) {
            fnrColumn(i, changes);
        }
    }

    private void toRanges(int c, int buckets, boolean eq) {
        DecimalFormat df = new DecimalFormat("#,###,###,###,###,###.####");
        DataColumn col = tempColumns.get(c);
        double[] info = col.minMaxAvg();
        ArrayList<StringValue> newValues = new ArrayList();
        if (eq) {
            int averageSize = tempRows.size() / buckets;
            int error = tempRows.size() - averageSize * buckets;
            DataValue[] rowValues = new DataValue[col.size()];
            for (int i = 0; i < col.size(); i++) {
                rowValues[i] = col.get(i);
            }
            Arrays.sort(rowValues, new Comparator<DataValue>() {
                @Override
                public int compare(DataValue d1, DataValue d2) {
                    if (d1.getDoubleValue() > d2.getDoubleValue()) {
                        return 1;
                    } else if (d1.getDoubleValue() == d2.getDoubleValue()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });
            DataValue curBucket = new StringValue("bucket");
            curBucket.setPopulation(0);
            double min = 0;
            double max;
            int actualBuckets = 0;
            for (DataValue rowValue : rowValues) {
                if (curBucket.getPopulation() == 0) {
                    min = rowValue.getDoubleValue() - Double.MIN_NORMAL;
                    actualBuckets++;
                }
                curBucket.alterPopulation(rowValue.getPopulation());
                for (int k = 0; k < tempRows.size(); k++) {
                    tempRows.get(k).replace(rowValue, curBucket);
                }
                col.remove(rowValue);
                if (curBucket.getPopulation() >= averageSize + error / 2 && actualBuckets != buckets) {
                    error -= curBucket.getPopulation() - averageSize;
                    max = rowValue.getDoubleValue() + Double.MIN_NORMAL;
                    curBucket.setValue(df.format(min) + " : " + df.format(max));
                    col.add(curBucket);
                    curBucket = new StringValue("bucket");
                    curBucket.setPopulation(0);
                }
                if (rowValue.equals(rowValues[rowValues.length - 1])) {
                    max = rowValue.getDoubleValue() + Double.MIN_NORMAL;
                    curBucket.setValue(df.format(min) + " : " + df.format(max));
                    col.add(curBucket);
                }
            }
        } else {
            double length = info[1] - info[0];
            double step = length / buckets;
            double min = info[0];
            double max = min + step;
            for (int i = 0; i < buckets; i++) {
                StringValue temp = new StringValue(df.format(min) + " : "
                        + df.format(max));
                temp.setPopulation(0);
                for (int j = col.size() - 1; j >= 0; j--) {
                    if (col.get(j).isNull()) {
                        continue;
                    }
                    if (col.get(j).getDoubleValue() < max) {
                        temp.alterPopulation(col.get(j).getPopulation());
                        for (int k = 0; k < tempRows.size(); k++) {
                            tempRows.get(k).replace(col.get(j), temp);
                        }
                        col.remove(col.get(j));
                    }
                }
                newValues.add(temp);
                min = max;
                max = max + step;
                if (i == buckets - 2) {
                    max = info[1] + info[1] / 1000.0;
                }
            }
            for (StringValue s : newValues) {
                if (s.getPopulation() != 0) {
                    col.add(s);
                }
            }
        }
        col.setType(String.class);
    }

    private void arraySort(DataValue[] values) {
        for (int i = 0; i < values.length - 1; i++) {
            int minL = i;
            for (int j = i + 1; j < values.length; j++) {
                if (values[j].getDoubleValue() < values[i].getDoubleValue()) {
                    minL = j;
                }
            }
            if (minL != i) {
                DataValue tempData = values[i];
                values[i] = values[minL];
                values[minL] = tempData;
            }
        }
    }
}
