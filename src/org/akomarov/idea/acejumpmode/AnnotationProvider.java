package org.akomarov.idea.acejumpmode;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.TextAnnotationGutterProvider;
import com.intellij.openapi.editor.VisualPosition;
import com.intellij.openapi.editor.colors.ColorKey;
import com.intellij.openapi.editor.colors.EditorFontType;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

class AnnotationProvider implements TextAnnotationGutterProvider {
    private final LineFromToStringConverter converter;

    AnnotationProvider(LineFromToStringConverter converter) {
        this.converter = converter;
    }

    @Nullable
    @Override
    public String getLineText(int i, Editor editor) {
        LogicalPosition logicalPosition = new LogicalPosition(i, 0);
        VisualPosition visualPosition = editor.logicalToVisualPosition(logicalPosition);
        return converter.fromLineNo(visualPosition.line);
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
