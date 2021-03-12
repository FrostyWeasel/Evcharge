import React, { useState }  from 'react';
import axios from 'axios';
import $ from 'jquery';
import { ModalContent, ModalFooter, ModalButton, useDialog } from 'react-st-modal';
import './MyVehicles.css';
import ChooseCar from './ChooseCar';
import { Route, Redirect, Switch, BrowserRouter } from "react-router-dom";
import { render } from 'react-dom';

class ShowDataStation extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: []
    };
  }
  
  componentDidMount() {
    const requestOptions = {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json', 
          'X-OBSERVATORY-AUTH': localStorage.getItem("token")
        }
      }
       fetch('//localhost:8765/evcharge/api/SessionsPerStation/'+ localStorage.getItem('StationDataId') + '/' +localStorage.getItem("fromdate") + '/' + localStorage.getItem("todate"), requestOptions)
        .then((response) => {
          return response.json();
        })
        .then((data) => {
          $(this.refs.main).DataTable({
            data: data.SessionsSummaryList,
            columns:[
            {
                title: 'Point',
                width: "15%",
                data: 'PointID'
            },
            {
                title: 'Point sessions',
                width: "15%",
                data: 'PointSessions'
            },
            {
              title: 'Energy delivered',
              width: "15%" ,
              data: 'EnergyDelivered'
          }
        ],
            ordering: false
         });
  })
  .catch(error => {
    console.error(error);
})
  }
render() {
    return (
        <html>
        <body className="stations-body">
          <meta charSet="UTF-8" />
          <title>Show session data </title>
        <div>
            <h4>Show session data </h4>
            <h4>Date from {localStorage.getItem("fromdate")} to { localStorage.getItem("todate")}</h4>
            <table ref="main" />
            
        </div>
        </body>
        </html>
    )
}
}

export default ShowDataStation;