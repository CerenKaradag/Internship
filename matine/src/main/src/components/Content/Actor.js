import React, {Component} from 'react';
import { Table, ButtonGroup} from 'react-bootstrap';
import Button from '@material-ui/core/Button';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link } from 'react-router-dom';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardContent from '@material-ui/core/CardContent';


export default class Actor extends Component{

	constructor(props) {
	    super(props);
	    this.state = {actors: [],content:[], isLoading: true};
        this.state.content = this.props.location.state.content;

	  }	  

	componentDidMount() {
		this.findAllActors();
	};

	findAllActors(){
	  fetch("http://localhost:8080/matine/actors/" + (this.state.content.contentId), {
	    method: "GET",
	    headers: {
	      "Content-Type": "application/json",
	      "Accept": "application/json",
		  
		  "Access-Control-Allow-Origin" : "*", 
		  "Access-Control-Allow-Credentials" : true 
	    },
        params: { contentId: this.state.content.contentId}, 

	  }).then((response) => {
	    response.json().then((data) => {
	      this.setState({ actors: data, isLoading: false });
	    });
	  });	
	}
	refreshPage() {
        window.location.reload(false);
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
			  }}> Oyuncular </h3>
        <CardContent>
			
							<Table bordered hover striped variant="light">
								<thead>
									<tr>
										<th>Oyuncu </th>
										<th>Rol</th>
									</tr>
								</thead>

								<tbody>
								{this.state.actors.length === 0 ?
								
									<tr align="center">
										<td colSpan="2">Oyuncu Bilgisi Bulunmamaktadır.</td>
									</tr> : 
									this.state.actors.map((actor) =>(

										<tr key={actor.actorId}>
											<td style={{textTransform: "capitalize"}}>{actor.actorName}</td>
											<td style={{textTransform: "capitalize"}}>{actor.actorRole}</td>
											

										</tr>
										))
									}
								</tbody>
							</Table>
                            <Button  type="submit" className="btn btn-outline-secondary btn-light btn-lg btn-block"><Link className="btn-light btn-lg" to={{pathname:"/content/" + this.state.content.contentName, state: {content :this.state.content}}}>İÇERİK SAYFASI</Link></Button>

						</CardContent>
						</CardActionArea>
				</Card>
			</div>
			)
	}
}
