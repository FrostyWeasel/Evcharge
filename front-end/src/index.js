import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Login from './Login';
// import MyVehicles from './MyVehicles';
// import Charge from './Charge';
// import ChangePassword from './ChangePassword';
// import History from './History';

const user = localStorage.getItem('user');

ReactDOM.render(
  <React.StrictMode>
    <Login user={user} />
    {/* < MyVehicles user={user} /> */}
    {/* < Charge user={user} /> */}
    {/* < ChangePassword user={user} /> */}
    {/* < History user={user} /> */}
  </React.StrictMode>,
  document.getElementById('root')
);
