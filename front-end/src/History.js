import React from 'react';
import axios from 'axios';
import $ from 'jquery';


class Stations extends React.Component {
    constructor(props) {
        super(props);
        this.deleteVehicle= this.deleteVehicle.bind(this);
        this.state = {
          data: [],
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
           fetch('//localhost:8765/evcharge/api/UserCars/' + localStorage.getItem("username"), requestOptions)
            .then((response) => {
              return response.json();
            })
            .then((data) => {
              $(this.refs.main).DataTable({
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
              <title>History of your vehicles</title>
            <div>
                <h2>History of your vehicles</h2>

                <table ref="main" />
                
            </div>
            </body>
            </html>
        )
    }
}

export default History;