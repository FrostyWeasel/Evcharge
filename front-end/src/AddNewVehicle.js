import React from 'react';
import axios from 'axios';
import $ from 'jquery';
import './MyVehicles.css';


class AddNewVehicle extends React.Component {
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
                    <div className="body1">
                        <h2><i className="fa fa-car"></i>Add New Vehicle</h2>
                        <div className="container">
                            <form action="/action_page.php">
                                <div className="row">
                                    <div className="col-50">
                                        <h3>New Vehicle</h3>
                                        <h4>Brand</h4>
                                        <label for="type"> Type</label>
                                        <select id="type" onChange={this.chooseType}>
                                            <option value="AUDI">Audi</option>
                                            <option value="OPEL">Opel</option>
                                            <option value="VOLVO">Volvo</option>
                                        </select>
                                        <label for="model"> Model</label>
                                        <select disabled id="model" name="model" onChange={this.chooseModel}>
                                            <option value="AUDI">Audi</option>
                                            <option value="OPEL">Opel</option>
                                            <option value="VOLVO">Volvo</option>
                                        </select>
                                        <label for="release_year"> Release year</label>
                                        <select disabled id="release_year" name="release_year" onChange={this.chooseReleaseYear}>
                                            <option value="1999">1999</option>
                                            <option value="2000">2000</option>
                                            <option value="1998">1998</option>
                                        </select>
                                        <label for="battery_size">Battery size</label>
                                        <select disabled id="battery_size" name="battery_size" >
                                            <option value="80">80</option>
                                            <option value="70">70</option>
                                            <option value="90">90</option>
                                        </select>
                                    </div>
                                    <input type="button" onClick={this.vechile_data} value="Add new vehicle" className="btn" />
                                </div>
                            </form>
                        </div>
                    </div>
                </body>
            </html>
        )
    }

    chooseType() {
    $("#model").attr("disabled", false);
    }
    chooseModel() {
        $("#release_year").attr("disabled", false);
    }
    chooseReleaseYear() {
            $("#battery_size").attr("disabled", false);
    }
}

export default AddNewVehicle;