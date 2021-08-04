import React , {Component} from 'react';
import {Card, Form, Button, Col} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link } from 'react-router-dom';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {  faPlusSquare } from '@fortawesome/free-solid-svg-icons';
import NewToast from "../NewToast"


export default class AddContent extends Component{

	constructor(props){
		super(props);
		this.state = this.initialState;
		this.contentChange = this.contentChange.bind(this);
		this.state = { errorMessage : false, error:""};

	}

	initialState = {
		contentId: "", genreId: "" , contentName:'', genre:"", contentDescription:'', contentName:''
	}

	
	
	

	handleSubmit = e => {
		e.preventDefault();
		this.postData(e);
		this.setState(this.initialState);
	  };

	postData(){
	  fetch("http://localhost:8080/matine/" + (this.state.name)+ "/content" , {
	    method: "POST",
	    headers: {
	      "Content-Type": "application/json",
	      "Accept": "application/json",
	    },
		params: {genreId: this.state.genreId} ,
	    body: JSON.stringify({
	    	contentName: this.state.contentName,
	    	genre: this.state.genre,
	    	contentDescription: this.state.contentDescription
	    })

	  }).then((response) => {
		if(response.status === 200){
			this.setState({
				errorMessage : true,
				error : "İçerik eklendi!"
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

	

	genrePage = () => {
		return this.props.history.push({pathname: "/genre/" + this.props.location.state.name, state: this.props.location.state});
	};

	contentChange = event =>{
		this.setState({
			[event.target.name] : event.target.value
		})
	}

	render() 
	{
		const {genre,contentName, contentDescription} = this.state;

		return(
		<div style={{
			paddingTop:50,
			width: 1000 
		}}>
			
			<NewToast show = {this.state.errorMessage} message={this.state.error}  type= {"danger"} />

			<Card className ={"border border-light bg-light text-black"}>
				<Card.Header>
				<FontAwesomeIcon icon={ faPlusSquare} /> {"Yeni İçerik Ekle"} 
				</Card.Header>
					<Form onSubmit={this.handleSubmit} id="genreFormId">
						<Card.Body>						
							<Form.Row>
									<Form.Group as={Col} controlId="formGridName">
									    <Form.Label>Tür</Form.Label>
									    <Form.Control required autoComplete="off"
									    	type="text" 
									    	value ={genre}
									    	onChange={this.contentChange}
                                            placeholder="Tür Adı"
									    	name ="genre"
											 />
									</Form.Group>	
									<Form.Group as={Col} controlId="formGridName">
									    <Form.Label>İçerik</Form.Label>
									    <Form.Control required autoComplete="off"
									    	type="text" 
									    	value ={contentName}
									    	onChange={this.contentChange}
									    	placeholder="İçerik Adı"
									    	name ="contentName" />
									</Form.Group>
									<Form.Group as={Col} controlId="formGridDesc">
									    <Form.Label>Açıklama</Form.Label>
									    <Form.Control required autoComplete="off"
									    	type="text" 
									    	value ={contentDescription}
									    	onChange={this.contentChange}
									    	placeholder="Açıklama"
									    	name ="contentDescription" />
									</Form.Group>
								</Form.Row>					
							</Card.Body>
							<Card.Footer style={{"textAlign":"right"}}>
								<Button variant="outline-primary" type="submit">
								  Kaydet
								</Button>
								<Button variant="outline-primary">
									 <Link to={{pathname:"/genreList"}}>  İçerik Listesi </Link></Button>
							</Card.Footer>
						</Form>
				</Card>
			</div>
			)
	}
}