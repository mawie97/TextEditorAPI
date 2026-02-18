package com.example.texteditorapi.editor;

import com.example.texteditorapi.editor.commands.Command;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import com.example.texteditorapi.editor.persistence.DocumentRepository;
import org.springframework.stereotype.Service;

@Service
public final class DocumentService {

    private final Map<UUID, Document> docs = new HashMap<>();
    private final DocumentRepository repo;

    public DocumentService(DocumentRepository repo) {
        this.repo = repo;
    }

    /** Create a new empty document. */
    public UUID create() {
        return create("");
    }

    /** Create a new document with initial text. */
    public UUID create(String initialText) {
        UUID id = UUID.randomUUID();
        Document doc = new Document(id, new TextBuffer(initialText));
        docs.put(id, doc);
        return id;
    }

    /** Get current snapshot of a document (for returning to UI/REST). */
    public TextBuffer.Snapshot get(UUID id) {
        return getDocument(id).snapshot();
    }

    /** Apply one command to a document and return the updated snapshot. */
    public TextBuffer.Snapshot apply(UUID id, Command cmd) {
        if (cmd == null) throw new IllegalArgumentException("cmd cannot be null");
        Document doc = getDocument(id);
        doc.apply(cmd);
        return doc.snapshot();
    }

    /** Optional: remove a document. */
    public boolean delete(UUID id) {
        return docs.remove(id) != null;
    }

    private Document getDocument(UUID id) {
        Document doc = docs.get(id);
        if (doc == null) {
            throw new NoSuchElementException("No document with id: " + id);
        }
        return doc;
    }
}
