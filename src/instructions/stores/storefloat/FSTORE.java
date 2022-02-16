package instructions.stores.storefloat;


import instructions.base.Index8Instruction;
import instructions.stores.Store;
import rtda.unshared.Zframe;

public class FSTORE extends Index8Instruction {
    @Override
    public void execute(Zframe frame) {
        Store.fstore(frame,index);
    }
}
