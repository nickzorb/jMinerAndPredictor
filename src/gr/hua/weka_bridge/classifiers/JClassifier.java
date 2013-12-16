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
package gr.hua.weka_bridge.classifiers;

import java.io.Serializable;
import weka.classifiers.Classifier;

public abstract class JClassifier implements Serializable {

    private final Classifier classifier;
    private final String name;
    private final String info;
    private final String[] supportedMeasurements;
    private final String[] removedOptions;
    private final Class[] classifiers = {JC4_5.class, JLibSVM.class,
        JLogisticRegression.class, JNaiveBayes.class, JNeuralNetwork.class,
        JRipper.class, JRotationForest.class};

    public static final JClassifier[] CLASSIFIERS = {};

    protected JClassifier(Classifier classifier, String name,
            String[] supportedMeasurements, String[] removedOptions,
            String info) {
        this.classifier = classifier;
        this.name = name;
        this.supportedMeasurements = supportedMeasurements;
        this.removedOptions = removedOptions;
        this.info = info;
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public String getName() {
        return name;
    }

    public String[] getSupportedMeasurements() {
        return supportedMeasurements;
    }

    public String getInfo() {
        return info;
    }

}
