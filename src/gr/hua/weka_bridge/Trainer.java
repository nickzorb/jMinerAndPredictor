package gr.hua.weka_bridge;

import gr.hua.data_structures.DataRow;
import gr.hua.gui.DataMiner.MiningResultsPanel;
import gr.hua.gui.MainMenu;
import gr.hua.utils.Logger;
import gr.hua.utils.SimpleLoggable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Random;
import java.util.zip.GZIPOutputStream;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class Trainer extends Thread {

    public static final String TRAINED_CLASSIFIER_PATH = "./classifiers/";
    public static final String OPTIMIZE_ATTRIBUTES = "-optAtt";
    public static final String SET_TOP_RESULTS = "-top";
    public static final String SET_LIMIT = "-limit";
    public static final int STOPPED = 0;
    public static final int RUNNING = 1;
    private int TOP_RESULTS = 3;
    private Classifier classifier;
    private MiningResultsPanel outputPanel;
    private ArrayList<Instance> instances = new ArrayList();
    private ArrayList<CloneableAttribute> attributes = new ArrayList();
    private CloneableAttribute target;
    private Properties properties;
    private static final int FOLDS = 10;
    private boolean run = true;
    private int n = 1;
    private FastVector curAttributes;
    private Instances trainingSet;
    private double max = 0;
    private String[] descriptions;
    private String[] classifiers;
    private String[] attributesFile;
    private FastVector[] usedAttributes;
    private double[] results;
    private double min = -1;
    private int limit;

    public Trainer(Classifier c, MiningResultsPanel output,
            ArrayList<CloneableAttribute> attrs,
            CloneableAttribute targetAttr, Properties p) {
        classifier = c;
        outputPanel = output;
        attributes = new ArrayList();
        for (CloneableAttribute ca : attrs) {
            attributes.add(ca.clone());
        }
        properties = p;
        target = targetAttr.clone();
    }

    public void stopMining() {
        run = false;
    }

    private void loopAttributes(int startingIndex, int attributesNumber)
            throws Exception {
        if (attributesNumber == 0) {
            //add target attribute last
            curAttributes.addElement(target);
            //done setting attributes, set instances
            buildInstances();
            curAttributes.removeElementAt(n);
            return;
        }
        for (int i = startingIndex + 1; i - 1 <= attributes.size()
                - attributesNumber; i++) {
            curAttributes.addElement(attributes.get(i - 1));
            loopAttributes(i, attributesNumber - 1);
            curAttributes.removeElementAt(n - attributesNumber);
        }
    }

    private void buildInstances() throws Exception {
        int limit = MainMenu.MANAGER.getNumberOfRows();
        //Setup training set
        trainingSet = new Instances("Training Set", curAttributes, limit);
        trainingSet.setClassIndex(n);
        //Setup instances
        instances = new ArrayList(limit);
        for (int j = 0; j < limit; j++) {
            DataRow curRow = MainMenu.MANAGER.get(j);
            instances.add(curRow.getInstance(curAttributes, trainingSet));
        }
        for (Instance i : instances) {
            trainingSet.add(i);
        }
        collectOptions();
    }

    private void collectOptions() {
        //TODO manage classifiers options
        buildClassifier();
    }

    private void buildClassifier() {
        if (outputPanel.getStatus() == STOPPED) {
            synchronized (outputPanel.getLock()) {
                outputPanel.stopped();
                try {
                    outputPanel.getLock().wait();
                } catch (InterruptedException ex) {
                    Logger.logException(ex);
                    ex.printStackTrace();
                }
            }
        }
        try {
            outputPanel.updateAttributes(curAttributes);
            classifier.buildClassifier(trainingSet);
            Evaluation eTest = new Evaluation(trainingSet);
            eTest.crossValidateModel(classifier, trainingSet, FOLDS,
                    new Random(1));
            double cur = eTest.correct() / (eTest.correct()
                    + eTest.incorrect());
            if (cur > max) {
                max = cur;
            }
            outputPanel.updateText("Best so far: " + (max * 100) + "%\n"
                    + eTest.toSummaryString() + "\n" + eTest.toMatrixString());
            if (cur > min) {
                int pos = -1;
                if (min == -1) {
                    pos = 0;
                } else {
                    for (int i = 0; i < TOP_RESULTS; i++) {
                        if (results[i] == min) {
                            pos = i;
                            break;
                        }
                    }
                }
                if (results[pos] != 0) {
                    File f = new File(classifiers[pos]);
                    f.delete();
                    f = new File(attributesFile[pos]);
                    f.delete();
                }
                results[pos] = cur;
                StringBuilder className;
                className = new StringBuilder(classifier.getClass().toString());
                className = className.delete(0, className.lastIndexOf(".") + 1);
                StringBuilder curName = new StringBuilder(TRAINED_CLASSIFIER_PATH);
                SimpleDateFormat formatter = new SimpleDateFormat("_MM_dd_m-S");
                String curDate = formatter.format(
                        GregorianCalendar.getInstance().getTimeInMillis());
                curName.append(MainMenu.CURRENT_FILE.substring(0, MainMenu.CURRENT_FILE.indexOf("."))).append("_");
                curName.append(className).append(curDate).append(".model");
                classifiers[pos] = curName.toString();
                OutputStream os = new FileOutputStream(curName.toString());
                try (ObjectOutputStream oos = new ObjectOutputStream(
                                new GZIPOutputStream(os))) {
                    oos.writeObject(classifier);
                }
                curName.setLength(curName.length() - 6);
                curName.append(".data");
                attributesFile[pos] = curName.toString();
                os = new FileOutputStream(curName.toString());
                try (ObjectOutputStream oos = new ObjectOutputStream(
                                new GZIPOutputStream(os))) {
                    oos.writeObject(curAttributes);
                }
                descriptions[pos] = "";
                usedAttributes[pos] = new FastVector(curAttributes.size());
                for (Object o : curAttributes.toArray()) {
                    usedAttributes[pos].addElement(o);
                }
                min = cur;
                for (int i = 0; i < TOP_RESULTS; i++) {
                    if (results[i] < min) {
                        min = results[i];
                    }
                }
            }
        } catch (Exception e) {
            Logger.logException(e);
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            //Setup attributes and run training
            if (properties.getProperty(SET_TOP_RESULTS) != null) {
                try {
                    int n = Integer.parseInt(properties.getProperty(SET_TOP_RESULTS));
                    if (n > 0) {
                        TOP_RESULTS = n;
                    } else {
                        Logger.logError(new SimpleLoggable("Invalid top results count", this));
                    }
                } catch (Exception e) {
                    Logger.logException(e);
                }
            }
            descriptions = new String[TOP_RESULTS];
            classifiers = new String[TOP_RESULTS];
            attributesFile = new String[TOP_RESULTS];
            usedAttributes = new FastVector[TOP_RESULTS];
            results = new double[TOP_RESULTS];
            if (properties.getProperty(OPTIMIZE_ATTRIBUTES) != null) {
                if (properties.getProperty(SET_LIMIT) != null) {
                    limit = Integer.parseInt(properties.getProperty(SET_LIMIT));
                } else {
                    limit  = attributes.size();
                }
                while (run) {
                    curAttributes = new FastVector(n + 1);
                    loopAttributes(0, n);
                    //done training move on
                    if (n < limit) {
                        n++;
                    } else {
                        run = false;
                        outputPanel.showFinishedScreen(usedAttributes, results,
                                descriptions, classifiers);
                    }
                }
            } else {
                n = attributes.size();
                curAttributes = new FastVector(n + 1);
                for (CloneableAttribute att : attributes) {
                    curAttributes.addElement(att);
                }
                curAttributes.addElement(target);
                buildInstances();
                outputPanel.showFinishedScreen(usedAttributes, results,
                        descriptions, classifiers);
            }
        } catch (Exception e) {
            Logger.logException(e);
            e.printStackTrace();
            run = false;
        }
    }
}
