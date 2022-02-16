package instructions.loads.loadref;


import instructions.base.Index8Instruction;
import instructions.loads.Load;
import rtda.unshared.Zframe;

public class ALOAD extends Index8Instruction {
    @Override
    public void execute(Zframe frame) {
        Load.aload(frame,index);
    }
}
