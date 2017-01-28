package org.akomarov.jumpline;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.TextAnnotationGutterProvider;
import com.intellij.openapi.editor.colors.ColorKey;
import com.intellij.openapi.editor.colors.EditorFontType;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class AnnotationProvider implements TextAnnotationGutterProvider {
    private final static StringBuilder ALPHABET_BUILDER = new StringBuilder();
    static {
        for (char ch = 'a'; ch <= 'z'; ch++) {
            ALPHABET_BUILDER.append(ch);
        }
        for (char ch = 'A'; ch <= 'Z'; ch++) {
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

    private final int visualLineStart;

    public AnnotationProvider(int visualLineStart) {
        this.visualLineStart = visualLineStart;
    }

    @Nullable
    @Override
    public String getLineText(int i, Editor editor) {
        int x = i - visualLineStart;
        return fromNum(x);
    }

    @Nullable
    @Override
    public String getToolTip(int i, Editor editor) {
        return null;
    }

    @Override
    public EditorFontType getStyle(int i, Editor editor) {
        return null;
    }

    @Nullable
    @Override
    public ColorKey getColor(int i, Editor editor) {
        return null;
    }

    @Nullable
    @Override
    public Color getBgColor(int i, Editor editor) {
        return null;
    }

    @Override
    public List<AnAction> getPopupActions(int i, Editor editor) {
        return null;
    }

    @Override
    public void gutterClosed() {

    }
}
