import React from 'react';
import axios from 'axios';
import $ from 'jquery';
import './MyVehicles.css';


class MyVehicles extends React.Component {
    constructor(props) {
        super(props);
        this.state = { user: props.user };
    }
    render() {
        return (
            <html>
                <body>
                    <meta name="viewport" content="width=device-width, initial-scale=1" />
                    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></link>
                    <title>My vechiles</title>
                    <div className="body"></div>
                    <div className="grad"></div>
                    <div className="header">
                        <div>Vechile<span>Charging</span></div>
                    </div>
                    <div class="topnav">
                        <a className="active" href="#vehicles"><i class="fa fa-fw fa-car"></i> My vehicles</a>
                        <a href="#history"><i class="fa fa-history" aria-hidden="true"></i> history of charging events</a>
                        <a href="#charge"><i class="fa fa-plug" aria-hidden="true"></i>charge</a>
                        <div className="topnav-right">
                            <div className="dropdown">
                                <button className="dropbtn">Settings
                                    <i className="fa fa-caret-down"></i>
                                </button>
                                <div className="dropdown-content">
                                    <a href="#ChangePassword">Change Password</a>
                                </div>
                            </div>
                            <a href="#logout"><i class="fa fa-fw fa-user"></i>Logout</a>
                        </div>
                    </div>
                    <div className="body1">
                        <h2><i className="fa fa-car"></i>Add New Vehicle</h2>
                        <div className="container">
                            <form action="/action_page.php">
                                <div className="row">
                                    <div className="col-50">
                                        <h3>New Vehicle</h3>
                                        <label for="brand"> Brand</label>
                                        <input type="text" id="brand" name="brand" placeholder="Audi" />
                                        <label for="type"> Type</label>
                                        <input type="text" id="type" name="type" placeholder="bev" />
                                        <label for="model"> Model</label>
                                        <input type="text" id="model" name="model" placeholder="e-tron 55" />
                                        <label for="release_year"> Release year</label>
                                        <input type="text" id="release_year" name="release_year" placeholder="2019" />
                                        <label for="battery_size">Battery size</label>
                                        <input type="text" id="battery_size" name="battery_size" placeholder="86,5" />
                                    </div>
                                    <input type="button" onClick={this.vechile_data} value="Login" className="btn" />
                                </div>
                            </form>
                        </div>
                    </div>
                    <div className="body1">
                        <h2><i className="fa fa-car"></i>Your Vehicle</h2>
                        <div className="container">
                            <form action="/action_page.php">
                                <div className="row">
                                    <div className="col-50">
                                        <h3> Vehicle 1</h3>
                                        <label for="brand"> Brand</label>
                                        <input type="text" id="brand" name="brand" placeholder="Audi" />
                                        <label for="type"> Type</label>
                                        <input type="text" id="type" name="type" placeholder="bev" />
                                        <label for="model"> Model</label>
                                        <input type="text" id="model" name="model" placeholder="e-tron 55" />
                                        <label for="release_year"> Release year</label>
                                        <input type="text" id="release_year" name="release_year" placeholder="2019" />
                                        <label for="battery_size">Battery size</label>
                                        <input type="text" id="battery_size" name="battery_size" placeholder="86,5" />
                                    </div>
                                    <input type="button" onClick={this.vechile_data} value="Login" className="btn" />
                                </div>
                            </form>
                        </div>
                    </div>
                </body>
            </html>
        )
    }
}

export default MyVehicles;