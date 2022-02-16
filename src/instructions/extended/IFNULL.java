package instructions.extended;

import instructions.base.BranchInstruction;
import instructions.base.BranchLogic;

import rtda.heap.Zobject;
import rtda.unshared.Zframe;

public class IFNULL extends BranchInstruction {
    @Override
    public void execute(Zframe frame) {
        Zobject ref = frame.getOperandStack().popRef();
        if (ref == null) {
            BranchLogic.branch(frame, offset);
        }
    }
}
