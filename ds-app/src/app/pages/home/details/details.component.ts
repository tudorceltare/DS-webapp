import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {PersonDetailsDTO} from '../../../dtos/PersonDetailsDTO';
import {PersonDTO} from '../../../dtos/PersonDTO';
import {PersonService} from '../../../services/person.service';
import {FormBuilder, Validators} from '@angular/forms';

// for updating
@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DetailsComponent>,
    private service: PersonService,
    private formBuilder: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: PersonDTO,
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

  onUpdate(): void {
    if (this.personForm?.valid) {
      const newPerson = ({
        id: this.data.id,
        name: this.personForm.get('name')?.value,
        username: this.personForm.get('username')?.value,
        address: this.personForm.get('address')?.value,
        // age can't be less than 18!!!
        age: this.personForm.get('age')?.value,
        email: this.personForm.get('email')?.value,
        role: this.personForm.get('role')?.value,
        password: this.personForm.get('password')?.value,
      }) as PersonDTO;
      this.service.updatePerson(newPerson).subscribe();
      this.dialogRef.close();
      // temporary reload page. To be removed and refresh page in in the subscribe part
      // window.location.reload();
    }
  }

  ngOnInit(): void { }

}
