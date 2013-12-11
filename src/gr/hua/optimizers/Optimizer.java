/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gr.hua.optimizers;

import gr.hua.data_manipulation.DataManager;
import gr.hua.weka_bridge.classifiers.JClassifier;
import java.util.Properties;

/**
 *
 * @author NickZorb
 */
public interface Optimizer {
    
    void setUp(JClassifier classifier, DataManager manager, Properties props );
    void run();
    void pause();
    void resume();
    void end();
    
}