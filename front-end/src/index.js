import React, { useState }  from 'react';
import ReactDOM from 'react-dom';
import $ from 'jquery';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import datepickerFactory from 'jquery-datepicker';
import datepickerJAFactory from 'jquery-datepicker/i18n/jquery.ui.datepicker-ja';
// import Dialog from 'react-bootstrap-dialog'
import { Router, Link, browserHistory } from 'react-router';
import { Route, Redirect, Switch, BrowserRouter } from "react-router-dom";
import './index.css';
import './Login.css';
import Logout from './Logout';

import './global';
import MyVehicles from './MyVehicles';
import AddNewVehicle from './AddNewVehicle';
import Charge from './Charge';

import SessionsPerPoint from './SessionsPerPoint';
import SessionsPerProvider from './SessionsPerProvider';
import SessionsPerVehicle from './SessionsPerVehicle';
import SessionsPerStation from './SessionsPerStation';
import Stations from './Stations';

import 'foundation-sites/dist/css/foundation.min.css';
import { Button, Colors, Grid, Cell } from 'react-foundation';
import { registerLocale, setDefaultLocale } from  "react-datepicker";
import es from 'date-fns/locale/es';
registerLocale('es', es)


const user = localStorage.getItem('user');

class App extends React.Component {
  constructor(props) {
    super(props);
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
        window.location = "/"
      })
      .catch(error => {
        alert('Wrong username or password');
        console.error(error);
      })
  }
  Logout() {
    const requestOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json', 
        'X-OBSERVATORY-AUTH': localStorage.getItem("token")
      },
      body: JSON.stringify({ title: 'Logout' })
    }
     fetch('//localhost:8765/evcharge/api/logout', requestOptions)
      .then(()=> {
        localStorage.setItem('token', null);
        localStorage.setItem('logged', false);
        window.location.reload();
      })
      .catch(error => {
        console.error(error);
      })
  }
  render() {
    if (localStorage.getItem("logged") == "true") {
      return (
        <html>
          <head>
          <link rel="stylesheet" href="/node_modules/foundation-sites/dist/css/foundation.min.css"/>
          </head>
          <body>
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
            <title>Stations</title>
            <div className="body"></div>
            <div className="grad"></div>
            <div className="header">
              <a href="/">
                <div>Electro<span>Wheeler</span></div>
              </a>
            </div>
            <div className="topnav">
              <a href="/Stations"><i className="fa fa-plug"></i> Stations</a>
              <div className="dropdown">
                  <button className="dropbtn"><i className="fa fa-fw fa-car"></i> My vehicles</button>      
                  <div className="dropdown-content">
                    <a href="/AddNewVehicle">Add new vehicle</a>
                    <a href="/ManageMyVehicles">Manage my vehicles</a>
                  </div>
                </div> 
                <div className="dropdown">
                  <button className="dropbtn"><i className="fa fa-history" aria-hidden="true"></i>Data of sessions</button>      
                  <div className="dropdown-content">
                    <a href="/SessionsPerPoint">Sessions per point</a>
                    <a href="/SessionsPerStation">Sessions per station</a>
                    <a href="/SessionsPerVehicle">Sessions per vehicle</a>
                    <a href="/SessionsPerProvider">Sessions per povider</a>
                  </div>
                </div> 
              <div className="topnav-right">
                {/* <a><i type="button" onClick={this.Logout.bind(this)} className="fa fa-fw fa-user"></i>Logout</a> */}
                <a href="/Logout"><i className="fa fa-fw fa-user"></i>Logout</a>
              </div>
            </div>
            <Switch>
              <Route path="/ManageMyVehicles" component={MyVehicles} />
              <Route path="/AddNewVehicle" component={AddNewVehicle} />
              <Route path="/stations" component={Stations} />
              <Route path="/SessionsPerPoint" component={SessionsPerPoint} />
              <Route path="/SessionsPerStation" component={SessionsPerStation} />
              <Route path="/SessionsPerVehicle" component={SessionsPerVehicle} />
              <Route path="/SessionsPerProvider" component={SessionsPerProvider} />
              <Route path="/Logout" component={Logout} />
              <Route path="/" component={Stations} />
            </Switch>
            <script src="/node_modules/foundation-sites/dist/js/foundation.min.js"></script>
          </body>
        </html>
      )
    } else {
      return (
        <html>
        <body className="login-body">
          <meta charSet="UTF-8" />
          <title>Login</title>

          <Grid className="body-login dispay">
          <Cell large={ 10 } medium={ 10 }>
          <div className="header-login">
          <a href="/">
                <div>Electro<span>Wheeler</span></div>
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
}

ReactDOM.render(
  <BrowserRouter>
    <App />
  </BrowserRouter>,
  document.getElementById("root")
);

// ReactDOM.render(
//   <React.StrictMode>
//     {/* <Login user={user} /> */}
//     {/* < MyVehicles user={user} /> */}
//     < Charge user={user} />
//     {/* < ChangePassword user={user} /> */}
//     {/* < History user={user} /> */}
//   </React.StrictMode>,
//   document.getElementById('root')
// );
