import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DocumentSummary, Document, Command } from './models'

@Injectable({
  providedIn: 'root',
})
export class DocumentService {
    constructor(private http: HttpClient){}

      getAll() {
        return this.http.get<DocumentSummary[]>('http://localhost:8080/api/documents');
      }

      get(id: string) {
        return this.http.get<Document>(`http://localhost:8080/api/documents/${id}`)
      }

      sendCommand(id: string, command: Command) {
        return this.http.post<Document>(`http://localhost:8080/api/documents/${id}/commands`, command)
      }

      create(title: string, text: string) {
        return this.http.post<Document>(`http://localhost:8080/api/documents`, { title, text })
      }



  }
