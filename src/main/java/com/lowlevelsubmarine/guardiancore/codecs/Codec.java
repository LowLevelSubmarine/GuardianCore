package com.lowlevelsubmarine.guardiancore.codecs;

public interface Codec {

    String name();
    byte[] toBytes(String string, int length);
    String toString(byte[] bytes);

}
