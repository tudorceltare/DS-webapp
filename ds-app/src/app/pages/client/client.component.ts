import { Component, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {Router} from '@angular/router';
import {SmartDeviceDTO} from '../../dtos/SmartDeviceDTO';
import {PersonDetailsDTO} from '../../dtos/PersonDetailsDTO';
import {PersonService} from '../../services/person.service';
import {HttpErrorResponse} from '@angular/common/http';
import {SmartDeviceService} from '../../services/smart-device.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {
  public person: PersonDetailsDTO | undefined;
  public devices: SmartDeviceDTO[] | undefined;

  constructor(
    private personService: PersonService,
    private deviceService: SmartDeviceService,
    public dialog: MatDialog,
    private router: Router,
  ) { }

  ngOnInit(): void {
    if (sessionStorage == null || sessionStorage.getItem('Role') !== 'Normal') {
      this.router.navigate(['/login']);
    }
    this.getPerson();
    this.getDevices();
  }

  public getPerson(): void {
    const userId: any = sessionStorage.getItem('Id');
    this.personService.getIndividual(userId).subscribe(
      (response: PersonDetailsDTO) => {
        this.person = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getDevices(): void {
    this.deviceService.getSmartDevices().subscribe(
      (response: SmartDeviceDTO[]) => {
        this.devices = response;
        console.log(this.devices);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  returnToLogin(): void {
    sessionStorage.clear();
    this.router.navigate(['/login']);
  }
}
