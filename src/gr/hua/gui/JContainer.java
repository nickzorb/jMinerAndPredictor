package gr.hua.gui;

import java.awt.Component;
import java.util.ArrayList;

public interface JContainer {

    void registerComponent(String name, Component element);

    ArrayList<Component> getComponents(String name);
}