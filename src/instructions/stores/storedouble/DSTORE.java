package instructions.stores.storedouble;


import instructions.base.Index8Instruction;
import instructions.stores.Store;
import rtda.unshared.Zframe;

public class DSTORE extends Index8Instruction {
    @Override
    public void execute(Zframe frame) {
        Store.dstote(frame,index);
    }
}
