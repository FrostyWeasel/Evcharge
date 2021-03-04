import React from 'react';
import axios from 'axios';
import $ from 'jquery';
import './History.css';

$.DataTable = require('datatables.net');

class History extends React.Component {
    constructor(props) {
        super(props);
        this.state = { user: props.user };
    }componentDidMount() {
        this.data = [{name: "aaa", location: "what"}, {name: "bbb", location: "hey"}];
        
            $(this.refs.main).DataTable({
                // dom: '<"data-table-wrapper"t>',
                data: this.data,
                columns:[
                    {
                    title: 'Name',
                    width: 120,
                    data: 'name'
                },
                {
                    title: 'Location',
                    width: 180,
                    data: 'location'
                }
            ],
                ordering: false
             });
        }
    
        render() {
            return (
                <html>
                <body className="stations-body">
                  <meta charSet="UTF-8" />
                  <title>Chargings history</title>
                <div>
                    <h2>Chargings history</h2>
    
                    <table ref="main" />
                    
                </div>
                </body>
                </html>
                )         
    }
    
    }
export default History;