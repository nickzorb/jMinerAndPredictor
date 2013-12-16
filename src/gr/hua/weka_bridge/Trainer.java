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
    public static final String SET_LOW_LIMIT = "-lowlimit";
    public static final String LEAVE_ONE_OUT = "-loo";
    public static final String STATISTICAL = "-st";
    public static final int STOPPED = 0;
    public static final int RUNNING = 1;
    private int TOP_RESULTS = 3;
    private Classifier classifier;
    private MiningResultsPanel outputPanel;
    private ArrayList<Instance> instances = new ArrayList();
    private ArrayList<CloneableAttribute> attributes = new ArrayList();
    private CloneableAttribute target;
    private Properties properties;
    private static int folds = 10;
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
    private String statistic;

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
            curAttributes.addElement(target);
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
                }
            }
        }
        try {
            outputPanel.updateAttributes(curAttributes);
            Evaluation eTest = new Evaluation(trainingSet);
            //////////////////////////////////////////////
            ArrayList<Double> evalResults = new ArrayList();
            Random r = new Random(1);
//            trainingSet = new Instances(trainingSet);
//            trainingSet.randomize(r);
//            if (trainingSet.classAttribute().isNominal()) {
//                trainingSet.stratify(folds);
//            }
//            for (int i = 0; i < folds; i++) {
//                Instances train = trainingSet.trainCV(folds, i, r);
//                eTest.setPriors(train);
//                Classifier copiedClassifier = Classifier.makeCopy(classifier);
//                copiedClassifier.buildClassifier(train);
//                Instances test = trainingSet.testCV(folds, i);
//                double[] res = eTest.evaluateModel(copiedClassifier, test);
//                for (int j = 0; j < res.length; j++) {
//                    evalResults.add(res[j]);
//                    System.out.println(test.instance(j).classValue() + ":" + res[j]);
//                }
//            }
            //////////////////////////////////////////////
//            StringBuffer predictions = new StringBuffer("Predictions: \n");
//            eTest.crossValidateModel(classifier, trainingSet, folds, new Random(1), predictions, new Range("1"), false);
            eTest.crossValidateModel(classifier, trainingSet, folds, new Random(1));
            ///////////////////////////////////////////// 
//            String[] lines = predictions.toString().split("\n", -1);
//            double[] values = new double[lines.length - 1];
//            double[] predics = new double[lines.length - 1];
//            for (int i = 1; i < lines.length; i++) {
//                String[] words = lines[i].split("[^\\d-.]+", -1);
//                if (words.length < 4) {
//                    continue;
//                }
//                try {
//                    values[i - 1] = Double.parseDouble(words[2]);
//                    if (!(values[i - 1] + "").equals(words[2])) {
//                        values[i - 1] = Integer.parseInt(words[2]);
//                    }
//                } catch (Exception e) {
//                    if (!(values[i - 1] + "").equals(words[2])) {
//                        values[i - 1] = Integer.parseInt(words[2]);
//                    }
//                }
//                try {
//                    predics[i - 1] = Double.parseDouble(words[3]);
//                    if (!(predics[i - 1] + "").equals(words[3])) {
//                        predics[i - 1] = Integer.parseInt(words[3]);
//                    }
//                } catch (Exception e) {
//                    if (!(predics[i - 1] + "").equals(words[3])) {
//                        predics[i - 1] = Integer.parseInt(words[3]);
//                    }
//                }
//            }
            /////////////////////////////////////////////////////
            double cur;
            StringBuilder summary = new StringBuilder("Best ");
            summary.append(statistic);
            if (target.isNumeric()) {
                cur = eTest.correlationCoefficient();
            } else {
                if (properties.getProperty(STATISTICAL) != null) {
                    cur = eTest.areaUnderROC(0);
                } else {
                    cur = eTest.pctCorrect();
                }
            }
            if (cur > max) {
                max = cur;
            }
            summary.append(max).append("\n");
            try {
                summary.append(eTest.toSummaryString()).append("\n");
                summary.append(eTest.toMatrixString());
            } catch (Exception e) {
                Logger.logException(e);
            }
            outputPanel.updateText(summary.toString());
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
                classifier.buildClassifier(trainingSet);
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
                descriptions[pos] = classifier.toString();
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
        }
    }
    
    private double calculateStatistic(double[] values, double[] predics) {
        throw new UnsupportedOperationException();
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
            if (properties.getProperty(LEAVE_ONE_OUT) != null) {
                folds = MainMenu.MANAGER.getNumberOfRows();
            }
            if (properties.getProperty(STATISTICAL) != null) {
                if (target.isNominal()) {
                    statistic = "ROC area under curve: ";
                } else {
                    statistic = "Correlation Coefficient: ";
                }
            } else {
                if (target.isNominal()) {
                    statistic = "Success rate: ";
                } else {
                    statistic = "Correlation Coefficient: ";
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
                if (properties.getProperty(SET_LOW_LIMIT) != null) {
                    n = Integer.parseInt(properties.getProperty(SET_LOW_LIMIT));
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
                                descriptions, classifiers, statistic);
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
                        descriptions, classifiers, statistic);
            }
        } catch (Exception e) {
            Logger.logException(e);
            run = false;
        }
    }
}
