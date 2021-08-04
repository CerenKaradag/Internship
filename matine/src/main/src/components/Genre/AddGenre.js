import React , {Component} from 'react';
import {Card, Form, Button, Col} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSave,faPlusSquare} from "@fortawesome/free-solid-svg-icons";
import NewToast from "../NewToast"

export default class Genre extends Component{

    constructor(props){
		super(props);
		this.state = this.initialState;
        this.genreChange = this.genreChange.bind(this);
		this.state = { errorMessage : false, error:""};

		
	}
    initialState = {
		id:"", name:''
	};

    genreChange = event =>{
		this.setState({
			[event.target.name] : event.target.value
		})
	};

    handleSubmit = e => {
		e.preventDefault();
		this.postData(e);
		this.setState(this.initialState);
	  };

	postData(){
		fetch("http://localhost:8080/matine/genre", {
		  method: "POST",
		  headers: {
			"Content-Type": "application/json",
			"Accept": "application/json",
		  },
		  body: JSON.stringify({
			  name: this.state.name
		  })
		}).then((response) => {
			if(response.status === 200){
				this.setState({
					errorMessage : true,
					error : "Tür eklendi!"
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
	  };
	render() 
	{
		const {name} = this.state;

		return(
			<div style={{
				paddingTop:50,
				width: 1000 
			}}>
							<NewToast show = {this.state.errorMessage} message={this.state.error}  type= {"danger"} />

				<Card className ={"border border-light bg-light text-black"}>
				<Card.Header>
				<FontAwesomeIcon icon={ faPlusSquare} /> {"Yeni Tür Ekle"} 
				</Card.Header>
				<Form onSubmit={ this.handleSubmit} id="genreFormId">
						<Card.Body>						
							<Form.Row>
									<Form.Group as={Col} controlId="formGridName">
									    <Form.Label>Tür İsmi</Form.Label>
									    <Form.Control required autoComplete="off"
									    	type="text" 

											className = {"bg-light text-black"}
									    	value ={name}
									    	onChange={this.genreChange}
									    	placeholder="Tür İsmi Giriniz"
									    	name ="name" />
									</Form.Group>
									

								</Form.Row>					
							</Card.Body>
                            <Card.Footer style={{"textAlign":"right"}}>
								<Button variant="success" type="submit">
									<FontAwesomeIcon icon={faSave} /> Kaydet
								</Button>
								
							</Card.Footer>
					</Form>
				</Card>
			</div>
		)
	}

}
