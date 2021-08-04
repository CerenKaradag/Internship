import React, {Component} from 'react';

import { Table, ButtonGroup, Button} from 'react-bootstrap';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import 'bootstrap/dist/css/bootstrap.min.css';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faEdit,faTrash,faList, faInfo} from "@fortawesome/free-solid-svg-icons";
import { Link } from 'react-router-dom';
import SearchBox from "./SearchBox";
import { CardHeader } from '@material-ui/core';

export default class Search extends Component{

	constructor(props) {
	    super(props);
	    this.state = {contents: [], isLoading: true, searchField:""};
	  }	  

	componentDidMount() {
		this.findAllContents();
	};

	findAllContents(){
	  fetch("http://localhost:8080/matine/contents", {
	    method: "GET",
	    headers: {
	      "Content-Type": "application/json",
	      "Accept": "application/json",
	    },
	  }).then((response) => {
	    response.json().then((data) => {
	      this.setState({ contents: data, isLoading: false });
	    });
	  });	
	}
	



	render() {
		const {isLoading, contents, searchField} = this.state;
		const filtered = contents.filter( content => {
				return content.contentName.includes(searchField)
		}
		)

	    if (isLoading) {
	      return <p>Loading...</p>;
	    }

		return(

			
			<div
			style={{
				paddingTop:50,
				width: 1000 
			}}>
			


			<Card>
			<CardHeader>			<h3>Arama</h3></CardHeader>
						<h3><SearchBox placeholder="İçerik Adı  ..."  handleChange={(e) => this.setState({searchField:e.target.value})}/></h3>

      <CardActionArea>
		  
		  <CardContent>

			  <Table bordered hover striped variant="light">
						<thead>
									<tr>
										<th> İçerik</th>
									</tr>
								</thead>
								<tbody>
							{ filtered.map(content => <tr key={content.id}>
											<td  style={{textTransform: "capitalize"}}>{content.contentName}   </td>
											<Button  variant="contained" ><Link className="btn-light btn-lg" to={{pathname:"/content/" + content.contentName, state: {content :content}}}> İçerik Sayfası </Link></Button>

										</tr>)}
										</tbody>  </Table>
						</CardContent>
						</CardActionArea>
				</Card>
			</div>
			)
	}
}
