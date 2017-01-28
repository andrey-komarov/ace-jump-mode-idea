package org.akomarov.idea.acejumpmode;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.actionSystem.*;
import org.jetbrains.annotations.Nullable;

public class AceJumpLineMode extends EditorAction {
    public AceJumpLineMode() {
        super(new Handler());
    }

    private static class Handler extends EditorActionHandler {
        @Override
        protected void doExecute(Editor editor, @Nullable Caret caret, DataContext dataContext) {
            ScrollingModel scrollingModel = editor.getScrollingModel();
            int lineFrom = scrollingModel.getVerticalScrollOffset() / editor.getLineHeight();
            LineFromToStringConverter converter = new LineFromToStringConverter(lineFrom);

            EditorGutter gutter = editor.getGutter();
            gutter.closeAllAnnotations();
            gutter.registerTextAnnotation(new AnnotationProvider(converter));

            EditorActionManager manager = EditorActionManager.getInstance();
            TypedAction typedAction = manager.getTypedAction();
            TypedActionHandler oldHandler = typedAction.getRawHandler();
            AceJumpLineModeHandler handler = new AceJumpLineModeHandler(typedAction, oldHandler, converter);
            typedAction.setupRawHandler(handler);
        }
    }

}
