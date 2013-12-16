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
package gr.hua.gui.utils;

import gr.hua.gui.Tab;
import gr.hua.gui.Predictor.PredictorTab;
import gr.hua.gui.MainMenu;
import gr.hua.gui.Preprocessor.PreprocessorTab;
import gr.hua.gui.DataMiner.DataMinerTab;
import gr.hua.gui.FileManager.FileManagerTab;

public class TabGenerator {

    public static final int numberOfTabs = 4;
    public static final int FileManagerTab = 0;
    public static final int PreprocessingTab = 1;
    public static final int DataMiningTab = 2;
    public static final int PrognosisTab = 3;
    private static String[] tabTitles = {"File manager", "Preprocessor", "Data Miner", "Predictor"};

    public static Tab generateTab(MainMenu parent, int tabId) {
        switch (tabId) {
            case FileManagerTab:
                return new FileManagerTab(parent, tabTitles[tabId]);
            case PreprocessingTab:
                return new PreprocessorTab(parent, tabTitles[tabId]);
            case DataMiningTab:
                return new DataMinerTab(parent, tabTitles[tabId]);
            case PrognosisTab:
                return new PredictorTab(parent, tabTitles[tabId]);
            default:
                return null;
        }
    }
}
