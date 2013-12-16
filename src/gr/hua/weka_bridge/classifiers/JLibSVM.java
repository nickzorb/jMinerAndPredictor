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

import gr.hua.data_structures.basic.Measures;
import weka.classifiers.functions.LibSVM;

/**
 *
 * @author NickZorb
 */
public class JLibSVM extends JClassifier {

    public JLibSVM() {
        super(new  LibSVM(), "SVM",
                new String[]{Measures.ROC_STATISTIC,
                    Measures.SUCCESS_PERCENTAGE,
                    Measures.KAPPA_STATISTIC},
                new String[]{"-J", "-V", "-M", "-E", "-W", "-D"}, "A support"
                        + " vector machine algorithm implementation provided by"
                        + " Chih-Chung Chang and Chih-Jen Lin");
    }
}
