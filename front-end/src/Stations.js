import React, { useState }  from 'react';
import axios from 'axios';
import $ from 'jquery';
import { ModalContent, ModalFooter, ModalButton, useDialog } from 'react-st-modal';
import './MyVehicles.css';
import ChooseCar from './ChooseCar';
import { Route, Redirect, Switch, BrowserRouter } from "react-router-dom";
import { render } from 'react-dom';

class Stations extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          data: [], ChargingPoints: []
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
           fetch('//localhost:8765/evcharge/api/chargingStations', requestOptions)
            .then((response) => {
              return response.json();
            })
            .then((data) => {
              const states={data};
              $(this.refs.main).DataTable({
                // dom: '<"data-table-wrapper"t>',
                data: data,
                columns:[
                  {
                    title: 'Town',
                    width: "15%",
                    data: 'address.Town'
                },
                {
                    title: 'Address',
                    width: "15%",
                    data: 'address.AddressLine1'
                },
                {
                  title: 'Country',
                  width: "15%" ,
                  data: 'address.Country.Title'
              },
            {
              title: 'Action',
              width: "25%",
              data: 'operator.ID',
              'render' : function(id){
                return(
                '<a className="btk" class="makechargeBtn" id="'+ id + '" ><i type="button">charge</i></a>'
                )}
          }

            ],
                ordering: false
             });
             $(this.refs.main).on("click",".makechargeBtn",function(ev){
              localStorage.setItem('OperatorId',ev.currentTarget.id);
              window.location = "http://localhost:3000/ChooseCar";
        })
      })
      }
    render() {
        return (
            <html>
            <body className="stations-body">
              <meta charSet="UTF-8" />
              <title>Stations</title>
            <div>
                <h2>Stations</h2>

                <table ref="main" />
                
            </div>
            </body>
            </html>
        )
    }
}

export default Stations;