import { Component, OnInit } from '@angular/core';
import {PersonService} from '../../services/person.service';
import {PersonDetailsDTO} from '../../dtos/PersonDetailsDTO';
import {PersonDTO} from '../../dtos/PersonDTO';
import {HttpErrorResponse} from '@angular/common/http';
import {MatDialog} from '@angular/material/dialog';
import {DetailsComponent} from './details/details.component';
import {AddpersonComponent} from './addperson/addperson.component';
import {error} from 'protractor';
import {DevicesComponent} from './devices/devices.component';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public persons: PersonDTO[] | undefined;

  constructor(
    private personService: PersonService,
    public dialog: MatDialog,
    private router: Router,
  ) { }

  ngOnInit(): void {
    if (sessionStorage == null || sessionStorage.getItem('Role') !== 'Admin') {
      this.router.navigate(['/login']);
    }
    this.getPersons();
  }

  public getPersons(): void {
    this.personService.getPersons().subscribe(
      (response: PersonDTO[]) => {
        this.persons = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getDetails(person: PersonDTO): void {
    const dialogRef = this.dialog.open(DetailsComponent, {
      data: person,
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Details Dialog closed.');
      this.getPersons();
    });
  }

  public addPerson(): void {
    const dialogRef = this.dialog.open(AddpersonComponent);
    dialogRef.afterClosed().subscribe(() => {
      console.log('Dialog closed.');
      this.getPersons();
    });

    // dialogRef.afterClosed().subscribe(result => {
    //   this.newPerson = result;
    // });

  }

  public deviceDetails(person: PersonDTO): void {
    const dialogRef = this.dialog.open(DevicesComponent, {
      data: this.personService.getIndividual(person.id),
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('Devices Dialog closed.');
    });
  }

  public deletePerson(person: PersonDTO): void {
    this.personService.deletePerson(person).subscribe(() => {
      this.getPersons();
      // window.location.reload();
    });
  }

  returnToLogin(): void {
    sessionStorage.clear();
    this.router.navigate(['/login']);
  }
}
