import React from 'react';
import $ from 'jquery';
import './MyVehicles.css';

class ShowDataProvider extends React.Component {
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
    fetch('//localhost:8765/evcharge/api/SessionsPerProvider/' + localStorage.getItem('ProviderDataId') + '/' + localStorage.getItem("fromdate") + '/' + localStorage.getItem("todate"), requestOptions)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        $(this.refs.main).DataTable({
          data: data,
          columns: [
            {
              title: 'Provider Name',
              width: "15%",
              data: 'ProviderName'
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
              data: 'EnergyDelivered'
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
              title: 'Total cost',
              width: "15%",
              data: 'TotalCost'
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
            <table ref="main" />

          </div>
        </body>
      </html>
    )
  }
}

export default ShowDataProvider;