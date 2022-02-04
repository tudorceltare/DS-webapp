import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {PersonService} from '../../../services/person.service';
import {FormBuilder, Validators} from '@angular/forms';
import {PersonDTO} from '../../../dtos/PersonDTO';
import {validate} from 'codelyzer/walkerFactory/walkerFn';
import {log} from 'util';

@Component({
  selector: 'app-addperson',
  templateUrl: './addperson.component.html',
  styleUrls: ['./addperson.component.css']
})
export class AddpersonComponent implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<AddpersonComponent>,
    private service: PersonService,
    private formBuilder: FormBuilder,
  ) { }

  personForm = this.formBuilder.group({
    name: ['', Validators.required],
    username: ['', Validators.required],
    age: ['', Validators.required],
    address: ['', Validators.required],
    email: ['', Validators.required],
    role: ['', [Validators.required, Validators.pattern('^Admin$|^Normal$')]],
    password: ['', Validators.required],
    confirmPassword: ['', Validators.required],
  });

  onNoClick(): void {
    this.dialogRef.close();
  }
  onAdd(): void {
    console.log('Validity of the file ' + this.personForm?.valid);
    console.log(this.personForm.get('role')?.value);
    if (this.personForm?.valid) {
      const newPerson = ({
        id: '',
        name: this.personForm.get('name')?.value,
        username: this.personForm.get('username')?.value,
        address: this.personForm.get('address')?.value,
        // age can't be less than 18!!!
        age: this.personForm.get('age')?.value,
        email: this.personForm.get('email')?.value,
        role: this.personForm.get('role')?.value,
        password: this.personForm.get('password')?.value,
        smartDeviceName: [],
        maxConsumption: []
      }) as PersonDTO;
      this.service.addPerson(newPerson).subscribe();
      this.dialogRef.close();
      // temporary reload page. To be removed and refresh page in in the subscribe part
      // window.location.reload();

    }
  }
  ngOnInit(): void {
  }

}
