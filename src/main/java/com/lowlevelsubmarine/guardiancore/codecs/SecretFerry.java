package com.lowlevelsubmarine.guardiancore.codecs;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;

public class SecretFerry implements Codec {

    private static final String ALLOWED_CHARS_STRING = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜabcdefghijklmnopqrstuvwxyzäöü !\"#$%&'()*+,-./:;<=>?@[\\]^{}";
    private static final LinkedList<Character> ALLOWED_CHARS = generateAllowedChars();
    private static final byte[] NULL_DOUBLEBYTE = new byte[] {-128, -128};

    @Override
    public String name() {
        return "SecretFerry";
    }

    @Override
    public byte[] toBytes(String decoded, int length) {
        byte[] encoded = new byte[length];
        for (int i = 0; i < length / 2; i++) {
            byte[] doublebyte;
            if (i >= length) {
                doublebyte = NULL_DOUBLEBYTE;
            } else {
                doublebyte = charToDoublebyte(decoded.charAt(i));
            }
            encoded[i * 2] = doublebyte[0];
            encoded[i * 2 + 1] = doublebyte[1];
        }
        return encoded;
    }

    public String toString(byte[] bytes) {
        String string = "";
        if (bytes.length % 2 != 0) {
            bytes = Arrays.copyOf(bytes, bytes.length - 1);
        }
        for (int i = 0; i < bytes.length / 2; i++) {
            byte[] doublebyte = new byte[2];
            doublebyte[0] = bytes[i * 2];
            doublebyte[1] = bytes[i * 2 + 1];
            if (!Arrays.equals(doublebyte, NULL_DOUBLEBYTE)) {
                Character ch = doublebyteToChar(doublebyte);
                if (ch != null) {
                    string += ch;
                }
            }
        }
        return string;
    }

    private static byte[] charToDoublebyte(char ch) {
        byte[] doublebyte = new byte[2];
        byte[] charBytes = charBytes(ch);
        if (charBytes.length < 2) {
            charBytes = new byte[] {-128, charBytes[0]};
        } else if (charBytes.length > 2) {
            charBytes = Arrays.copyOf(charBytes, 2);
        }
        doublebyte[0] = charBytes[0];
        doublebyte[1] = charBytes[1];
        return doublebyte;
    }

    private static byte[] charBytes(char ch) {
        return new Character(ch).toString().getBytes(StandardCharsets.UTF_8);
    }

    private static Character doublebyteToChar(byte[] doublebyte) {
        char ch;
        if (doublebyte[0] == -128) {
            ch = (char) doublebyte[1];
        } else {
            ch = byteChar(doublebyte);
        }
        return safeChar(ch);
    }

    private static char byteChar(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8).charAt(0);
    }

    private static Character safeChar(char ch) {
        if (!ALLOWED_CHARS.contains(ch)) {
            byte[] doublebytes = charToDoublebyte(ch);
            if (Arrays.equals(doublebytes, new byte[] {-17, -65})) {
                return null;
            }
            int hash = doublebytes[0] + 129 * doublebytes[1] + 129 * 8699;
            int index = hash % ALLOWED_CHARS_STRING.length();
            return ALLOWED_CHARS_STRING.charAt(index);
        } else {
            return ch;
        }
    }

    private static LinkedList<Character> generateAllowedChars() {
        LinkedList<Character> chars = new LinkedList<>();
        for (char ch : ALLOWED_CHARS_STRING.toCharArray()) {
            chars.add(ch);
        }
        return chars;
    }
}
