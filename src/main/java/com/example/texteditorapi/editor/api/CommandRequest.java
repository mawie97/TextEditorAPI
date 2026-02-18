package com.example.texteditorapi.editor.api;

public record CommandRequest(
        String type,
        String text,
        Integer pos
) {}
