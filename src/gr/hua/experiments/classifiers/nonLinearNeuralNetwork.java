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
package gr.hua.experiments.classifiers;

import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.OptionHandler;
import weka.core.Randomizable;
import weka.core.WeightedInstancesHandler;

public class nonLinearNeuralNetwork extends Classifier
        implements OptionHandler, WeightedInstancesHandler, Randomizable {

    private static final long serialVersionUID = 6102300921865532295L;
    
    public static void main(String [] argv) {
        runClassifier(new nonLinearNeuralNetwork(), argv);
    }
    
    @Override
    public void buildClassifier(Instances data) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSeed(int seed) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getSeed() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
