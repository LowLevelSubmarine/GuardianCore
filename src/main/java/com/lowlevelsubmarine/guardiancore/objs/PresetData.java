package com.lowlevelsubmarine.guardiancore.objs;

import java.util.HashMap;

public class PresetData {

    private String name;
    private String codec;
    private byte[] bytes;
    private HashMap<String, String> infos;

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

    public HashMap<String, String> getInfos() {
        return this.infos;
    }

}