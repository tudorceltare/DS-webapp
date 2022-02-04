import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PersonDTO} from '../dtos/PersonDTO';
import {PersonDetailsDTO} from '../dtos/PersonDetailsDTO';
import {environment} from '../../environments/environment';
import {LoginComponent} from '../pages/login/login.component';
import {LoginDTO} from '../dtos/LoginDTO';

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  private apiServerUrl = environment.apiBaseUrl + 'person';

  constructor(private http: HttpClient) { }

  public getPersons(): Observable<PersonDTO[]> {
    return this.http.get<PersonDTO[]>(`${this.apiServerUrl}`);
  }

  public getIndividual(id: string): Observable<PersonDetailsDTO> {
    return this.http.get<PersonDetailsDTO>(`${this.apiServerUrl}/${id}`);
  }

  public addPerson(person: PersonDTO): Observable<PersonDTO> {
    return this.http.post<PersonDTO>(`${this.apiServerUrl}`, person);
  }

  public updatePerson(person: PersonDTO): Observable<PersonDTO> {
    return this.http.post<PersonDTO>(`${this.apiServerUrl}/update`, person);
  }

  public deletePerson(person: PersonDTO): Observable<PersonDTO> {
    return this.http.delete<PersonDTO>(`${this.apiServerUrl}/${person.id}`);
  }

  public loginPerson(personCredentials: LoginDTO): any {
    return this.http.post<string>(`${this.apiServerUrl}/login`, personCredentials);
  }
}
