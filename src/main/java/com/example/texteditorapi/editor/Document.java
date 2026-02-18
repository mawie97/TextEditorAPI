package com.example.texteditorapi.editor;

import com.example.texteditorapi.editor.commands.Command;
import java.util.UUID;

public final class Document {

    private final UUID id;
    private TextBuffer buffer;

    public Document(UUID id, TextBuffer buffer) {
        this.id = id;
        this.buffer = buffer;
    }

    public UUID getId() {
        return id;
    }

    public TextBuffer.Snapshot snapshot() {
        return buffer.snapshot();
    }

    public void apply(Command cmd) {
        cmd.apply(buffer);
    }
}

