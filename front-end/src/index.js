import React from 'react';
import ReactDOM from 'react-dom';
import $ from 'jquery';
// import Dialog from 'react-bootstrap-dialog'
import { Router, Link, browserHistory } from 'react-router';
import { Route, Redirect, Switch, BrowserRouter } from "react-router-dom";
import './index.css';
import Login from './Login';
import MyVehicles from './MyVehicles';
import AddNewVehicle from './AddNewVehicle';
import Charge from './Charge';
//import ChangePassword from './ChangePassword';
import History from './History';
import Stations from './Stations';

import 'foundation-sites/dist/css/foundation.min.css';
import { Button, Colors, Grid, Cell } from 'react-foundation';

const user = localStorage.getItem('user');

var loggedIn= true;
class App extends React.Component {
  render() {
    if (loggedIn) {
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
                <div>Vehicle<span>Charging</span></div>
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
              <a href="/history"><i className="fa fa-history" aria-hidden="true"></i> Chargings history</a>
              <div className="topnav-right">
                <a href="/logout"><i className="fa fa-fw fa-user"></i>Logout</a>
              </div>
            </div>
            <Switch>
              <Route path="/ManageMyVehicles" component={MyVehicles} />
              <Route path="/AddNewVehicle" component={AddNewVehicle} />
              <Route path="/history" component={History} />
              <Route path="/stations" component={Stations} />
              {/* <Redirect from="/" exact to="/home" />
            <Redirect to="/not-found" /> */}
            </Switch>
            <script src="/node_modules/foundation-sites/dist/js/foundation.min.js"></script>
          </body>
        </html>
      )
    } else {
      return (
        <html>
          <body>
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
            <title>Stations</title>
            <div className="body"></div>
            <div className="grad"></div>
            <div className="header">
              <a href="/">
                <div>Vehicle<span>Charging</span></div>
              </a>
            </div>
            <div className="topnav">
              <a href="/Stations"><i className="icon-station"></i> Stations</a>
              <div className="topnav-right">
                <a href="/login"><i className="fa fa-fw fa-user"></i>Login</a>
              </div>
            </div>
            <Switch>
              {<Route path="/login" component={Login} />
          }
            </Switch>
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
