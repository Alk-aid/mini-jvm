package instructions.comparisons.ifacmp;


import instructions.base.BranchInstruction;
import instructions.base.BranchLogic;
import rtda.unshared.Zframe;

public class IF_ACMPNE extends BranchInstruction {
    @Override
    public void execute(Zframe frame) {
        if (!IfAcmp._acmp(frame)) {
            BranchLogic.branch(frame, offset);
        }
    }
}
