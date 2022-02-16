package instructions.base;


import rtda.heap.method_area.Zclass;
import rtda.heap.method_area.Zmethod;
import rtda.unshared.Zframe;
import rtda.unshared.Zthread;

/**

 * desc:执行类的初始化
 */
public class ClassInitLogic {
    public static void initClass(Zthread thread, Zclass clazz) {
        clazz.startInit();
        scheduleClinit(thread, clazz);
        initSuperClass(thread, clazz);
    }

    private static void scheduleClinit(Zthread thread, Zclass clazz) {
        Zmethod clinit = clazz.getMethod("<clinit>", "()V");
        if (clinit != null && clinit.getClazz() == clazz) {
            // exec <clinit>
            Zframe newFrame = thread.createFrame(clinit);
            thread.pushFrame(newFrame);
        }
    }

    private static void initSuperClass(Zthread thread, Zclass clazz) {
        if (!clazz.isInterface()) {
            Zclass superClass = clazz.getSuperClass();
            if (superClass != null && !superClass.isInitStarted()) {
                initClass(thread, superClass);
            }
        }
    }
}


