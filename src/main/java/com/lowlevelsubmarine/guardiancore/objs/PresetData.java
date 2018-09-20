package com.lowlevelsubmarine.guardiancore.objs;

public class PresetData<T> {

    private String name;
    private String codec;
    private byte[] bytes;
    private T info;

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBytes() {
        return this.bytes;
    }
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getCodec() {
        return this.codec;
    }
    public void setCodec(String coded) {
        this.codec = coded;
    }

    public T getInfo() {
        return this.info;
    }
    public void setInfo(T info) {
        this.info = info;
    }

}