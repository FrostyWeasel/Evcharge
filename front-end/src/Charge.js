import React from 'react';
import axios from 'axios';
import $ from 'jquery';
import './Charge.css';


class Charge extends React.Component {
    constructor(props) {
        super(props);
        this.state = { user: props.user };
    }
    Calculate() {
        var params1 = {
            energy: $('#energy').val(),
            costperkw: $('#costperkw').val()
        }
        var chargingcost = params1.energy * params1.costperkw;
    }
    render() {
        return (
            <html>
                <body>
                    <meta name="viewport" content="width=device-width, initial-scale=1" />
                    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
                    <title>Charge</title>
                    <div className="body"></div>
                    <div className="grad"></div>
                    <div className="header">
                        <div>Vehicle<span>Charging</span></div>
                    </div>
                    <div className="topnav">
                        <a href="#vehicles"><i className="fa fa-fw fa-car"></i> My vehicles</a>
                        <a href="#history"><i className="fa fa-history" aria-hidden="true"></i> history of charging events</a>
                        <a className="active" href="#charge"><i class="fa fa-plug" aria-hidden="true"></i> charge</a>
                        <div className="topnav-right">
                            <div className="dropdown">
                                <button className="dropbtn">Settings
                                    <i className="fa fa-caret-down"></i>
                                </button>
                                <div className="dropdown-content">
                                    <a href="#ChangePassword">Change Password</a>
                                </div>
                            </div>
                            <a href="#logout"><i className="fa fa-fw fa-user"></i>Logout</a>
                        </div>
                    </div>
                    <div className="body1">
                        <h2><i class="fa fa-calculator" aria-hidden="true"></i> Charging cost calculation </h2>
                        <div className="row">
                            <div className="col-75">
                                <div className="container">
                                    <form action="/action_page.php">
                                        <div className="row">
                                            <div className="col-50">
                                                <label for="energy"><i className="fa fa-bolt"></i> KW</label>
                                                <input type="text" id="energy" name="energy" placeholder="30,02" />
                                                <label for="costperkw"><i className="fa fa-money"></i> Cost per KW</label>
                                                <input type="text" id="costperkw" name="costperkw" placeholder="3,02" />
                                                <input type="button" onClick={this.Calculate} value="Calculate" className="btn" />
                                                
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="body1">
                        <h2><i class="fa fa-exchange" aria-hidden="true"></i>Make a new charging</h2>
                        <div className="row">
                            <div className="col-75">
                                <div className="container">
                                    <form action="/action_page.php">

                                        <div className="row">
                                            <div className="col-50">
                                                <h3>Details of charging</h3>
                                                <label for="VechileId"><i className="fa fa-car"></i> Vehicle Id</label>
                                                <input type="text" id="VechileId" name="VehicleId" placeholder="27d7610e-9a77-498a-b1b5-28d4bc92cbf2" />
                                                <label for="RequestTimestamp"><i className="fa fa-calendar"></i> Request </label>
                                                <input type="text" id="ReqestTimestamp" name="ReqestTimestamp" placeholder="2021/01/19(YYYY/MM/DD)" />
                                                <label for="energy"><i className="fa fa-bolt"></i> KW</label>
                                                <input type="text" id="energy" name="energy" placeholder="30,02" />
                                                <label for="city"><i className="fa fa-institution"></i> City</label>
                                                <input type="text" id="city" name="city" placeholder="Athens" />


                                                <div className="row">
                                                    <div className="col-50">
                                                        <label for="stationId">Station_Id</label>
                                                        <input type="text" id="stationId" name="stationId" placeholder="9474yf3gfdopw7efpw2f" />
                                                    </div>
                                                    <div className="col-50">
                                                        <label for="point_id">Point_Id</label>
                                                        <input type="text" id="point_id" name="point_id" placeholder="2829" />
                                                    </div>
                                                </div>

                                            </div>

                                            <div className="col-50">
                                                <h3>Payment</h3>
                                                <label for="fname">Accepted Cards</label>
                                                <div className="icon-container">
                                                    <i className="fa fa-cc-visa" style={{ color: "navy" }}></i>
                                                    <i className="fa fa-cc-amex" style={{ color: "blue" }}></i>
                                                    <i className="fa fa-cc-mastercard" style={{ color: "red" }}></i>
                                                    <i className="fa fa-cc-discover" style={{ color: "orange" }}></i>
                                                </div>
                                                <label for="cname">Name on Card</label>
                                                <input type="text" id="cname" name="cardname" placeholder="John More Doe" />
                                                <label for="ccnum">Credit card number</label>
                                                <input type="text" id="ccnum" name="cardnumber" placeholder="1111-2222-3333-4444" />
                                                <div className="row">
                                                    <div className="col-50">
                                                        <label for="expmonthyear">Exp Month/Year</label>
                                                        <input type="text" id="expmonthyear" name="expmonthyear" placeholder="09/24" />
                                                    </div>
                                                    <div className="col-50">
                                                        <label for="cvv">CVV</label>
                                                        <input type="text" id="cvv" name="cvv" placeholder="352" />
                                                    </div>
                                                    <div className="col-50">
                                                        <label for="email"><i className="fa fa-envelope"></i> Email</label>
                                                        <input type="text" id="email" name="email" placeholder="john@example.com" />
                                                        <label for="TotalAmount"><i className="fa fa-money"></i> Total Amount</label>
                                                        <input type="text" id="TotalAmount" name="TotalAmount" placeholder="30,09" />
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                        <input type="submit" value="Continue to checkout" className="btn" />
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </body>
            </html>
        )
    }
}

export default Charge;