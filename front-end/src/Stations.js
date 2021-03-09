import React from 'react';
import axios from 'axios';
import $ from 'jquery';
import './MyVehicles.css';


class Stations extends React.Component {
    constructor(props) {
        super(props);
        this.deleteVehicle= this.deleteVehicle.bind(this);
        this.state = {
          data: [], ChargingPoints: []
        };
      }
      deleteVehicle(ev){
        const requestOptions = {
          method: 'GET',
          headers: { 
            'Content-Type': 'application/json', 
            'X-OBSERVATORY-AUTH': localStorage.getItem("token") 
        },   
          body: JSON.stringify({ title: 'NewVehicle' })
        }
         fetch('//localhost:8765/evcharge/api/UserCars/'+ localStorage.getItem("username")+ '/' + ev.currentTarget.id, requestOptions)
          .then((response) => {
            window.location.reload();
          })
          .catch(error => {
            console.error(error);
          })
      }
      componentDidMount() {
        const requestOptions = {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json', 
              'X-OBSERVATORY-AUTH': localStorage.getItem("token")
            }
          }
           fetch('//localhost:8765/evcharge/api/admin/chargingStations', requestOptions)
            .then((response) => {
              return response.json();
            })
            .then((data) => {
                var initial = data;
                this.setState({ChargingPoints: initial});
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
              // {
              //     title: 'Charging Point',
              //     width: "15%",
              //     data: 'chargingPoints.operator.Title',
              //     render(){
              //       return(
              //           <select id="brand" onChange={this.getBrandData.bind(this)}>
              //               <option value="no" selected="selected">Choose an option</option>
              //               <option value={this.state.ChargingPoints.chargingPoints.id}>{this.state.ChargingPoints.chargingPoints.operator.Title}</option>
              //           </select>
              //       )}
              // },
              
            {
              title: 'Action',
              width: "25%",
              data: 'id',
              render(id){
                return(
                '<a className="btk"><i type="button" onClick="deleteVehicle(id)">Delete</i></a>'
                )}
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