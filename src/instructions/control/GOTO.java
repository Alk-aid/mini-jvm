package instructions.control;


import instructions.base.BranchInstruction;
import instructions.base.BranchLogic;
import rtda.unshared.Zframe;

public class GOTO extends BranchInstruction {
    @Override
    public void execute(Zframe frame) {
        BranchLogic.branch(frame,offset);
    }
}
