import { Component, OnInit, signal, ViewChild, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DocumentService } from '../documentService';
import { Document, Command, CommandType } from '../models'

@Component({
  selector: 'app-document-editor',
  imports: [],
  templateUrl: './document-editor.html',
  styleUrl: './document-editor.css',
})
export class DocumentEditor implements OnInit{
  constructor(private documentService: DocumentService, private route: ActivatedRoute){}

  @ViewChild('editor') editor!: ElementRef<HTMLTextAreaElement>;
  document = signal<Document | null>(null)

  ngOnInit() {
    this.documentService.get(this.route.snapshot.paramMap.get('id')!).subscribe(data => {
      this.document.set(data)
    });
  }

  onKeyDown(event: KeyboardEvent) {
    event.preventDefault();

    let command: Command;

    if (event.key === 'Backspace') {
      command = { type: 'DELETE_LEFT' };
    } else if (event.key === 'Delete') {
      command = { type: 'DELETE_RIGHT' };
    } else if (event.key === 'ArrowLeft' && event.shiftKey) {
      command = { type: 'MOVE_LEFT_SELECTION' };
    } else if (event.key === 'ArrowLeft') {
      command = { type: 'MOVE_LEFT' };
    } else if (event.key === 'ArrowRight' && event.shiftKey) {
      command = { type: 'MOVE_RIGHT_SELECTION' };
    } else if (event.key === 'ArrowRight') {
      command = { type: 'MOVE_RIGHT' };
    } else if (event.key === 'ArrowUp' && event.shiftKey) {
      command = { type: 'MOVE_UP_SELECTION' };
    } else if (event.key === 'ArrowUp') {
      command = { type: 'MOVE_UP' };
    } else if (event.key === 'ArrowDown' && event.shiftKey) {
      command = { type: 'MOVE_DOWN_SELECTION' };
    } else if (event.key === 'ArrowDown') {
      command = { type: 'MOVE_DOWN' };
    } else if (event.key === 'Home' && event.shiftKey) {
      command = { type: 'MOVE_LINE_START_SELECTION' };
    } else if (event.key === 'Home') {
      command = { type: 'MOVE_LINE_START' };
    } else if (event.key === 'End' && event.shiftKey) {
      command = { type: 'MOVE_LINE_END_SELECTION' };
    } else if (event.key === 'End') {
      command = { type: 'MOVE_LINE_END' };
    } else if (event.key === 'Enter') {
      command = { type: 'INSERT', text: '\n'};
    } else if (event.key.length === 1) {
      command = { type: 'INSERT', text: event.key };
    } else {
      return;
    }

    this.documentService.sendCommand(this.route.snapshot.paramMap.get('id')!, command).subscribe(data => {
      this.document.set(data);
      setTimeout(() => {
        this.editor.nativeElement.selectionStart = data.cursor;
        this.editor.nativeElement.selectionEnd = data.anchor;
      })
    });
  }
}
