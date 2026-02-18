package com.example.texteditorapi.editor.commands;

import com.example.texteditorapi.editor.TextBuffer;

public final class DeleteLeftCommand implements Command {
    @Override public void apply(TextBuffer buffer) { buffer.deleteLeft(); }
}
