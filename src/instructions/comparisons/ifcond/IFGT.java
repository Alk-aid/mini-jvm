package instructions.comparisons.ifcond;


import instructions.base.BranchInstruction;
import instructions.base.BranchLogic;
import rtda.unshared.Zframe;

public class IFGT extends BranchInstruction {
    @Override
    public void execute(Zframe frame) {
        int val = frame.getOperandStack().popInt();
        if (val > 0) {
            BranchLogic.branch(frame, offset);
        }
    }
}
