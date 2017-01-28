package org.akomarov.jumpline;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class LineFromToStringConverter {
    private final static StringBuilder ALPHABET_BUILDER = new StringBuilder();
    private final static Map<Character, Integer> SYMBOL_TO_INDEX = new HashMap<>();
    static {
        for (char ch = 'a'; ch <= 'z'; ch++) {
            SYMBOL_TO_INDEX.put(ch, ALPHABET_BUILDER.length());
            ALPHABET_BUILDER.append(ch);
        }
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            SYMBOL_TO_INDEX.put(ch, ALPHABET_BUILDER.length());
            ALPHABET_BUILDER.append(ch);
        }
    }
    private final static String ALPHABET = ALPHABET_BUILDER.toString();

    static String fromNum(int x) {
        if (0 <= x && x < ALPHABET.length()) {
            return "" + ALPHABET.charAt(x);
        } else {
            return "?";
        }
    }

    public String fromLineNo(int i) {
        return fromNum(i - visualLineStart);
    }

    @Nullable
    public static Integer fromString(String s) {
        if (s.length() == 1) {
            return SYMBOL_TO_INDEX.get(s.charAt(0));
        } else {
            return null;
        }
    }

    @Nullable
    public Integer fromStringToLineNo(String s) {
        Integer res = fromString(s);
        if (res == null) {
            return null;
        } else {
            return res + visualLineStart;
        }
    }

    private final int visualLineStart;

    public LineFromToStringConverter(int visualLineStart) {
        this.visualLineStart = visualLineStart;
    }


}
