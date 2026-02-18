package com.example.texteditorapi.editor.commands;

import com.example.texteditorapi.editor.TextBuffer;

public interface Command {
    void apply(TextBuffer buffer);
}
