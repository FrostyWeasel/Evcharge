import React from 'react';
import $ from 'jquery';
import './Charge.css';
import moment from 'moment';


class Charge extends React.Component {
    constructor(props) {
        super(props);
        this.state = { ChargingPoints: [], Providers: [], kw: '' };
        this.updateInput = this.updateInput.bind(this);
    }
    updateInput(event) {
        this.setState({ kw: event.target.value });
        localStorage.setItem("energydelivered", event.target.value);
        var cost;
        var kw1;
        $("#Provider").html("").removeClass("disabled").attr("disabled", false);
        $("#Provider").append('<option value="no" selected="selected">Choose an option</option>');
        this.state.Providers.forEach(function (item) {
            if (item.lowtoMidLimit == 0) {
                cost = item.lowPrice * localStorage.getItem("energydelivered");
                cost = cost.toFixed(2);
                $("#Provider").append(
                    "<option value=" + item.id + ">" + item.brandName + " " + cost + "</option>"
                )
            }
            else {
                if (item.lowtoMidLimit >= localStorage.getItem("energydelivered")) {
                    cost = item.lowPrice * localStorage.getItem("energydelivered");
                    cost = cost.toFixed(2);
                    $("#Provider").append(
                        "<option value=" + item.id + ">" + item.brandName + " " + cost + "</option>"
                    )
                }
                if (item.lowtoMidLimit < localStorage.getItem("energydelivered")) {
                    if (item.midtoHighLimit >= localStorage.getItem("energydelivered")) {
                        cost = item.lowPrice * item.lowtoMidLimit;
                        kw1 = localStorage.getItem("energydelivered") - item.lowtoMidLimit;
                        cost = cost + (kw1 * item.midPrice);
                        cost = cost.toFixed(2);
                        $("#Provider").append(
                            "<option value=" + item.id + ">" + item.brandName + " " + cost + "</option>"
                        )
                    }
                    if (item.midtoHighLimit < localStorage.getItem("energydelivered")) {
                        cost = item.lowPrice * item.lowtoMidLimit;
                        cost = cost + ((item.midtoHighLimit - item.lowtoMidLimit) * item.midPrice);
                        kw1 = localStorage.getItem("energydelivered") - item.midtoHighLimit;
                        cost = cost + kw1 * item.highPrice;
                        cost = cost.toFixed(2);
                        $("#Provider").append(
                            "<option value=" + item.id + ">" + item.brandName + " " + cost + "</option>"
                        )
                    }
                }
            }
        })
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
    makeSession(ev) {
        ev.preventDefault();
        var value = parseFloat(localStorage.getItem("energydelivered"));
        if (value <= localStorage.getItem("UsableBatterySizeforcharge") && value > 0) {
            var date = new Date();
            const format1 = "yyyy-MM-DDThh:mm:ss"
            var dateTime1 = moment(date).format(format1);
            localStorage.setItem("startedon", dateTime1);
            var milliseconds = new Date().getTime() + (1 * 60 * 60 * 1000);
            var later = new Date(milliseconds);
            var dateTime2 = moment(later).format(format1);
            localStorage.setItem("finishedon", dateTime2);
            var car = localStorage.getItem("carid");
            var chargingPoint = parseInt(localStorage.getItem("chargingpointid"));
            var energyProvider = parseInt(localStorage.getItem("energyproviderid"));
            var username = localStorage.getItem("username");
            var startedOn = localStorage.getItem("startedon");
            var finishedOn = localStorage.getItem("finishedon");
            var protocol = localStorage.getItem("protocol");
            var payment = localStorage.getItem("payment");
            var cost = parseFloat(localStorage.getItem("cost"));
            var energyDelivered = parseFloat(localStorage.getItem("energydelivered"));
            let formData = {
                VehicleID: car,
                ChargingPointID: chargingPoint,
                EnergyProviderID: energyProvider,
                Username: username,
                StartedOn: startedOn,
                FinishedOn: finishedOn,
                Protocol: protocol,
                Payment: payment,
                Cost: cost,
                EnergyDelivered: energyDelivered
            };
            const requestOptions = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'X-OBSERVATORY-AUTH': localStorage.getItem("token")
                },
                body: JSON.stringify(formData)
            }
            fetch('//localhost:8765/evcharge/api/sessions', requestOptions)
                .then(() => {
                    window.location = "/";
                })
                .catch(error => {
                    alert("You need to fill in all the fields");
                    window.location.reload();
                    console.error(error);
                })
        }
        if (value > localStorage.getItem("UsableBatterySizeforcharge")) {
            ev.preventDefault();
            alert("Usable battery size of your car is less than the kw you filled in");
            window.location.reload();
        }
        if (value <= 0) {
            ev.preventDefault();
            alert("Kw can not be <=0");
            window.location.reload();
        }
    }
    Protocol(ev) {
        localStorage.setItem("protocol", ev.currentTarget.value);
        $("#payment").html("").removeClass("disabled").attr("disabled", false);
        $("#payment").append('<option value="no" selected="selected">Choose an option</option>');
        $("#payment").append(
            "<option value=" + "card" + ">" + "card" + "</option>"
        )
        $("#payment").append(
            "<option value=" + "cash" + ">" + "cash" + "</option>"
        )
    }
    Payment(ev) {
        localStorage.setItem("payment", ev.currentTarget.value);
    }
    ChargingPointSaveId(ev) {
        localStorage.setItem("chargingpointid", ev.currentTarget.value);
    }
    EnergyProviderId(ev) {
        localStorage.setItem("energyproviderid", ev.currentTarget.value);
        var cost;
        var kw1;
        this.state.Providers.forEach(function (item) {
            if (item.id == localStorage.getItem("energyproviderid")) {
                if (item.lowtoMidLimit == 0) {
                    cost = item.lowPrice * localStorage.getItem("energydelivered");
                    cost = cost.toFixed(2);
                    localStorage.setItem("cost", cost);
                }
                else {
                    if (item.lowtoMidLimit >= localStorage.getItem("energydelivered")) {
                        cost = item.lowPrice * localStorage.getItem("energydelivered");
                        cost = cost.toFixed(2);
                        localStorage.setItem("cost", cost);
                    }
                    if (item.lowtoMidLimit < localStorage.getItem("energydelivered")) {
                        if (item.midtoHighLimit >= localStorage.getItem("energydelivered")) {
                            cost = item.lowPrice * item.lowtoMidLimit;
                            kw1 = localStorage.getItem("energydelivered") - item.lowtoMidLimit;
                            cost = cost + (kw1 * item.midPrice);
                            cost = cost.toFixed(2);
                            localStorage.setItem("cost", cost);
                        }
                        if (item.midtoHighLimit < localStorage.getItem("energydelivered")) {
                            cost = item.lowPrice * item.lowtoMidLimit;
                            cost = cost + ((item.midtoHighLimit - item.lowtoMidLimit) * item.midPrice);
                            kw1 = localStorage.getItem("energydelivered") - item.midtoHighLimit;
                            cost = cost + kw1 * item.highPrice;
                            cost = cost.toFixed(2);
                            localStorage.setItem("cost", cost);
                        }
                    }
                }
            }
        })

        $("#protocol").html("").removeClass("disabled").attr("disabled", false);
        $("#protocol").append('<option value="no" selected="selected">Choose an option</option>');
        $("#protocol").append(
            "<option value=" + "accharger" + ">" + "ac charger" + "</option>"
        )
        $("#protocol").append(
            "<option value=" + "dccharger" + ">" + "dc charger" + "</option>"
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
                                        <select disabled id="Provider" onChange={this.EnergyProviderId.bind(this)}>
                                        </select>
                                        <label for="protocol"> Protocol</label>
                                        <select disabled id="protocol" onChange={this.Protocol.bind(this)}>
                                        </select>
                                        <label for="payment"> Payment</label>
                                        <select disabled id="payment" onChange={this.Payment.bind(this)}>
                                        </select>
                                    </div>
                                </div>
                                <button id="Makecharge" className="btn" onClick={this.makeSession.bind(this)}>Make the charging</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default Charge;