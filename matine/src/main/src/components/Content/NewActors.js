import React , {Component} from 'react';
import {Card, Form, Button, Col} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link } from 'react-router-dom';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {  faPlusSquare } from '@fortawesome/free-solid-svg-icons';
import NewToast from "../NewToast"

export default class NewActors extends Component{

	constructor(props){
		super(props);
		this.state = this.initialState;
		this.state = {contentId: this.props.location.state.content.contentId, errorMessage : false, error:""};

        
	}

	initialState = {
		 actorId: "", actorRole:"", actorName:""
	}

	
	
	

	handleSubmit = e => {
		e.preventDefault();
		this.postData(e);
		this.setState(this.initialState);
	  };

	postData(){
	  fetch("http://localhost:8080/matine/actors/" + (this.state.contentId)  , {
	    method: "POST",
	    headers: {
	      "Content-Type": "application/json",
	      "Accept": "application/json",
	    },
		params: {contentId: this.state.contentId} ,
	    body: JSON.stringify({
	    	contentId: this.state.contentId,
            actorName: this.state.actorName,
            actorRole: this.state.actorRole

	    })

	  }).then((response) => {
		if(response.status === 200){
			this.setState({
				errorMessage : true,
				error : "Oyuncu eklendi!"
			  })
		  window.location.reload(false);
		}else{
		  response.json().then((responseN) => {
			this.setState({
			  errorMessage : true,
			  error :  responseN.message
			})
		})
		}
	})
	}

	

	
	actorChange = event =>{
		this.setState({
			[event.target.name] : event.target.value
		})
	}

	render() 
	{
		const {actorName, actorRole} = this.state;

		return(
		<div style={{
			paddingTop:50,
			width: 1000 
		}}>
						<NewToast show = {this.state.errorMessage} message={this.state.error}  type= {"danger"} />


			<Card className ={"border border-light bg-light text-black"}>
				<Card.Header>
				<FontAwesomeIcon icon={ faPlusSquare} /> {"Yeni Oyuncu Ekle"} 
				</Card.Header>
					<Form onSubmit={this.handleSubmit} id="genreFormId">
						<Card.Body>						
							<Form.Row>
										
									<Form.Group as={Col} controlId="formGridName">
									    <Form.Label>Oyuncu</Form.Label>
									    <Form.Control required autoComplete="off"
									    	type="text" 
									    	value ={actorName}
									    	onChange={this.actorChange}
									    	placeholder="Oyuncu Adı"
									    	name ="actorName" />
									</Form.Group>
									<Form.Group as={Col} controlId="formGridDesc">
									    <Form.Label>Rol</Form.Label>
									    <Form.Control required autoComplete="off"
									    	type="text" 
									    	value ={actorRole}
									    	onChange={this.actorChange}
									    	placeholder="Rol"
									    	name ="actorRole" />
									</Form.Group>
								</Form.Row>					
							</Card.Body>
							<Card.Footer style={{"textAlign":"right"}}>
								<Button variant="primary" type="submit">

									    Kaydet
								</Button>
									 
							</Card.Footer>
						</Form>
				</Card>
			</div>
			)
	}
}