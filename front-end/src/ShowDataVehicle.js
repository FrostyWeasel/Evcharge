import React from 'react';
import $ from 'jquery';
import './MyVehicles.css';

class ShowDataVehicle extends React.Component {
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
    fetch('//localhost:8765/evcharge/api/SessionsPerEV/' + localStorage.getItem('VehicledataId') + '/' + localStorage.getItem("fromdate") + '/' + localStorage.getItem("todate"), requestOptions)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        $(this.refs.main).DataTable({
          data: data.VehicleChargingSessionsList,
          columns: [
            {
              title: 'Energy provider',
              width: "15%",
              data: 'EnergyProvider'
            },
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
              data: 'ΕnergyDelivered'
            },
            {
              title: 'Cost per KWh',
              width: "15%",
              data: 'CostPerKWh'
            },
            {
              title: 'Price policy',
              width: "15%",
              data: 'PricePolicyRef'
            },
            {
              title: 'Session cost',
              width: "15%",
              data: 'SessionCost'
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
            <h4>Date from {localStorage.getItem("fromdate")} to {localStorage.getItem("todate")}</h4>
            <h4>Number of visited points: {localStorage.getItem("NumberOfVisitedPoints")}</h4>
            <h4>Total energy consumed: {localStorage.getItem("TotalEnergyConsumed")}</h4>
            <table ref="main" />

          </div>
        </body>
      </html>
    )
  }
}

export default ShowDataVehicle;