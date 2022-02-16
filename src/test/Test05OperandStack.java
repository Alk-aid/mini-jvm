package test;


import rtda.unshared.OperandStack;

/**
 * @Author: Alk-aid
 * @Date: 1/31/2022 19:46
 * @Description:
 */
public class Test05OperandStack {
    public static void main(String[] args) {
        OperandStack stack = new OperandStack(10);
        stack.pushInt(100);
        stack.pushInt(-100);
        stack.pushLong(2997934580L);
        stack.pushLong(-2997934580L);
        stack.pushFloat(3.1415925f);
        stack.pushDouble(2.141592678912);

        System.out.println(stack.popDouble());
        System.out.println(stack.popFloat());
        System.out.println(stack.popLong());
        System.out.println(stack.popLong());
        System.out.println(stack.popInt());
        System.out.println(stack.popInt());
    }
}
