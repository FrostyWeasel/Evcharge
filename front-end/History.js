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
        <div></div>
        )
    }
}

export default History;