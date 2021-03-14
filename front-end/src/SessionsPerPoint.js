import React from 'react';
import $ from 'jquery';


class SessionsPerPoint extends React.Component {
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
    fetch('//localhost:8765/evcharge/api/chargingPoints/', requestOptions)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        $(this.refs.main).DataTable({
          // dom: '<"data-table-wrapper"t>',
          data: data,
          columns: [
            {
              title: 'Charging point id',
              width: "15%",
              data: 'id'
            },
            {
              title: 'Operator name',
              width: "15%",
              data: 'operator.Title'
            },
            {
              title: 'Action',
              width: "25%",
              data: 'id',
              render(id) {
                return (
                  '<a class="pointsessions" className="btk" id="' + id + '" ><i type="button">See sessions</i></a>'
                )
              }
            }

          ],
          ordering: false
        });
        $(this.refs.main).on("click", ".pointsessions", function (ev) {
          localStorage.setItem('PointDataId', ev.currentTarget.id);
          localStorage.setItem("value", "point");
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
          <title>Sessions Per Point</title>
          <div>
            <h2>Sessions Per Point</h2>

            <table ref="main" />

          </div>
        </body>
      </html>
    )
  }
}

export default SessionsPerPoint;