package instructions.stores.storeref;


import instructions.base.Index8Instruction;
import instructions.stores.Store;
import rtda.unshared.Zframe;

public class ASTORE extends Index8Instruction {
    @Override
    public void execute(Zframe frame) {
        Store.astore(frame,index);
    }
}
