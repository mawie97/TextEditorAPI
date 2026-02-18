package com.example.texteditorapi.editor.commands;

import com.example.texteditorapi.editor.TextBuffer;

public final class SetCursorSelectionCommand implements Command {
    private final int pos;

    public SetCursorSelectionCommand(int pos) {
        if (pos < 0) throw new IllegalArgumentException("pos cannot be negative");
        this.pos = pos;
    }

    @Override
    public void apply(TextBuffer buffer) {
        buffer.setCursorSelection(pos);
    }
}