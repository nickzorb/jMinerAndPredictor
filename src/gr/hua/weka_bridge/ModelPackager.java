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

import gr.hua.data_structures.Statistics;
import gr.hua.weka_bridge.classifiers.JClassifier;
import java.io.Serializable;

/**
 *
 * @author NickZorb
 */
public class ModelPackager implements Serializable {

    private JClassifier classifier;
    private Statistics[] statistics;

    public ModelPackager(JClassifier classifier, Statistics[] statistics) {
        this.classifier = classifier;
        this.statistics = statistics;
    }

    public JClassifier getClassifier() {
        return classifier;
    }

    public void setClassifier(JClassifier classifier) {
        this.classifier = classifier;
    }

    public Statistics[] getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics[] statistics) {
        this.statistics = statistics;
    }
    
    
}