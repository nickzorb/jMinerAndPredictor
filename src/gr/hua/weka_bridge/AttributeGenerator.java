package gr.hua.weka_bridge;

public interface AttributeGenerator {

    public CloneableAttribute getAttribute();
    public CloneableAttribute getForcedNominalAttribute();
}
