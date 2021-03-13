import React from 'react';
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
        this.setState({ ChargingPoints: initial });
        $(this.refs.main).DataTable({
          // dom: '<"data-table-wrapper"t>',
          data: data,
          columns: [
            {
              title: 'Provider name',
              width: "15%",
              data: 'brandName',
              sortable: true
            },
            {
              title: 'Action',
              width: "25%",
              data: 'id',
              'render': function (id) {
                return (
                  '<a className="btk" class="SessionsProviderBtn" id="' + id + '" ><i type="button">See sessions</i></a>'
                )
              }
            }

          ],
          ordering: false
        });
        $(".SessionsProviderBtn").on('click', function (ev) {
          localStorage.setItem('ProviderDataId', ev.currentTarget.id);
          localStorage.setItem("value", "provider");
          window.location = "http://localhost:3000/ChooseDate";
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
          <title>Sessions per provider</title>
          <div>
            <h2>Sessions per provider</h2>

            <table ref="main" />

          </div>
        </body>
      </html>
    )
  }
}

export default SessionsPerProvider;