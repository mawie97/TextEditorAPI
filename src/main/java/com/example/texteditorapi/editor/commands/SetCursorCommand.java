package com.example.texteditorapi.editor.commands;

import com.example.texteditorapi.editor.TextBuffer;

public final class SetCursorCommand implements Command {
    private final int pos;

    public SetCursorCommand(int pos) {
        if (pos < 0) throw new IllegalArgumentException("pos cannot be negative");
        this.pos = pos;
    }

    @Override
    public void apply(TextBuffer buffer) {
        buffer.setCursor(pos);
    }
}
