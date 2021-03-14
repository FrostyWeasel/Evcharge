import React from 'react';
import $ from 'jquery';
import './MyVehicles.css';


class SessionsPerStation extends React.Component {
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
    fetch('//localhost:8765/evcharge/api/chargingStations', requestOptions)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        var initial = data;
        this.setState({ ChargingPoints: initial });
        $(this.refs.main).DataTable({
          data: data,
          columns: [
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
              width: "15%",
              data: 'address.Country.Title'
            },
            {
              title: 'Action',
              width: "25%",
              data: 'id',
              'render': function (id) {
                return (
                  '<a className="btk" class="SessionsBtn" id="' + id + '" ><i type="button">See sessions</i></a>'
                )
              }
            }

          ],
          ordering: false
        });
        $(this.refs.main).on("click", ".SessionsBtn", function (ev) {
          localStorage.setItem("value", "station");
          localStorage.setItem('StationDataId', ev.currentTarget.id);
          window.location = "//localhost:3000/ChooseDate";
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
          <title>Sessions per station</title>
          <div>
            <h2>Sessions per station</h2>

            <table ref="main" />

          </div>
        </body>
      </html>
    )
  }
}

export default SessionsPerStation;