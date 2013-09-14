package gr.hua.gui;

import gr.hua.data_manipulation.DataManager;
import gr.hua.gui_utils.TabGenerator;
import gr.hua.utils.Logger;
import gr.hua.utils.SimpleLoggable;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author r0t0r
 */
public final class MainMenu extends JFrame implements JContainer {

    public static final String LOG_AREAS = "LOG AREA";
    public static final String DATA_AREAS = "DATA AREA";
    public static final String MAIN_WINDOW = "ORIGIN";
    public static String CURRENT_FILE = null;
    public static final HashMap<String, ArrayList<Component>> COMPONENTS =
            new HashMap();
    public static final DataManager MANAGER = new DataManager();
    public static MainMenu main;
    private ArrayList<Tab> tabs;

    @SuppressWarnings("LeakingThisInConstructor")
    private MainMenu() {
        initComponents();
        setTitle("Data Miner");
        main = this;
        registerComponent(MAIN_WINDOW, this);
        tabs = new ArrayList();
        for (int i = 0; i < TabGenerator.numberOfTabs; i++) {
            tabs.add(TabGenerator.generateTab(this, i));
        }
        for (Tab tab : tabs) {
            jTabbedPane.addTab(tab.getTitle(), tab);
        }
        Logger.log(new SimpleLoggable("Ready", this));
    }

    public void refressContainers(final JTable contents) {
        for (Component container : getComponents(DATA_AREAS)) {
            JTable temp = new JTable(contents.getModel(),
                    contents.getColumnModel(), contents.getSelectionModel());
            JPanel curContainer;
            try {
                curContainer = (JPanel) container;
            } catch (Exception e) {
                Logger.logException(e);
                Logger.logError(new SimpleLoggable("Failed to load tables",
                        this));
                return;
            }
            curContainer.removeAll();
            curContainer.add(temp.getTableHeader(), BorderLayout.PAGE_START);
            curContainer.add(temp, BorderLayout.CENTER);
            container.revalidate();
        }
    }

    @Override
    public void registerComponent(String name, Component element) {
        if (COMPONENTS.containsKey(name)) {
            COMPONENTS.get(name).add(element);
        } else {
            ArrayList<Component> components = new ArrayList();
            components.add(element);
            COMPONENTS.put(name, components);
        }
    }

    @Override
    public ArrayList<Component> getComponents(String name) {
        return COMPONENTS.get(name);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(840, 680));
        setResizable(false);

        jTabbedPane.setMaximumSize(new java.awt.Dimension(800, 640));
        jTabbedPane.setMinimumSize(new java.awt.Dimension(800, 640));
        jTabbedPane.setPreferredSize(new java.awt.Dimension(800, 640));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane;
    // End of variables declaration//GEN-END:variables
}
