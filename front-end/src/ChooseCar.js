import React from 'react';
import axios from 'axios';
import $ from 'jquery';
import './MyVehicles.css';
$.DataTable =require('datatables.net');

class ChooseCar extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          data: [],
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
           fetch('//localhost:8765/evcharge/api/UserCars/' + localStorage.getItem("username"), requestOptions)
            .then((response) => {
              return response.json();
            })
            .then((data) => {
              var table = $(this.refs.main).DataTable({
                // dom: '<"data-table-wrapper"t>',
                data: data,
                columns:[
                    {
                    title: 'Brand',
                    width: "15%",
                    data: 'brand.name'
                },
                {
                    title: 'Type',
                    width: "15%",
                    data: 'type'
                },
                {
                  title: 'Model',
                  width: "15%" ,
                  data: 'model'
              },
              {
                  title: 'Release year',
                  width: "15%",
                  data: 'release_year'
              },
              {
                title: 'Battery size',
                width: "15%",
                data: 'usable_battery_size'
            },
            {
              title: 'Action',
              width: "25%",
              data: 'id',
              'render' : function(id){
                return(
                '<a className="btk" class="ChooseCarBtn" id="'+ id + '" ><i type="button">Choose</i></a>'
                )}
          }

            ],
                ordering: false
             });

             $(".ChooseCarBtn").on('click',function(ev){
              localStorage.setItem("carid", ev.currentTarget.id);
              localStorage.setItem("chargingpointid", " ");
              localStorage.setItem("energyproviderid", " ");
              localStorage.setItem("startedon", " ");
              localStorage.setItem("finishedon", " ");
              localStorage.setItem("protocol", " ");
              localStorage.setItem("payment", " ");
              localStorage.setItem("cost", " ");
              localStorage.setItem("energydelivered", " ");
              window.location = "http://localhost:3000/Charge";
            })
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
              <title>Choose vehicle for session</title>
            <div>
                <h2>Choose vehicle for session</h2>

                <table ref="main" />
                
            </div>
            </body>
            </html>
        )
    }
}

export default ChooseCar;