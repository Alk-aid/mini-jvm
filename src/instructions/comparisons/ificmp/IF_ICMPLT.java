package instructions.comparisons.ificmp;


import instructions.base.BranchInstruction;
import instructions.base.BranchLogic;
import rtda.unshared.Zframe;

public class IF_ICMPLT extends BranchInstruction {
    @Override
    public void execute(Zframe frame) {
        int[] res = IfIcmp._icmpPop(frame);
        int val1 = res[0];
        int val2 = res[1];
        if (val1 < val2) {
            BranchLogic.branch(frame, offset);
        }

    }
}
