import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { of, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LabelService {
  private readonly apiUrl = 'YOUR_LABELS_API_URL';

  constructor(private http: HttpClient) {}

  getLabels(): Observable<string[]> {
    return this.http.get<string[]>('/api/labels');
  }
}