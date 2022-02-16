package instructions.stores.storedouble;


import instructions.base.NoOperandsInstruction;
import instructions.stores.Store;
import rtda.unshared.Zframe;

public class DSTORE_1 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Store.dstote(frame,1);
    }
}
