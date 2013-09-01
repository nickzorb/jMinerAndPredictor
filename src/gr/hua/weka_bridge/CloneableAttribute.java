package gr.hua.weka_bridge;

import java.util.Collections;
import java.util.List;
import weka.core.Attribute;
import weka.core.FastVector;

public class CloneableAttribute extends Attribute implements Cloneable {

    public CloneableAttribute(final String name, final FastVector values) {
        super(name, values);
    }

    @Override
    public CloneableAttribute clone() {
        List<Object> curObjects = Collections.list(enumerateValues());
        FastVector values = new FastVector(curObjects.size());
        for (Object o : curObjects) {
            values.addElement(o);
        }
        return new CloneableAttribute(name(), values);
    }
}
