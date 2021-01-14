import React from 'react';
import axios from 'axios';
import $ from 'jquery';
import './Login.css';

class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = { user: props.user };
  }

  handleSubmit() {
    var params = {
      username: $('#username').val(),
      pswd: $('#password').val()
    }

    axios.post('/evcharge/api/login' + params.username + '/' + params.pswd)
      .then(res => {
        console.log(res)
      })
  }

  render() {
    return (
      <html>
        <body>
          <meta charSet="UTF-8" />
          <title>Login</title>

          <div className="body"></div>
          <div className="grad"></div>
          <div className="header">
            <div>Vehicle<span>Charging</span></div>
          </div><br />
          <form name="login">
            <div className="login">
              <input id="username" type="text" placeholder="Username" name="userid" required />
              <input id="password" type="password" placeholder="Password" name="pswrd" required />
              <input type="button" onClick={this.handleSubmit} value="Login" />
            </div>
          </form>
        </body>
      </html>
    )
  }
}

export default Login;
