import React, { Component } from 'react';
import { Link} from 'react-router-dom';
import {Form,  Col} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';

import Button from '@material-ui/core/Button';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faUser} from '@fortawesome/free-solid-svg-icons';

class EditProfile extends Component {

  
	constructor(props){
		super(props);
        this.state = this.initialState;  
        this.userChange = this.userChange.bind(this);
	}
		

	initialState = {
		id:"",lastName:'', firstName:'', userName:'', userRole: '', email: '', dateOfBirth:'',password:'',
		
	}
    refreshPage() {
        window.location.reload(false);
      }
	componentDidMount() {
		const userId = localStorage.getItem("id");

		if(userId){
			this.findUser(userId);
		}};
    findUser(userId){
        fetch("http://localhost:8080/matine/profile/" + (userId) , {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
          },
        }).then((response) => {
			response.json().then((data) => {
				this.setState({ 
					id: data.id,
					firstName: data.firstName,
					lastName: data.lastName,
					userName: data.userName,
					password: data.password,
					dateOfBirth: data.dateOfBirth,
					email: data.email
			   });
			  })
			}).catch((error) => {
				console.error("Error - "+error);
			});
      }
	
      handleUpdate = e => {
        e.preventDefault();
        this.updateData(e);
        }
    
        updateData(){

        fetch("http://localhost:8080/matine/profile/edit/" + (localStorage.getItem("id")), {
          method: "PUT",
          headers: {
          "Content-Type": "application/json",
          "Accept": "application/json",
          },
          params: {userId: localStorage.getItem("id")} ,
          body: JSON.stringify({
            id: this.state.id,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            userName: this.state.userName,
			password: this.state.password,
			email: this.state.email,
			dateOfBirth: this.state.dateOfBirth
           
          })
         

        }).then(response => {
          if(response.status === 200){
              
              console.log(response)
			this.setState({"show": true, "method": "put"});
				setTimeout(() => this.setState({"show": false}),3000); 
                this.refreshPage();

		  }
		  else{
			response.json().then((responseN) => {
				console.log(responseN.message)
			})
		  }
          
          }
        )
        };
    
      userChange = event =>{
        this.setState({
          [event.target.name] : event.target.value
        })
      };
	

	
    
	

  render() {

		const {firstName, lastName,userName,dateOfBirth,password,email} = this.state;
        

    return (
<div style={{
				width: 1000,
				paddingTop: 50
			  }}>
			

			<Card >
				
					<Form onSubmit={ this.handleUpdate} >
					<h3 style={{color: 'black'}}><FontAwesomeIcon icon={faUser}/> Profil Düzenle </h3>

					<CardActionArea>
					<CardContent>

							<Form.Row>
									<Form.Group as={Col} controlId="formGridName">
									    <Form.Label>İsim</Form.Label>
									    <Form.Control required autoComplete="off"
									    	type="text" 
									    	value ={firstName}
									    	onChange={this.userChange}
									    	placeholder="Description"

									    	name ="firstName"
											/>
									</Form.Group>	
									
									<Form.Group as={Col} controlId="formGridDesc">
									    <Form.Label>Soyisim</Form.Label>
									    <Form.Control required autoComplete="off"
									    	type="text" 
									    	value ={lastName}
									    	onChange={this.userChange}
									    	placeholder
									    	name ="lastName" />
									</Form.Group>
                  </Form.Row>
                  <Form.Row>
                  <Form.Group as={Col} controlId="formGridName">
									    <Form.Label>Kullanıcı Adı</Form.Label>
									    <Form.Control required autoComplete="off"
									    	type="text" 
									    	value ={userName}
									    	onChange={this.userChange}
									    	placeholder

									    	name ="userName"
											/>
									</Form.Group>	
									
									<Form.Group as={Col} controlId="formGridDesc">
									    <Form.Label>Doğum Tarihi</Form.Label>
									    <Form.Control autoComplete="off"
									    	type="text" 
									    	value ={dateOfBirth}
									    	onChange={this.userChange}
									    	placeholder
									    	name ="dateOfBirth" />
									</Form.Group>
								</Form.Row>		
                <Form.Row>
                  <Form.Group as={Col} controlId="formGridName">
									    <Form.Label>Email</Form.Label>
									    <Form.Control required autoComplete="off"
									    	type="email" 
									    	value ={email}
									    	onChange={this.userChange}
									    	placeholder
									    	name ="email"
											/>
									</Form.Group>	
									
									<Form.Group as={Col} controlId="formGridDesc">
									    <Form.Label>Şifre</Form.Label>
									    <Form.Control required autoComplete="off"
									    	type="password" 
									    	value ={password}
									    	onChange={this.userChange}
									    	placeholder
									    	name ="password" />
									</Form.Group>
								</Form.Row>		
								</CardContent>
		
							</CardActionArea>	  

							<CardActions>
                            <Button type="submit" className="btn btn-dark btn-lg btn-block" >KAYDET</Button>

                            <Button type="submit" className="btn btn-dark btn-lg btn-block" > <Link to={{pathname:"/userProfile" }}> İPTAL  </Link></Button>
		   
      </CardActions>
						</Form>
				</Card>
			</div>
	)
  }
}

export default EditProfile;