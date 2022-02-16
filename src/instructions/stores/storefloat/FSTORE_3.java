package instructions.stores.storefloat;


import instructions.base.NoOperandsInstruction;
import instructions.stores.Store;
import rtda.unshared.Zframe;

public class FSTORE_3 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Store.fstore(frame,3);
    }
}
