import React, { Component ,useState,useEffect}  from 'react';
import axios from 'axios';
import $ from 'jquery';
import './Login.css';
import { withRouter,useHistory } from 'react-router-dom';

import 'foundation-sites/dist/css/foundation.min.css';
import { Button, Colors, Grid, Cell } from 'react-foundation';

import './global';

class Login extends React.Component {
  constructor(props) {
    super(props);

    // this.state = {
    //     token: null
    // };
}


  handleSubmit() {
    var x = document.forms["login"]["userid"].value;
    var y = document.forms["login"]["pswrd"].value;
    if (x == ""|| y== "") {
      alert("Username and password must be filled out");
      return false;
    }
    var bodyFormData = new FormData();
    bodyFormData.append('username', $('#username').val());
    bodyFormData.append('password', $('#password').val());
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      body: new URLSearchParams(bodyFormData)
    }
     fetch('//localhost:8765/evcharge/api/login', requestOptions)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        localStorage.setItem('token', data.token);
        localStorage.setItem('username', x );
        localStorage.setItem("logged", true);
        window.location = "/localhost:3000/stations"
      })
      .catch(error => {
        console.error(error);
      })
  }

  render() {
    return (
      <html>
        <body className="login-body">
          <meta charSet="UTF-8" />
          <title>Login</title>

          <Grid className="body-login dispay">
          <Cell large={ 10 } medium={ 10 }>
          <div className="header-login">
          <a href="/">
                <div>Vehicle<span>Charging</span></div>
              </a>
          </div>
          <form name="login" action="/action_page.php" classNameonsubmit="return validateForm()" method="post">
            <div className="login">
              <input id="username" type="text" placeholder="Username" name="userid" required/>
              <input id="password" type="password" placeholder="Password" name="pswrd" required/>
              <input type="button" onClick={this.handleSubmit.bind(this)} value="Login"/>
            </div>  
          </form>
          </Cell>
          </Grid>
        </body>
      </html>
    )
  }
}

export default Login;
