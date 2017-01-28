package org.akomarov.idea.acejumpmode;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.VisualPosition;
import com.intellij.openapi.editor.actionSystem.TypedAction;
import com.intellij.openapi.editor.actionSystem.TypedActionHandler;
import org.jetbrains.annotations.NotNull;

public class AceJumpLineModeHandler implements TypedActionHandler {
    private final TypedAction myAction;
    private final TypedActionHandler mySavedRawHandler;
    private final LineFromToStringConverter myConverter;

    AceJumpLineModeHandler(TypedAction myAction,
                           TypedActionHandler mySavedRawHandler,
                           LineFromToStringConverter myConverter) {
        this.myAction = myAction;
        this.mySavedRawHandler = mySavedRawHandler;
        this.myConverter = myConverter;
    }

    @Override
    public void execute(@NotNull Editor editor, char c, @NotNull DataContext dataContext) {
        myAction.setupRawHandler(mySavedRawHandler);
        editor.getGutter().closeAllAnnotations();
        Integer lineNo = myConverter.fromStringToLineNo("" + c);
        if (lineNo != null) {
            int column = editor.getCaretModel().getLogicalPosition().column;
            VisualPosition visualPosition = new VisualPosition(lineNo, column);
            LogicalPosition position = editor.visualToLogicalPosition(visualPosition);
            editor.getCaretModel().moveToLogicalPosition(position);
        }
    }
}
