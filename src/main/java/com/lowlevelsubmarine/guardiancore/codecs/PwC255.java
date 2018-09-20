package com.lowlevelsubmarine.guardiancore.codecs;

public class PwC255 implements Codec {

    /*
    Codec definition:
    All the convertable characters should be all the common password chars.
    All different byte positions represent eather one char from the charset or a null character.
    With a byte size of 256 the charsets maximum size must be 255 to keep the null character.
    The null character is for convinience 127 (byte has a value from -128 to 127).
     */

    private static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜabcdefghijklmnopqrstuvwxyzäöü \"#$%&'()*+,-./:;<=>?@[\\]^{}";
    private static final byte NULL_BYTE = 127;

    @Override
    public String name() {
        return "PwC255";
    }

    @Override
    public String toString(byte[] encoded) {
        String string = "";
        for (byte byt : encoded) {
            Character chr = getChar(byt);
            if (chr != null) {
                string += chr;
            }
        }
        return string;
    }

    @Override
    public byte[] toBytes(String string, int length) {
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            if (i < string.length()) {
                bytes[i] = getByte(string.charAt(i));
            } else {
                bytes[i] = NULL_BYTE;
            }
        }
        return bytes;
    }

    private byte getByte(char chr) {
        int charIndex = CHARSET.indexOf(chr);
        if (charIndex == -1) {
            return NULL_BYTE;
        } else {
            //Translate the chars index 128 steps back to fit the byte
            return (byte) (charIndex-128);
        }
    }

    private Character getChar(byte byt) {
        //Translate the byte 128 steps up so the final integer fits the CHARSET size
        int charIndex = byt + 128;
        if (charIndex < CHARSET.length()) {
            return CHARSET.charAt(charIndex);
        } else {
            return null;
        }
    }

}
