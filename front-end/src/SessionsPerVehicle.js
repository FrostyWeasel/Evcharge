import React from 'react';
import axios from 'axios';
import $ from 'jquery';
import './MyVehicles.css';


class SessionsPerVehicle extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          data: [], Cars: []
        };
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
                this.setState({Cars: initial});
              $(this.refs.main).DataTable({
                // dom: '<"data-table-wrapper"t>',
                data: data,
                columns:[
                  {
                    title: 'Brand',
                    width: "10%",
                    data: 'brand.name'
                },
                {
                    title: 'Type',
                    width: "10%",
                    data: 'type'
                },
                {
                  title: 'Model',
                  width: "10%" ,
                  data: 'model'
              },
                {
                  title: 'Release year',
                  width: "10%" ,
                  data: 'release_year'
              },
              {
                title: 'Battery size',
                width: "10%" ,
                data: 'usable_battery_size'
            },


              
              {
                title: 'From',
                width: "15%",
                data: 'id',
                'render' : function(id){
                  return(
                  '<input class="FromBtn" className="btk"  type="text" name="FromBtn" placeholder="yyyymmdd" required/>'
                  )}
              },
              
                  {
                    title: 'Action',
                    width: "15%",
                    data: 'id',
                    'render' : function(id){
                      return(
                      '<input class="ToBtn" className="btk" name="toBtn" placeholder="yyyymmdd" type="text" required/>'
                      )}
                  },
              
            {
              title: 'Action',
              width: "17,5%",
              data: 'id',
              'render' : function(id){
                return(
                '<a className="btk" class="SessionsBtn" id="'+ id + '" ><i type="button">See sessions</i></a>'
                )}
          }

            ],
                ordering: false
             });
             $(".FromBtn").on('text',function(ev){
             localStorage.setItem('dateFrom',)
            })
             $(".SessionsBtn").on('click',function(ev){
              const requestOptions = {
                method: 'GET',
                headers: { 
                  'Content-Type': 'application/json', 
                  'X-OBSERVATORY-AUTH': localStorage.getItem("token") 
              }
              }
               fetch('//localhost:8765/evcharge/api/SessionsPerEV/'+ ev.currentTarget.id+ '/:' + localStorage.getItem("dateFrom")+ '_from/:'+ localStorage.getItem("dateTo")+ '_to/:', requestOptions)
                    .then((response) => {
                        return response.json();
                      })
                      .then((data) => {
                        $(this.refs.main).DataTable({
                          data: data,
                          columns:[
                            {
                              title: 'The date/time of the call',
                              width: "7%",
                              data: 'RequestTimestamp'
                          },
                          {
                              title: 'Period from',
                              width: "7%",
                              data: 'PeriodFrom'
                          },
                          {
                            title: 'Period to',
                            width: "7%" ,
                            data: 'PeriodTo'
                        },
                          {
                            title: 'Total energy consumed',
                            width: "7%" ,
                            data: 'TotalEnergyConsumed'
                        },
                        {
                          title: 'Number of visited points',
                          width: "7%" ,
                          data: 'NumberOfVisitedPoints'
                      }, 
                      {
                        title: 'Number of vehicle charging sessions',
                        width: "7%" ,
                        data: 'NumberOfVehicleChargingSessions'
                    },
                    {
                      title: 'Vehicle charging sessions',
                      width: "7%" ,
                      data: 'VehicleChargingSessionsList:'
                  }, 
                  {
                    title: 'Session index',
                    width: "7%" ,
                    data: 'SessionIndex'
                },
                {
                  title: 'Energy provider',
                  width: "7%" ,
                  data: 'EnergyProvider'
              },
              {
                title: 'Started on',
                width: "7%" ,
                data: 'StartedOn'
            },
            {
              title: 'Finished on',
              width: "7%" ,
              data: 'FinishedOn'
          },
          {
            title: 'Εnergy delivered',
            width: "7%" ,
            data: 'ΕnergyDelivered'
        },
        {
          title: 'Price policy',
          width: "7%" ,
          data: 'PricePolicyRef'
      },
      {
        title: 'Cost per KWh',
        width: "7%" ,
        data: 'CostPerKWh'
    },
    {
      title: 'Session cost',
      width: "7%" ,
      data: 'SessionCost'
  }
                      ],
                      ordering: false
                       });
                })
                .catch(error => {
                  console.error(error);
                })
             })
            })
            .catch(error => {
              console.error(error);
            })
        }
    render() {
        return (
            <html>
            <body className="stations-body">
              <meta charSet="UTF-8" />
              <title>Stations</title>
            <div>
                <h2>Stations</h2>

                <table ref="main" />
                
            </div>
            </body>
            </html>
        )
    }
}

export default SessionsPerVehicle;