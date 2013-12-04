package gr.hua.weka_bridge;

import java.util.ArrayList;
import weka.core.Attribute;

public interface AttributeSelector {

    ArrayList<ArrayList<Attribute>> getAttributeSets(ArrayList<Attribute> attr);
}
