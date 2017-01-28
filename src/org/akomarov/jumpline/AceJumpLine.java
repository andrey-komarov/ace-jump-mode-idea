package org.akomarov.jumpline;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class AceJumpLine extends EditorAction {
    public AceJumpLine() {
        this(new Handler());
    }

    protected AceJumpLine(EditorActionHandler handler) {
        super(handler);
    }

    private static class Handler extends EditorActionHandler {
        @Override
        protected void doExecute(Editor editor, @Nullable Caret caret, DataContext dataContext) {
            System.err.println("lol");
            VisualPosition pos = editor.getCaretModel().getVisualPosition();
            VisualPosition dest = new VisualPosition(pos.getLine() - 1, pos.getColumn());
            int offset = editor.logicalPositionToOffset(editor.visualToLogicalPosition(dest));
            editor.getCaretModel().moveToOffset(offset);
            super.doExecute(editor, caret, dataContext);

            CaretModel caretModel = editor.getCaretModel();
            VisualPosition visualPosition = caretModel.getVisualPosition();
            ScrollingModel scrollingModel = editor.getScrollingModel();
            int lineFrom = scrollingModel.getVerticalScrollOffset() / editor.getLineHeight();
            LogicalPosition logicalPosition = editor.visualToLogicalPosition(visualPosition);
            System.err.println("visual line: " + lineFrom);
            System.err.println("logical line: " + logicalPosition.line);

            Rectangle rectangle = scrollingModel.getVisibleArea();

            LogicalPosition lu = editor.xyToLogicalPosition(new Point(rectangle.width, rectangle.height));
            int screenHeight = lu.line;
            System.err.println("first line: " + lu.line);


            EditorGutter gutter = editor.getGutter();
            gutter.closeAllAnnotations();
            gutter.registerTextAnnotation(new AnnotationProvider(lineFrom));
        }
    }

}
