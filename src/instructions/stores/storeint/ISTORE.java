package instructions.stores.storeint;


import instructions.base.Index8Instruction;
import instructions.stores.Store;
import rtda.unshared.Zframe;

public class ISTORE extends Index8Instruction {
    @Override
    public void execute(Zframe frame) {
        Store.istore(frame,index);
    }
}
