package rtda.heap.symRef;


import rtda.heap.method_area.Zclass;
import rtda.heap.method_area.Zmethod;

public class MethodLookup {
    public static Zmethod lookupMethodInClass(Zclass clazz, String name, String descriptor) {
        Zclass c = clazz;
        while (c != null) {
            for (Zmethod method : c.methods) {
                if (method.name.equals(name) && method.descriptor.equals(descriptor)) {
                    return method;
                }
            }
            c = c.superClass;
        }
        return null;
    }

    public static Zmethod lookupMethodInInterfaces(Zclass[] ifaces, String name, String descriptor) {
        for (Zclass iface : ifaces) {
            for (Zmethod method : iface.methods) {
                if (method.name.equals(name) && method.descriptor.equals(descriptor)) {
                    return method;
                }
            }
            Zmethod method = lookupMethodInInterfaces(iface.interfaces, name, descriptor);
            if (method != null) {
                return method;
            }
        }
        return null;
    }
}
