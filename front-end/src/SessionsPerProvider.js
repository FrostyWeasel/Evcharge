import React from 'react';
import axios from 'axios';
import $ from 'jquery';
import './MyVehicles.css';


class SessionsPerProvider extends React.Component {
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
           fetch('//localhost:8765/evcharge/api/energyproviders', requestOptions)
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
                    title: 'Provider name',
                    width: "15%",
                    data: 'brandName'
                }, 
            {
              title: 'Action',
              width: "25%",
              data: 'id',
              'render' : function(id){
                return(
                '<a className="btk" class="SessionsBtn" id="'+ id + '" ><i type="button">See sessions</i></a>'
                )}
          }

            ],
                ordering: false
             });
             $(".SessionsBtn").on('click',function(ev){
              const requestOptions = {
                method: 'DELETE',
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

export default SessionsPerProvider;