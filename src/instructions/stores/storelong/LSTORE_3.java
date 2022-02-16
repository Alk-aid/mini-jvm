package instructions.stores.storelong;

import instructions.base.NoOperandsInstruction;
import instructions.stores.Store;
import rtda.unshared.Zframe;

public class LSTORE_3 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Store.lstore(frame,3);
    }
}
