import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Observable} from 'rxjs';
import {SmartDeviceDTO} from '../dtos/SmartDeviceDTO';
import {SmartDeviceDetailsDTO} from '../dtos/SmartDeviceDetailsDTO';

@Injectable({
  providedIn: 'root'
})
export class SmartDeviceService {
  private apiServerURL = environment.apiBaseUrl + 'smart-device';
  constructor(private http: HttpClient) { }

  public getSmartDevices(): Observable<SmartDeviceDTO[]> {
    return this.http.get<SmartDeviceDTO[]>(`${this.apiServerURL}`);
  }

  public getIndividual(id: string): Observable<SmartDeviceDetailsDTO> {
    return this.http.get<SmartDeviceDetailsDTO>(`${this.apiServerURL}/${id}`);
  }

  public addDevice(device: SmartDeviceDTO): Observable<SmartDeviceDTO> {
    return this.http.post<SmartDeviceDTO>(`${this.apiServerURL}`, device);
  }

  public updateDevice(device: SmartDeviceDTO): Observable<SmartDeviceDTO> {
    return this.http.post<SmartDeviceDTO>(`${this.apiServerURL}/update`, device);
  }

  public deleteDevice(device: SmartDeviceDTO): Observable<SmartDeviceDTO> {
    return this.http.delete<SmartDeviceDTO>(`${this.apiServerURL}/${device.id}`);
  }
}
