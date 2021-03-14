import React from 'react';
import $ from 'jquery';
import './MyVehicles.css';


class AddNewVehicle extends React.Component {
    constructor(props) {
        super(props);
        this.state = { listItems: [], vehicles: [] };
    }
    componentDidMount() {
        const requestOptions = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'X-OBSERVATORY-AUTH': localStorage.getItem("token")
            }
        }
        fetch('//localhost:8765/evcharge/api/cars', requestOptions)
            .then((response) => {
                return response.json();
            })
            .then((data) => {
                var initial = data;
                var cars = [];
                data.forEach(function (item) {
                    return cars.push(item.brand);
                });
                cars = cars.reduce((unique, o) => {
                    if (!unique.some(obj => obj.id === o.id)) {
                        unique.push(o);
                    }
                    return unique;
                }, []);
                this.setState({ listItems: cars, vehicles: initial });
            })
            .catch(error => {
                console.error(error);
            })
    }
    NewVehicle(ev) {
        ev.preventDefault();
        if (localStorage.getItem("carid") == " ") {
            alert("you have not chosen a car");
            window.location.reload();
        }
        else {
            const requestOptions1 = {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'X-OBSERVATORY-AUTH': localStorage.getItem("token")
                }
            }
            fetch('//localhost:8765/evcharge/api/UserCars/' + localStorage.getItem("username"), requestOptions1)
                .then((response) => {
                    return response.json();
                })
                .then((data) => {
                    data.forEach(function (item) {
                        if (item.id == localStorage.getItem("carid")) {
                            alert("You already have this car");
                            window.location = "//localhost:3000/AddNewVehicle";
                        }
                    })

                })
                .catch(error => {
                    console.error(error);
                })
            const requestOptions = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'X-OBSERVATORY-AUTH': localStorage.getItem("token")
                },
                body: JSON.stringify({ title: 'NewVehicle' })
            }
            fetch('//localhost:8765/evcharge/api/UserCars/' + localStorage.getItem("username") + '/' + localStorage.getItem("carid"), requestOptions)
                .then((response) => {
                    localStorage.setItem("carid", " ");
                    window.location = "//localhost:3000/ManageMyVehicles";
                })
                .catch(error => {
                    console.error(error);
                })
        }
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
                                        <label for="brand"> Brand</label>
                                        <select id="brand" onChange={this.getBrandData.bind(this)}>
                                            <option value="no" selected="selected">Choose an option</option>
                                            {this.state.listItems.map(listItem => (
                                                <option value={listItem.id}>{listItem.name}</option>
                                            ))}
                                        </select>
                                        <label for="type"> Type</label>
                                        <select disabled id="type" onChange={this.chooseType.bind(this)}>
                                        </select>
                                        <label for="model"> Model</label>
                                        <select disabled id="model" name="model" onChange={this.chooseModel.bind(this)}>
                                        </select>
                                        <label for="release_year"> Release year</label>
                                        <select disabled id="release_year" name="release_year" onChange={this.chooseReleaseYear.bind(this)}>
                                        </select>
                                        <label for="battery_size">Battery size</label>
                                        <select disabled id="battery_size" name="battery_size" onChange={this.chooseAddNewVehicle.bind(this)}>
                                        </select>
                                        <button id="AddNewVehicle" className="btn" onClick={this.NewVehicle.bind(this)}>Add new vehicle</button>
                                    </div>

                                </div>
                            </form>
                        </div>
                    </div>
                </body>
            </html>
        )
        // })
    }
    getBrandData(ev) {
        var types = [];
        this.state.vehicles.forEach(function (item) {
            if (item.brand.id == ev.currentTarget.value) {
                types.push(item.type);
                localStorage.setItem('carbrand', item.brand.id);
            }
        })

        var list = types.filter(function (el, index, arr) {
            return index === types.indexOf(el);
        });

        $("#type").html("").removeClass("disabled").attr("disabled", false);
        $("#type").append('<option value="no" selected="selected">Choose an option</option>');
        list.forEach(function (item) {
            $("#type").append(
                "<option value=" + item + ">" + item + "</option>"
            )
        })
    }
    chooseType(ev) {
        var types = [];
        this.state.vehicles.forEach(function (item) {
            if (item.type === ev.currentTarget.value && localStorage.getItem("carbrand") == item.brand.id) {
                types.push(item.model);
                localStorage.setItem('cartype', item.type);
            }
        })

        var models = types.filter(function (el, index, arr) {
            return index === types.indexOf(el);
        });

        $("#model").html("").removeClass("disabled").attr("disabled", false);
        $("#model").append('<option value="no" selected="selected">Choose an option</option>');
        models.forEach(function (item) {
            $("#model").append(
                "<option value=" + encodeURIComponent(item) + ">" + item + "</option>"
            )
        })
    }
    chooseModel(ev) {
        var types = [];
        this.state.vehicles.forEach(function (item) {
            if (item.model === decodeURIComponent(ev.currentTarget.value) && localStorage.getItem("carbrand") == item.brand.id && localStorage.getItem("cartype") == item.type) {
                types.push(item.release_year);
                localStorage.setItem('carmodel', item.model);
            }
        })

        var releaseyear = types.filter(function (el, index, arr) {
            return index === types.indexOf(el);
        });

        $("#release_year").html("").removeClass("disabled").attr("disabled", false);
        $("#release_year").append('<option value="no" selected="selected">Choose an option</option>');
        releaseyear.forEach(function (item) {
            $("#release_year").append(
                "<option value=" + item + ">" + item + "</option>"
            )
        })
    }
    chooseReleaseYear(ev) {
        var types = [];
        this.state.vehicles.forEach(function (item) {
            var foo = item.release_year;
            var bar = '' + foo;
            if (bar === ev.currentTarget.value && localStorage.getItem("carbrand") == item.brand.id && localStorage.getItem("cartype") == item.type && localStorage.getItem("carmodel") == item.model) {
                types.push(item.usable_battery_size);
                localStorage.setItem('carrelease_year', bar);
            }
        })

        var batterysize = types.filter(function (el, index, arr) {
            return index === types.indexOf(el);
        });

        $("#battery_size").html("").removeClass("disabled").attr("disabled", false);
        $("#battery_size").append('<option value="no" selected="selected">Choose an option</option>');
        batterysize.forEach(function (item) {
            $("#battery_size").append(
                "<option value=" + item + ">" + item + "</option>"
            )
        })
    }
    chooseAddNewVehicle(ev) {//?
        this.state.vehicles.forEach(function (item) {
            var foo = item.usable_battery_size;
            var bar = '' + foo;
            if (bar === ev.currentTarget.value && localStorage.getItem("carbrand") == item.brand.id && localStorage.getItem("cartype") == item.type && localStorage.getItem("carmodel") == item.model && localStorage.getItem("carrelease_year") == item.release_year) {
                localStorage.setItem('carusable_battery_size', bar);
                localStorage.setItem('carid', item.id);
            }
        })
    }


}

export default AddNewVehicle;