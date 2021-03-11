import React from 'react';
import axios from 'axios';
import $ from 'jquery';
import './Charge.css';
import moment from 'moment';


class Charge extends React.Component {
    constructor(props) {
        super(props);
        this.state = { ChargingPoints: [], Providers: [], cost: [], kw: '' };
        this.updateInput = this.updateInput.bind(this);
    }
    updateInput(event) {
        this.setState({ kw: event.target.value });
        localStorage.setItem("energydelivered", event.target.value);
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
                var types = [];
                data.forEach(function (item) {
                    if (item.operator.ID == localStorage.getItem("OperatorId")) {
                        types.push(item.id);
                    }
                })
                this.setState({ ChargingPoints: types });
            })
            .catch(error => {
                console.error(error);
            })
        const requestOptions1 = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'X-OBSERVATORY-AUTH': localStorage.getItem("token")
            }
        }
        fetch('//localhost:8765/evcharge/api/energyproviders/', requestOptions)
            .then((response) => {
                return response.json();
            })
            .then((data) => {
                this.setState({ Providers: data });
            })
            .catch(error => {
                console.error(error);
            })


    }
    makeSession() {
        var date = new Date();
        const format1 = "YYYY-MM-DD HH:mm:ss"
        var dateTime1 = moment(date).format(format1);
        localStorage.setItem("startedon", dateTime1);
        localStorage.setItem("finishedon", dateTime1);
        let formData ={
            VehicleID: localStorage.getItem("carid"),
            ChargingPointID: localStorage.getItem("chargingpointid"),
            EnergyProviderID: localStorage.getItem("energyproviderid"),
            Username: localStorage.getItem("username"),
            StartedOn: localStorage.getItem("startedon"),
            FinishedOn: localStorage.getItem("finishedon"),
            Protocol: localStorage.getItem("protocol"),
            Payment: localStorage.getItem("payment"),
            Cost: localStorage.getItem("cost"),
            EnergyDelivered: localStorage.getItem("energydelivered")
        }
        // let formData = new FormData();
        // formData.append('VehicleID', 'localStorage.getItem("carid")');
        // formData.append('ChargingPointID', 'localStorage.getItem("chargingpointid")');
        // formData.append('EnergyProviderID', 'localStorage.getItem("energyproviderid")');
        // formData.append('Username', 'localStorage.getItem("username")');
        // formData.append('StartedOn', 'localStorage.getItem("startedon")');
        // formData.append('FinishedOn', 'localStorage.getItem("finishedon")');
        // formData.append('Protocol', 'localStorage.getItem("protocol")');
        // formData.append('Payment', 'localStorage.getItem("payment")');
        // formData.append('Cost', 'localStorage.getItem("cost")');
        // formData.append('EnergyDelivered', 'localStorage.getItem("energydelivered")');
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-OBSERVATORY-AUTH': localStorage.getItem("token")
            },
            body: JSON.stringify({"car": localStorage.getItem("carid"),
            "chargingPoint": localStorage.getItem("chargingpointid"),
            "energyProvider": localStorage.getItem("energyproviderid"),
            "username": localStorage.getItem("username"),
            "startedOn": localStorage.getItem("startedon"),
            "finishedOn": localStorage.getItem("finishedon"),
            "protocol": localStorage.getItem("protocol"),
            "payment": localStorage.getItem("payment"),
            "cost": localStorage.getItem("cost"),
            "energyDelivered": localStorage.getItem("energydelivered")})
        }
        fetch('//localhost:8765/evcharge/api/sessions', requestOptions)
            .then(() => {
                window.location = "/";
            })
            .catch(error => {
                console.error(error);
            })
    }
    Protocol(ev) {
        if (ev.currentTarget.value == "fast") {
            localStorage.setItem("protocol", "fast");
            localStorage.setItem("cost", localStorage.getItem("costhigh"));
        }
        else if (ev.currentTarget.value == "slow") {
            localStorage.setItem("protocol", "slow");
            localStorage.setItem("cost", localStorage.getItem("costlow"));
        }
    }
    Payment(ev) {
        localStorage.setItem("payment", ev.currentTarget.value);
    }
    ChargingPointSaveId(ev) {
        localStorage.setItem("chargingpointid", ev.currentTarget.value);
    }
    EnergyProviderId(ev) {
        localStorage.setItem("energyproviderid", ev.currentTarget.value);
        var y;
        var z;
        var x = localStorage.getItem("energydelivered");
        this.state.Providers.forEach(function (item) {
            if (localStorage.getItem("energyproviderid") == item.id) {
                y = item.highPrice;
                z = item.lowPrice;
            }
        })

        var Costlow = x * z;
        Costlow = Costlow.toFixed(2);
        var Costhigh = x * y;
        Costhigh = Costhigh.toFixed(2);

        localStorage.setItem("costlow", Costlow);
        localStorage.setItem("costhigh", Costhigh);

        $("#protocol").html("").removeClass("disabled").attr("disabled", false);
        $("#protocol").append('<option value="no" selected="selected">Choose an option</option>');
        $("#protocol").append(
            "<option value=" + "fast" + ">" + "fast " + localStorage.getItem("costhigh") + "</option>"
        )
        $("#protocol").append(
            "<option value=" + "slow" + ">" + "slow " + localStorage.getItem("costlow") + "</option>"
        )
    }
    render() {
        return (
            <div className="body1">
                <h2><i class="fa fa-exchange" aria-hidden="true"></i>Make a new charging</h2>
                <div className="row">
                    <div className="col-75">
                        <div className="container">
                            <form action="/action_page.php">
                                <div className="row">
                                    <div className="col-50">
                                        <h3>Details of charging</h3>
                                        <label for="Point"> Charging point</label>
                                        <select id="Point" onChange={this.ChargingPointSaveId.bind(this)}>
                                            <option value="no" selected="selected">Choose an option</option>
                                            {this.state.ChargingPoints.map(ChargingPoints => (
                                                <option value={ChargingPoints}>{ChargingPoints}</option>
                                            ))}
                                        </select>
                                        <label for="energy"><i className="fa fa-bolt"></i> KW</label>
                                        <input type="text" id="energy" onChange={this.updateInput.bind(this)} name="energy" placeholder="30,02" />
                                        <label for="Provider"> Energy provider</label>
                                        <select id="Provider" onChange={this.EnergyProviderId.bind(this)}>
                                            <option value="no" selected="selected">Choose an option</option>
                                            {this.state.Providers.map(Providers => (
                                                <option value={Providers.id}>{Providers.brandName}</option>
                                            ))}
                                        </select>
                                        <label for="protocol"> Protocol</label>
                                        <select disabled id="protocol" onChange={this.Protocol.bind(this)}>

                                        </select>
                                        <label for="payment"> Payment</label>
                                        <select id="payment" onChange={this.Payment.bind(this)}>
                                            <option value="no" selected="selected">Choose an option</option>
                                            <option value="card">card</option>
                                            <option value="cash">cash</option>
                                        </select>
                                    </div>
                                </div>
                                <a disabled id="Makecharge" className="btn"><i type="button" onClick={this.makeSession.bind(this)}>Make the charging</i></a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default Charge;