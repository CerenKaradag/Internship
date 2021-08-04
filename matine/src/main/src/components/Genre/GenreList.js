import React, {Component} from 'react';
import {Card, Table, ButtonGroup} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faList, faInfo} from "@fortawesome/free-solid-svg-icons";
import { Link } from 'react-router-dom';
import Typography from '@material-ui/core/Typography';

export default class GenreList extends Component{

	constructor(props) {
	    super(props);
	    this.state = {genres: [], isLoading: true, searchField:""};
	    
	  }	  

	componentDidMount() {
		this.findAllGenres();
	};

	findAllGenres(){
	  fetch("http://localhost:8080/matine/genre", {
	    method: "GET",
	    headers: {
	      "Content-Type": "application/json",
	      "Accept": "application/json",
	    },
	  }).then((response) => {
	    response.json().then((data) => {
	      this.setState({ genres: data, isLoading: false });
	    });
	  });	
	}
	
	


	render() {
		const {isLoading, genres, searchField} = this.state;
		

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
                        <Typography component="h4" variant="h4" align="center" color="Black" gutterBottom>Türler
                            </Typography>
                          
                        </Card.Header>
						<Card.Body>
							<Table bordered hover striped variant="light">
								<thead>
									<tr>
										<th>Tür </th>
										<th>Detaylar </th>

									</tr>
								</thead>

								<tbody>
								{this.state.genres.length === 0 ?
								
									<tr align="center">
										<td colSpan="2"> Tür Yok</td>
									</tr> : 
									
									this.state.genres.map((genre) =>(

										<tr key={genre.id}>
											<td style={{textTransform: "capitalize"}}>{genre.name}</td>
											<td>
											<ButtonGroup>
														<Link to={{pathname:"genre/" + genre.name, state: genre}} className="btn btn-sm btn-outline-secondary"><FontAwesomeIcon icon={faInfo}/> </Link>
													</ButtonGroup>
													</td>
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
