package instructions.stores.storeref;


import instructions.base.NoOperandsInstruction;
import instructions.stores.Store;
import rtda.unshared.Zframe;

public class ASTORE_1 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Store.astore(frame,1);
    }
}