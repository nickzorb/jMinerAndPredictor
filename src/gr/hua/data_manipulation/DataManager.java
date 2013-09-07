/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.hua.data_manipulation;

import gr.hua.data_structures.ColumnValue;
import gr.hua.data_structures.DataColumn;
import gr.hua.data_structures.DataRow;
import gr.hua.data_structures.StringValue;
import gr.hua.history.HistoryManager;
import gr.hua.utils.Logger;
import gr.hua.utils.PropertiesLoader;
import gr.hua.utils.SimpleLoggable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private Properties properties;
    private boolean ready = false;

    public DataManager() {
        rows = new HistoryManager();
        columns = new HistoryManager();
        loadDefaultProperties();
    }

    public DataManager(Properties p) {
        properties = p;
        rows = new HistoryManager();
        columns = new HistoryManager();
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

    public void saveFile(File file) {
        if (!ready) {
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(file), properties.getProperty(W_ENCODING)))) {
            bw.write(columnsToFile());
            for (DataRow r : rows.getLatestState()) {
                bw.write(rowToFile(r));
            }
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

    private String rowToFile(DataRow r) {
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

    public ColumnValue get(int i, int j) {
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

    public int readFile(File f) {
        ready = true;
        if (!readData(f) || !evaluateData()) {
            ready = false;
            return -1;
        }
        save();
        return 0;
    }

    public void save() {
        if (!ready) {
            return;
        }
        rows.save();
        columns.save();
    }

    public void revert() {
        if (!ready) {
            return;
        }
        rows.revert();
        columns.revert();
    }

    public void redo() {
        if (!ready) {
            return;
        }
        rows.redo();
        columns.redo();
    }

    public void validate() {
        for (DataColumn d : columns.getLatestState()) {
            d.revalidate();
        }
    }

    public void showData(JTable table) {
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
            String replacement = "aeg141t4gggggggga4g15t35h36h111135y35h13";
            while (fileContents.indexOf(replacement) != -1) {
                replacement += "135f1f14aegg141rgdg24";
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
            String[] tmpColumns = lines[0].split(columnSeparators);
            for (String s : tmpColumns) {
                while (s.contains(replacement)) {
                    s = s.replaceFirst(replacement, literals.get(0));
                    literals.remove(0);
                }
                DataColumn d = new DataColumn(s);
                dataColumns.add(d);
            }
            for (int i = 1; i < lines.length; i++) {
                String[] values = lines[i].split(columnSeparators);
                DataRow tmpRow = new DataRow();
                for (int j = 0; j < values.length; j++) {
                    if (values[j].matches(nullValues) || values[j].equals("")) {
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
            save();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.logException(e);
            return false;
        }
        return true;
    }

    @Override
    public int applyAction(Action a) {
        if (!ready) {
            return -1;
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Action[] getCounterActions() {
        if (!ready) {
            return null;
        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteColumn(int i) {
        if (!ready) {
            return;
        }
        ArrayList<DataColumn> tmpColumns = new ArrayList();
        for (DataColumn d : columns.getLatestState()) {
            tmpColumns.add(d.clone());
        }
        ArrayList<DataRow> tmpRows = new ArrayList();
        for (DataRow d : rows.getLatestState()) {
            tmpRows.add(d.clone());
        }
        tmpColumns.remove(i);
        for (DataRow r : tmpRows) {
            r.remove(i);
        }
        columns.setAlteredState(tmpColumns);
        rows.setAlteredState(tmpRows);
        save();
    }

    public void deleteRow(int i) {
        if (!ready) {
            return;
        }
        ArrayList<DataColumn> tmpColumns = new ArrayList();
        for (DataColumn d : columns.getLatestState()) {
            tmpColumns.add(d.clone());
        }
        ArrayList<DataRow> tmpRows = new ArrayList();
        for (DataRow d : rows.getLatestState()) {
            tmpRows.add(d.clone());
        }
        for (int j = 0; j < tmpColumns.size(); j++) {
            if (j < tmpRows.get(i).size()) {
                tmpRows.get(i).get(j).alterPopulation(-1);
            }
        }
        tmpRows.remove(i);
        columns.setAlteredState(tmpColumns);
        rows.setAlteredState(tmpRows);
        save();
    }
}