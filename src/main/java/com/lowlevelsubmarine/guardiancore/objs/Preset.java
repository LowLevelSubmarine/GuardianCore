package com.lowlevelsubmarine.guardiancore.objs;

import com.lowlevelsubmarine.guardiancore.Caesar;

public class Preset {

    private PresetData data;
    private Caesar caesar;

    public Preset(PresetData data, Caesar caesar) {
        this.data = data;
        this.caesar = caesar;
    }

    public PresetData getData() {
        return this.data;
    }

    public String getSecret(String key) {
        return this.caesar.decrypt(key, this.data.getBytes());
    }
    public void setSecret(String key, String value) {
        this.data.setBytes(this.caesar.encrypt(key, value));
    }

}
