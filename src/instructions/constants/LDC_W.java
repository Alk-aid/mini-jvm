package instructions.constants;


import instructions.base.BytecodeReader;

public class LDC_W extends LDC {
    @Override
    public void fetchOperands(BytecodeReader reader) {
        index = reader.readUint16();
    }
}
