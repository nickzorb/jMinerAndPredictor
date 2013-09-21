package gr.hua.gui_utils;

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
