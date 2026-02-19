package com.example.texteditorapi.editor;

import com.example.texteditorapi.editor.commands.Command;
import com.example.texteditorapi.editor.persistence.DocumentEntity;
import com.example.texteditorapi.editor.persistence.DocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class DocumentService {

    private final DocumentRepository repo;

    public DocumentService(DocumentRepository repo) {
        this.repo = repo;
    }

    /** Create a new empty document. */
    @Transactional
    public UUID create() {
        return create("");
    }

    /** Create a new document with initial text. */
    @Transactional
    public UUID create(String initialText) {
        UUID id = UUID.randomUUID();

        TextBuffer buffer = new TextBuffer(initialText);
        TextBuffer.Snapshot snap = buffer.snapshot();

        DocumentEntity entity = new DocumentEntity(
                id,
                snap.text,
                snap.cursor,
                snap.anchor,
                snap.preferredColumn
        );

        repo.save(entity);
        return id;
    }

    /** Get current snapshot of a document (for returning to UI/REST). */
    @Transactional(readOnly = true)
    public TextBuffer.Snapshot get(UUID id) {
        DocumentEntity entity = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No document with id: " + id));

        return new TextBuffer.Snapshot(
                entity.getText(),
                entity.getCursor(),
                entity.getAnchor(),
                entity.getPreferredColumn()
        );
    }

    /** Apply one command to a document and return the updated snapshot. */
    @Transactional
    public TextBuffer.Snapshot apply(UUID id, Command cmd) {
        if (cmd == null) throw new IllegalArgumentException("cmd cannot be null");

        DocumentEntity entity = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No document with id: " + id));

        // Rebuild buffer from persisted snapshot (Option A: undo/redo not persisted)
        TextBuffer buffer = TextBuffer.fromSnapshot(new TextBuffer.Snapshot(
                entity.getText(),
                entity.getCursor(),
                entity.getAnchor(),
                entity.getPreferredColumn()
        ));

        // Apply command
        cmd.apply(buffer);

        // Persist updated snapshot
        TextBuffer.Snapshot updated = buffer.snapshot();
        entity.setText(updated.text);
        entity.setCursor(updated.cursor);
        entity.setAnchor(updated.anchor);
        entity.setPreferredColumn(updated.preferredColumn);

        repo.save(entity);

        return updated;
    }

    /** Optional: remove a document. */
    @Transactional
    public boolean delete(UUID id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
