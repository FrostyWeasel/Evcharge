import React from 'react';
import axios from 'axios';
import $ from 'jquery';
import './MyVehicles.css';


class SessionData extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          data: []
        };
      }
      componentDidMount() {
        //SessionsPerProvider
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
                    title: 'Time',
                    width: "15%",
                    data: 'RequestTimestamp',
                    sortable: true
                }, 
                {
                  title: 'Time',
                  width: "15%",
                  data: 'RequestTimestamp',
                  sortable: true
              }, 

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

export default SessionData;