package instructions.stores.storeint;


import instructions.base.NoOperandsInstruction;
import instructions.stores.Store;
import rtda.unshared.Zframe;

public class ISTORE_1 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Store.istore(frame,1);
    }
}
