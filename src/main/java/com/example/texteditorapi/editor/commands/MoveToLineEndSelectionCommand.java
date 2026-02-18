package com.example.texteditorapi.editor.commands;

import com.example.texteditorapi.editor.TextBuffer;

public final class MoveToLineEndSelectionCommand implements Command {
    @Override public void apply(TextBuffer buffer) { buffer.moveToLineEndSelection(); }
}