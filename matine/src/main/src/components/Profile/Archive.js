import React, {Component} from 'react';
import { Table, ButtonGroup} from 'react-bootstrap';
import Button from '@material-ui/core/Button';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link } from 'react-router-dom';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardContent from '@material-ui/core/CardContent';


export default class Archive extends Component{

	constructor(props) {
	    super(props);
	    this.state = {contents: [], isLoading: true};
	  }	  

	componentDidMount() {
		this.findAllContents();
	};

	findAllContents(){
	  fetch("http://localhost:8080/matine/profile/archive/" + localStorage.getItem("id"), {
	    method: "GET",
	    headers: {
	      "Content-Type": "application/json",
	      "Accept": "application/json",
		  
		  "Access-Control-Allow-Origin" : "*", 
		  "Access-Control-Allow-Credentials" : true 
	    },
        params: {user_id: localStorage.getItem("id")} ,

	  }).then((response) => {
	    response.json().then((data) => {
	      this.setState({ contents: data, isLoading: false });
	    });
	  });	
	}
	refreshPage() {
        window.location.reload(false);
      }
	removeFromArchive(content){
		fetch("http://localhost:8080/matine/profile/archive/" + localStorage.getItem("id") , {
		  method: "DELETE",
		  headers: {
			"Content-Type": "application/json",
			"Accept": "application/json",
			"Access-Control-Allow-Origin" : "*", 
			"Access-Control-Allow-Credentials" : true 

		  },
          
		  params: {userId: localStorage.getItem("id")} ,
		  body: JSON.stringify({
              archiveId : content.archiveId,
			  contentId: content.contentId,
			  userId: localStorage.getItem("id")
			  
		  })
		}).then(() => {
				this.setState({"show": true, "method": "put"});
				setTimeout(() => this.setState({"show": false}),3000);
				this.refreshPage();

			}
		)
	  };


	render() {
		const {isLoading} = this.state;

	    if (isLoading) {
	      return <p>Loading...</p>;
	    }

		return(
			<div style={{
				width: 1000,
				paddingTop: 50
			  }}>
				

				<Card>
      <CardActionArea>
	 <h3 style={{ 
				justifyContent: "center"
			  }}>Arşivim</h3>
        <CardContent>
			
							<Table bordered hover striped variant="light">
								<thead>
									<tr>
										<th>İçerik Adı</th>
										<th>Açıklaması</th>
										<th>Actions</th>
									</tr>
								</thead>

								<tbody>
								{this.state.contents.length === 0 ?
								
									<tr align="center">
										<td colSpan="2">Hiç İçerik Yok</td>
									</tr> : 
									this.state.contents.map((content) =>(

										<tr key={content.contentId}>
											<td style={{textTransform: "capitalize"}}>{content.contentName}</td>
											<td style={{textTransform: "capitalize"}}>{content.contentDescription}</td>
											<td>				
											
											<ButtonGroup><Button onClick = {() => this.removeFromArchive(content) }type="submit" className="btn btn-outline-secondary">Arşivden Çıkar</Button>
											<Button className=" btn btn-outline-secondary "><Link className="btn  " to={{pathname:"/content/" + content.contentName, state: {content :content}}}>  İçerik Sayfası </Link></Button></ButtonGroup>
											</td>

										</tr>
										))
									}
								</tbody>
							</Table>
                            <Button  type="submit" className="btn btn-outline-secondary btn-light btn-lg btn-block"><Link to="/userProfile" className="btn btn-light btn-lg btn-block">PROFİL</Link></Button>

						</CardContent>
						</CardActionArea>
				</Card>
			</div>
			)
	}
}
