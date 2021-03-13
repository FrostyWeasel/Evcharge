import React from 'react';
import $ from 'jquery';
import './MyVehicles.css';
$.DataTable = require('datatables.net');

class MyVehicles extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [],
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
    fetch('//localhost:8765/evcharge/api/UserCars/' + localStorage.getItem("username"), requestOptions)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        var table = $(this.refs.main).DataTable({
          // dom: '<"data-table-wrapper"t>',
          data: data,
          columns: [
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
              width: "15%",
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
              'render': function (id) {
                return (
                  '<a className="btk" class="deleteBtn" id="' + id + '" ><i type="button">Delete</i></a>'
                )
              }
            }

          ],
          ordering: false
        });

        $(this.refs.main).on("click", ".deleteBtn", function (ev) {
          const requestOptions = {
            method: 'DELETE',
            headers: {
              'Content-Type': 'application/json',
              'X-OBSERVATORY-AUTH': localStorage.getItem("token")
            },
            body: JSON.stringify({ title: 'DeleteVehicle' })
          }
          fetch('//localhost:8765/evcharge/api/UserCars/' + localStorage.getItem("username") + '/' + ev.currentTarget.id, requestOptions)
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
          <title>MyVehicles</title>
          <div>
            <h2>MyVehicles</h2>

            <table ref="main" />

          </div>
        </body>
      </html>
    )
  }
}

export default MyVehicles;