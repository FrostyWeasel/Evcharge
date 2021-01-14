import React from 'react';
import axios from 'axios';
import $ from 'jquery';
import './History.css';

class History extends React.Component {
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
                        <a className="active" href="#history"><i className="fa fa-history" aria-hidden="true"></i> history of charging events</a>
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
                    
                </body>
            </html>
        )
    }
}

export default History;