import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {PersonService} from '../../../services/person.service';
import {PersonDTO} from '../../../dtos/PersonDTO';
import {FormBuilder, Validators} from '@angular/forms';
import {PersonDetailsDTO} from '../../../dtos/PersonDetailsDTO';

@Component({
  selector: 'app-devices',
  templateUrl: './devices.component.html',
  styleUrls: ['./devices.component.css']
})
export class DevicesComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DevicesComponent>,
    private service: PersonService,
    private formBuilder: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: PersonDetailsDTO,
  ) { }

  deviceForm = this.formBuilder.group({
    deviceId: ['', Validators.required],
    deviceAddress: ['', Validators.required],
    avgConsumption: ['', [Validators.required, Validators.pattern('^^[0-9]*\.?[0-9]+$')]],
  });

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
  }

  deleteDevice(element: string): void {
    // delete this.data.smartDeviceName[i];
    // this.data.deviceIds.forEach((value, index) => {
    //   if (value === element) {
    //     this.data.deviceIds.splice(index, 1);
    //     this.data.deviceAddresses.splice(index, 1);
    //     this.data.avgConsumption.splice(index, 1);
    //   }
    // });
    // this.service.updatePerson(this.data).subscribe();
  }

  onAdd(): void{
    // if (this.deviceForm?.valid) {
    //   this.data.smartDeviceName.push(this.deviceForm.get('smartDeviceName')?.value);
    //   this.data.maxConsumption.push(this.deviceForm.get('maxConsumption')?.value);
    //   this.service.updatePerson(this.data).subscribe();
    //   // this.dialogRef.close();
    // }
  }
}
