import { Component, OnInit, signal } from '@angular/core';
import { DocumentService } from '../documentService';
import { DocumentSummary } from '../models';
import { Router, RouterLink } from '@angular/router';


@Component({
  selector: 'app-document-list',
  imports: [RouterLink],
  templateUrl: './document-list.html',
  styleUrl: './document-list.css',
})

export class DocumentList implements OnInit{
  constructor(private documentService: DocumentService, private router: Router){}

  documents = signal<DocumentSummary[]>([])

  ngOnInit() {
    this.documentService.getAll().subscribe(data => {
        this.documents.set(data);
    });
  }

  createDocument(title: string, text: string){
    this.documentService.create(title, text).subscribe(data => {
      this.router.navigate(['/documents', data.id])})
    }



}
