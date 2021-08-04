import React, {Component} from 'react';

import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';

import 'bootstrap/dist/css/bootstrap.min.css';



export default class WarnedList extends Component{

	constructor(props) {
	    super(props);
	    this.state = {reports: [], isLoading: true};
	  }	  

	componentDidMount() {
		this.findAllReports();
	};
    refreshPage() {
        window.location.reload(false);
      }

	findAllReports(){
	  fetch("http://localhost:8080/matine/warned_users", {
	    method: "GET",
	    headers: {
	      "Content-Type": "application/json",
	      "Accept": "application/json",
	    },
	  }).then((response) => {
	    response.json().then((data) => {
	      this.setState({ reports: data, isLoading: false });

	    });
	  });	
	}
	
	
    

	  unwarnUser(report){
		fetch("http://localhost:8080/matine/users" , {
		  method: "PUT",
		  headers: {
			"Content-Type": "application/json",
			"Accept": "application/json",
		  },
         
		  body: JSON.stringify({
            id : report.id,
            userName: report.userName,
            firstName: report.firstName,
            lastName: report.lastName,
            email: report.email,
            password: report.password,
            dateOfBirth: report.dateOfBirth,
            isWarned: false
              
			  
		  })
		}).then(() => {

				this.setState({"show": true, "method": "put"});
				setTimeout(() => this.setState({"show": false}),3000);
				this.refreshPage();
			}
		)
	  };

      deleteUser(report){
		fetch("http://localhost:8080/matine/users" , {
		  method: "DELETE",
		  headers: {
			"Content-Type": "application/json",
			"Accept": "application/json",
		  },
          

		  body: JSON.stringify({
              id : report.id,
              userName: report.userName,
              firstName: report.firstName,
              lastName: report.lastName,
              email: report.email,
              password: report.password,
              dateOfBirth: report.dateOfBirth
			  
		  })
		}).then(() => {

				this.setState({"show": true, "method": "put"});
				setTimeout(() => this.setState({"show": false}),3000);
				this.refreshPage();

			}
		)
	  };

	render() {
		const {isLoading, reports, searchField} = this.state;
console.log(this.state.reports)
	    if (isLoading) {
	      return <p>Loading...</p>;
	    }

		return(

			
			<div style={{
				paddingTop:200,
				width: 1000 
			}}>
				<Card className ={"border border-light bg-light text-black"}>
					<Card.Header > 
					Uyarılı Üyeler
					</Card.Header>
						<Card.Body>
							<Table bordered hover striped variant="light">
								<thead>
									<tr>
										<th>Kullanıcı Adı</th>
                                        <th>İşlemler</th>


									</tr>
								</thead>

								<tbody>
								{this.state.reports.length === 0 ?
								
									<tr align="center">
										<td colSpan="2">Uyarılı Kullanıcı Yok.</td>
									</tr> : 
									
									this.state.reports.map((report) =>(

										<tr key={report.id}>
											<td style={{textTransform: "capitalize"}}>{report.userName}</td>

                                            <td><Button variant="outline-secondary" onClick={() => this.unwarnUser(report) } > UYAR</Button>
                                            <Button variant="outline-secondary" onClick={() => this.deleteUser(report) } > KULLANICIYI SİL</Button></td>

                                    </tr>
										))
									}
								</tbody>
							</Table>
						</Card.Body>
				</Card>
			</div>
			)
	}
}
