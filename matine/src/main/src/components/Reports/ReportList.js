import React, {Component} from 'react';

import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';

import 'bootstrap/dist/css/bootstrap.min.css';



export default class ReportList extends Component{

	constructor(props) {
	    super(props);
	    this.state = {reports: [], comments: [],isLoading: true};
	  }	  

	componentDidMount() {
		this.findAllReports();
		this.findAllReportedComments();
	};
    refreshPage() {
        window.location.reload(false);
      }

	  findAllReportedComments(){
		fetch("http://localhost:8080/matine/report/comments", {
		  method: "GET",
		  headers: {
			"Content-Type": "application/json",
			"Accept": "application/json",
		  },
		}).then((response) => {
		  response.json().then((data) => {
			this.setState({ comments: data, isLoading: false });
  
		  });
		});	
	  }
	findAllReports(){
	  fetch("http://localhost:8080/matine/reports", {
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
	
	
    

	  warnUser(report){
		fetch("http://localhost:8080/matine/warned_users" , {
		  method: "POST",
		  headers: {
			"Content-Type": "application/json",
			"Accept": "application/json",
		  },
          params: {user_id : localStorage.getItem("id")}, 

		  body: JSON.stringify({
			  reportId : report.reportId,
              reportedId : report.reportedId,
              reportedUserName : report.reportedUserName,
              reportingId  :report.reportingId,
              reportDescription : report.reportDescription
		  })
		}).then(() => {

				this.setState({"show": true, "method": "put"});
				setTimeout(() => this.setState({"show": false}),3000);
				this.refreshPage();

			}
		)
	  };

	  removeComment(report){
		fetch("http://localhost:8080/matine/report/" + (report.contentId), {
		  method: "DELETE",
		  headers: {
			"Content-Type": "application/json",
			"Accept": "application/json",
		  },
          
		  params: {commentId : report.commentId}, 
		  body: JSON.stringify({
              commentId : report.commentId
			  
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
	    if (isLoading) {
	      return <p>Loading...</p>;
	    }

		return(

			
			<div style={{
				paddingTop:50,
				width: 1000 
			}}>
				<Card className ={"border border-light bg-light text-black"}>
					<Card.Header > 
					Bildirili Üyeler
					</Card.Header>
						<Card.Body>
							<Table bordered hover striped variant="light">
								<thead>
									<tr>
										<th>Kullanıcı Adı</th>
										<th>Bildiri Sebebi</th>
										<th>Uyarı</th>
									</tr>
								</thead>

								<tbody>
								{this.state.reports.length === 0 ?
								
									<tr align="center">
										<td colSpan="2"> Bildirili Kullanıcı Yok.</td>
									</tr> : 
									
									this.state.reports.map((report) =>(

										<tr key={report.id}>
											<td style={{textTransform: "capitalize"}}>{report.reportedUserName}</td>
											<td style={{textTransform: "capitalize"}}>{report.reportDescription}</td>
                                            <td><Button variant="outline-secondary" onClick={() => this.warnUser(report) }  >UYAR</Button></td>
									</tr>
										))
									}
								</tbody>
							</Table>
						</Card.Body>
				</Card>

<h3></h3>
				<Card className ={"border border-light bg-light text-black"}>
					<Card.Header > 
					Bildirili Yorumlar
					</Card.Header>
						<Card.Body>
							<Table bordered hover striped variant="light">
								<thead>
									<tr>
										<th>Yorum içeriği</th>
									
										<th>İşlemler</th>
									</tr>
								</thead>

								<tbody>
								{this.state.comments.length === 0 ?
								
									<tr align="center">
										<td colSpan="2"> Bildirili Yorum Yok.</td>
									</tr> : 
									
									this.state.comments.map((comment) =>(
											
										<tr key={comment.id}>
											<td style={{textTransform: "capitalize"}}>{comment.commentDescription}</td>
											{console.log(comment)}
                                            <td><Button variant="outline-secondary" onClick={() => this.removeComment(comment) }  >Yorumu Sil</Button></td>
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
