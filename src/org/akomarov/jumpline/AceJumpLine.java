package org.akomarov.jumpline;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.VisualPosition;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import org.jetbrains.annotations.Nullable;

public class AceJumpLine extends EditorAction {
    public AceJumpLine() {
        this(new Handler());
    }

    protected AceJumpLine(EditorActionHandler handler) {
        super(handler);
    }

    static class Handler extends EditorActionHandler {
        @Override
        protected void doExecute(Editor editor, @Nullable Caret caret, DataContext dataContext) {
            System.err.println("lol");
            VisualPosition pos = editor.getCaretModel().getVisualPosition();
            VisualPosition dest = new VisualPosition(pos.getLine() - 1, pos.getColumn());
            int offset = editor.logicalPositionToOffset(editor.visualToLogicalPosition(dest));
            editor.getCaretModel().moveToOffset(offset);
            super.doExecute(editor, caret, dataContext);
        }
    }

}
