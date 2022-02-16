package classfile;

import java.math.BigInteger;

public class ClassReader {
    private byte[] data;
    private int index = 0;
    public ClassReader(byte[] data) {
        this.data = data;
    }

    public byte[] readBytes(int n) {
        byte[] res = new byte[n];
        for (int i = 0; i < n; i++) {
            res[i] = data[index++];
        }
        return res;
    }
    // u1
    public int readUint8() {
        byte[] val = readBytes(1);
        return new BigInteger(1,val).intValue();
    }
    // u2
    public int readUint16() {
        byte[] val = readBytes(2);
        return new BigInteger(1,val).intValue();
    }
    // u4
    public long readUint32() {
        byte[] val = readBytes(4);
        return new BigInteger(1,val).longValue();
    }


    public int readInteger(){
        byte[] val = readBytes(4);
        return new BigInteger(1, val).intValue();
    }

    public float readFloat() {
        byte[] val = readBytes(4);
        return new BigInteger(1, val).floatValue();
    }

    public long readLong() {
        byte[] val = readBytes(8);
        return new BigInteger(1, val).longValue();
    }

    public double readDouble() {
        byte[] val = readBytes(8);
        return new BigInteger(1, val).doubleValue();
    }

    public int[] readUint16s() {
        int n = readUint16();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = readUint16();
        }
        return data;
    }
}
