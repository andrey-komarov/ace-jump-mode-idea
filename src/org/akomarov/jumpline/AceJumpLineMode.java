package org.akomarov.jumpline;

import com.intellij.openapi.editor.actionSystem.EditorActionManager;
import com.intellij.openapi.editor.actionSystem.TypedAction;
import org.jetbrains.annotations.NotNull;

public class AceJumpLineMode implements com.intellij.openapi.components.ApplicationComponent {
    private static final String COMPONENT_NAME = "AceJumpLinePlugin";

    @Override
    public void initComponent() {
        EditorActionManager manager = EditorActionManager.getInstance();
        TypedAction typedAction = manager.getTypedAction();
    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return COMPONENT_NAME;
    }
}
