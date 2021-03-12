import React, { useState } from "react";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";
import moment from 'moment';
import './MyVehicles.css';


class ChooseDate extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      startDate: new Date(), startDate1: new Date()
    };
    this.handleStartChange = this.handleStartChange.bind(this);
    this.handleStartChange1 = this.handleStartChange1.bind(this);
  }

  handleStartChange = (date) => {
    this.setState({
      startDate: date
    })
  }
  handleStartChange1 = (date) => {
    this.setState({
      startDate1: date
    })
  }
  chooseDate(ev){
    ev.preventDefault();
    const format = "yyyy-MM-DD"
    var dateTime = moment(this.state.startDate).format(format);
    localStorage.setItem("fromdate", dateTime);
    var dateTime1 = moment(this.state.startDate1).format(format);
    localStorage.setItem("todate", dateTime1);
  if(localStorage.getItem("value")=="vehicle"){
    window.location = "//localhost:3000/ShowDataVehicle";
  }
  if(localStorage.getItem("value")=="station"){
    window.location = "//localhost:3000/ShowDataStation";
  }
  if(localStorage.getItem("value")=="point"){
    window.location = "//localhost:3000/ShowDataPoint";
  }
  if(localStorage.getItem("value")=="provider"){
    window.location = "//localhost:3000/ShowDataProvider";
  }
   }

  render() {
    return (
      <html>
        <body className="stations-body">
          <meta charSet="UTF-8" />
          <title>Choose date</title>
          <div>
            <h2>Choose date</h2>

            <table ref="main" />

          </div>
          <div className="body1">
            <div className="col-75">
              <div className="container">
                <form action="/action_page.php">
                  <h3>From</h3>

                  <div className="filters">
                    <div id="filterbox">
                      <DatePicker
                        selected={this.state.startDate}
                        onChange={this.handleStartChange} />
                    </div>
                  </div>
                  <h3>To</h3>
                  <div className="filters">
                    <div id="filterbox">
                      <DatePicker
                        selected={this.state.startDate1}
                        onChange={this.handleStartChange1} />
                    </div>
                  </div>
                  <button id="chooseDate" className="btn" onClick={this.chooseDate.bind(this)}>Show data</button>
                </form>
              </div>
            </div>
          </div>
        </body>
      </html>
    )
  }
}

export default ChooseDate;