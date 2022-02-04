import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {PersonService} from '../../services/person.service';
import {LoginDTO} from '../../dtos/LoginDTO';
import {ResponseDTO} from '../../dtos/ResponseDTO';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public loginCredentials: LoginDTO | undefined;
  constructor(
    private personService: PersonService,
    private formBuilder: FormBuilder,
    private router: Router,
  ) { }

  loginForm = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
  });

  ngOnInit(): void {
  }
  onLogin(): void {
    const loginCredentials = ({
      username: this.loginForm.get('username')?.value,
      password: this.loginForm.get('password')?.value
    }) as LoginDTO;
    console.log(loginCredentials);
    this.personService.loginPerson(loginCredentials).subscribe(
      (response: ResponseDTO) => {
        console.log(response);
        this.redirectUser(response, loginCredentials.username);
      },
      (error: any) => {
        console.log(error);
      }
    );
  }

  redirectUser(response: ResponseDTO, username: string): void {
    if (response.response === 'Admin'){
      sessionStorage.setItem('Role', response.response);
      sessionStorage.setItem('Id', response.user_id);
      this.router.navigate(['/home']);
      // sessionStorage.setItem('person', JSON.stringify(response));
    } else if (response.response === 'Normal') {
      sessionStorage.setItem('Role', response.response);
      sessionStorage.setItem('Id', response.user_id);
      this.router.navigate(['/client']);
      console.log('Redirect to user page');
    }
  }
}
