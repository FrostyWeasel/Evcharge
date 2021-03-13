import React from 'react';
import $ from 'jquery';
import './MyVehicles.css';

class ShowDataPoint extends React.Component {
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
    fetch('//localhost:8765/evcharge/api/SessionsPerPoint/' + localStorage.getItem('PointDataId') + '/' + localStorage.getItem("fromdate") + '/' + localStorage.getItem("todate"), requestOptions)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        $(this.refs.main).DataTable({
          data: data.ChargingSessionsList,
          columns: [
            {
              title: 'Started on',
              width: "15%",
              data: 'StartedOn'
            },
            {
              title: 'Finished on',
              width: "15%",
              data: 'FinishedOn'
            },
            {
              title: 'Energy delivered',
              width: "15%",
              data: 'EnergyDelivered'
            },
            {
              title: 'Payment',
              width: "15%",
              data: 'Payment'
            },
            {
              title: 'Protocol',
              width: "15%",
              data: 'Protocol'
            },
            {
              title: 'Vehicle type',
              width: "15%",
              data: 'VehicleType'
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
          <title>Show session data of point {localStorage.getItem("PointDataId")}</title>
          <div>
            <h4>Show session data of point {localStorage.getItem("PointDataId")}</h4>
            <h4>Point operator: {localStorage.getItem("Operatortitle")}</h4>
            <h4>Date from {localStorage.getItem("fromdate")} to {localStorage.getItem("todate")}</h4>
            <table ref="main" />

          </div>
        </body>
      </html>
    )
  }
}

export default ShowDataPoint;