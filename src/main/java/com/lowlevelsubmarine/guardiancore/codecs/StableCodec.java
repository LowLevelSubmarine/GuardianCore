package com.lowlevelsubmarine.guardiancore.codecs;

public enum StableCodec implements Codec {

    PWC255(new PwC255()), SECRETFERRY(new SecretFerry());

    private Codec codec;

    StableCodec(Codec codec) {
        this.codec = codec;
    }

    @Override
    public byte[] toBytes(String string, int length) {
        return this.codec.toBytes(string, length);
    }

    @Override
    public String toString(byte[] bytes) {
        return this.codec.toString(bytes);
    }

}
