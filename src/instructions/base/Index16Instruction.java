package instructions.base;

/**
 * Desc:有一些指令需要访问运行时常量池，常量池索引由两字节操作数给出。
 */
public abstract class Index16Instruction implements Instruction {
    public int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        index = reader.readUint16();
    }
}
