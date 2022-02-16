package instructions.stores.storelong;


import instructions.base.Index8Instruction;
import instructions.stores.Store;
import rtda.unshared.Zframe;

public class LSTORE extends Index8Instruction {
    @Override
    public void execute(Zframe frame) {
        Store.lstore(frame,index);
    }
}
