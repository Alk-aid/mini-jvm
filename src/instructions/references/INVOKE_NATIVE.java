package instructions.references;


import instructions.base.NoOperandsInstruction;
import rtda.heap.method_area.Zmethod;
import rtda.unshared.Zframe;
import znative.NativeMethod;
import znative.RegisterCenter;

/**

 * desc:nativie 方法执行的指令
 */
public class INVOKE_NATIVE extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Zmethod method = frame.getMethod();
        String clazzName = method.getClazz().thisClassName;
        String methodName = method.getName();
        String descriptor = method.getDescriptor();

        NativeMethod nativeMethod = RegisterCenter.findNativeMethod(clazzName, methodName, descriptor);
        if (nativeMethod == null) {
            String methodInfo = clazzName + "." + methodName + descriptor;
            throw new UnsatisfiedLinkError(methodInfo);
        }

        nativeMethod.run(frame);
    }
}
