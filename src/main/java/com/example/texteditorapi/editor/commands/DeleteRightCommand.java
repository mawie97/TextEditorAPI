package com.example.texteditorapi.editor.commands;

import com.example.texteditorapi.editor.TextBuffer;

public final class DeleteRightCommand implements Command {
    @Override public void apply(TextBuffer buffer) { buffer.deleteRight(); }
}