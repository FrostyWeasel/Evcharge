import React from 'react';
import $ from 'jquery';
import './MyVehicles.css';

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
    fetch('//localhost:8765/evcharge/api/SessionsPerStation/' + localStorage.getItem('StationDataId') + '/' + localStorage.getItem("fromdate") + '/' + localStorage.getItem("todate"), requestOptions)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        $(this.refs.main).DataTable({
          data: data.SessionsSummaryList,
          columns: [
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
              width: "15%",
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
            <h4>Date from {localStorage.getItem("fromdate")} to {localStorage.getItem("todate")}</h4>
            <h4>Number of charging sessions: {localStorage.getItem("NumberOfChargingSessions")}</h4>
            <h4>Total energy delivered: {localStorage.getItem("TotalEnergyDelivered")}</h4>
            <table ref="main" />

          </div>
        </body>
      </html>
    )
  }
}

export default ShowDataStation;