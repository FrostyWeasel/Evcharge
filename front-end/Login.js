import React from 'react';
import axios from 'axios';
import $ from 'jquery';
import './Login.css';

import 'foundation-sites/dist/css/foundation.min.css';
import { Button, Colors, Grid, Cell } from 'react-foundation';

class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = { user: props.user };
  }

  handleSubmit() {
    // var params = 
    var x = document.forms["login"]["userid"].value;
    var y = document.forms["login"]["pswrd"].value;
    if (x == ""|| y== "") {
      alert("Username and password must be filled out");
      return false;
    }

    axios.post('//localhost:8765/evcharge/api/login',{
      username: $('#username').val(),
      password: $('#password').val()
    },  { headers: {"Access-Control-Allow-Origin": "*"} })
      .then(res => {
        console.log(res)
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
              <input type="button" onClick={this.handleSubmit} value="Login"/>
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
