import React from 'react';
import axios from 'axios';
import $ from 'jquery';
import './ChangePassword.css';

class ChangePassword extends React.Component {
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
                    <title>Change Password</title>
                    <div className="body"></div>
                    <div className="grad"></div>
                    <div className="header">
                        <div>Vehicle<span>Charging</span></div>
                    </div>
                    <div className="topnav">
                        <a href="#vehicles"><i className="fa fa-fw fa-car"></i> My vehicles</a>
                        <a href="#history"><i className="fa fa-history" aria-hidden="true"></i> history of charging events</a>
                        <a href="#charge"><i class="fa fa-plug" aria-hidden="true"></i> charge</a>
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
                        <h2><i class="fa fa-gear" aria-hidden="true"></i> Settings </h2>
                        <div className="row">
                            <div className="col-75">
                                <div className="container">
                                    <form action="/action_page.php">
                                        <div className="row">
                                            <div className="col-50">
                                            <h3>Change Password</h3>
                                                <label for="Password"><i className="fa fa-key"></i> Password</label>
                                                <input type="text" id="Password" name="Password" placeholder="Password" />
                                                <label for="NewPassword"><i className="fa fa-key"></i> New Password</label>
                                                <input type="text" id="NewPassword" name="NewPassword" placeholder="Type your new password" />
                                                <label for="NewPasswordAgain"><i className="fa fa-key"></i> New Password</label>
                                                <input type="text" id="NewPasswordAgain" name="NewPasswordAgain" placeholder="Type your new password again" />
                                                <input type="button" onClick={this.Calculate} value="Change" className="btn" />
                                                
                                            </div>
                                        </div>
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

export default ChangePassword;