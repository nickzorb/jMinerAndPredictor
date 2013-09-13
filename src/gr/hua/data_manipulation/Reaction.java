package gr.hua.data_manipulation;

import java.io.Serializable;
import java.util.LinkedList;

public class Reaction extends Action implements Cloneable, Serializable {
    
    //column reactions (superset of column actions):
    public static final String CDV = "Decrease value";
    public static final String CIV = "Increase value";
    public static final String CUV = "Update value";
    public static final String[] column_reactions = {CR, CCN, CD, CCT, CFNRV, CCAV, CTR, CARX, CDV, CIV, CUV};
    //row reactions (superset of row actions):
    public static final String RRC = "Remove column";
    public static final String RDC = "Duplicate column";
    public static final String RRV = "Replace values";
    public static final String[] row_reactions = {RR, RD, RFNRV, RARX, RRC, RDC, RRV};
    //global reactions (superset of global actions):
    public static final String[] global_reactions = {GREC, GRER, GFNRV, GARX};

    public Reaction(String mode, LinkedList<String> flags, Object target) {
        super(mode, flags, target);
    }

    private Reaction(Reaction c) {
        super(c);
    }
    
    @Override
    public Action clone() {
        return new Reaction(this);
    }
}
