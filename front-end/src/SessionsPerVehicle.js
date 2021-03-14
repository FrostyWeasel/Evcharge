import React from 'react';
import $ from 'jquery';
import './MyVehicles.css';



class SessionsPerVehicle extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [], Cars: []
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
    fetch('//localhost:8765/evcharge/api/cars', requestOptions)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        var initial = data;
        this.setState({ Cars: initial });
        $(this.refs.main).DataTable({
          // dom: '<"data-table-wrapper"t>',
          data: data,
          columns: [
            {
              title: 'Brand',
              width: "10%",
              data: 'brand.name',
              sortable: true
            },
            {
              title: 'Type',
              width: "10%",
              data: 'type'
            },
            {
              title: 'Model',
              width: "10%",
              data: 'model'
            },
            {
              title: 'Release year',
              width: "10%",
              data: 'release_year'
            },
            {
              title: 'Battery size',
              width: "10%",
              data: 'usable_battery_size'
            },
            {
              title: 'Action',
              width: "17,5%",
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
          localStorage.setItem('VehicledataId', ev.currentTarget.id);
          localStorage.setItem("value", "vehicle");
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
          <title>Sessions per vehicle</title>
          <div>
            <h2>Sessions per vehicle</h2>

            <table ref="main" />

          </div>
        </body>
      </html>
    )
  }
}

export default SessionsPerVehicle;