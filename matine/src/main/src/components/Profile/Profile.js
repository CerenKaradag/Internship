import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { FormGroup} from 'react-bootstrap';
import { Form,  Col} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faUser} from '@fortawesome/free-solid-svg-icons'
class Profile extends Component {

   
	constructor(props){
		super(props);
        this.state = this.initialState;  

	}
		

	initialState = {
		id:"",lastName:'', firstName:'', userName:'', userRole: '', email: '', dateOfBirth:''
		
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
					userRole: data.userRole,
					dateOfBirth: data.dateOfBirth,
					email: data.email
			   });
			  })
			}).catch((error) => {
				console.error("Error - "+error);
			});
      }
	
    
	

  render() {

    const {user, isLoading} = this.state;
        

    return (

		<div style={{
			width: 1000,
			paddingTop: 50
		  }}>
	    <Card>
      <CardActionArea>
	 
        <CardContent>
          
       
					
						
		<Form>
			<h3 style={{color: 'black'}}><FontAwesomeIcon icon={faUser}/> Profil </h3>
		<Form.Row>
			<FormGroup as={Col} controlId="formGridName">
		
					<div style={{display: 'flex', justifyContent: 'center'}}  className="form-group">
						
					<div style={{display: 'flex', justifyContent: 'center'}} textAlign="center" style={{color: 'black'}}>İsim : {this.state.firstName}</div>

					</div>

					
			</FormGroup>
			<FormGroup as={Col} controlId="formGridName">
     <div style={{display: 'flex', justifyContent: 'center'}} textAlign="center" className="form-group">

       <label htmlFor="lastName" style={{color: 'black'}}>Soyisim : {this.state.lastName}</label>
	</div>
	</FormGroup>
		</Form.Row>
		<Form.Row>
		<FormGroup as={Col} controlId="formGridName">
			<div style={{display: 'flex', justifyContent: 'center'}} textAlign="center" className="form-group">

       <label style={{display: 'flex', justifyContent: 'center'}} textAlign="center" htmlFor="lastName" style={{color: 'black'}}>Kullanıcı Adı : {this.state.userName}</label>
       
	</div>
		</FormGroup>
			<FormGroup as={Col} controlId="formGridName">
				<div style={{display: 'flex', justifyContent: 'center'}} textAlign="center" className="form-group">

       <label style={{display: 'flex', justifyContent: 'center'}} textAlign="center" htmlFor="lastName" style={{color: 'black'}}>Kullanıcı Durumu : {this.state.userRole}</label>
       
	</div>
			</FormGroup>
		</Form.Row>
  
		<Form.Row>
		<FormGroup as={Col} controlId="formGridName">
     <div style={{display: 'flex', justifyContent: 'center'}} textAlign="center"className="form-group">

       <label style={{display: 'flex', justifyContent: 'center'}} textAlign="center" htmlFor="email" style={{color: 'black'}}>Email Adresi : {this.state.email}</label>
      
	</div>
	</FormGroup>
	<FormGroup as={Col} controlId="formGridName">
     <div style={{display: 'flex', justifyContent: 'center'}} textAlign="center" className="form-group">

       <label style={{display: 'flex', justifyContent: 'center'}} textAlign="center" htmlFor="password" style={{color: 'black'}}>Doğum Tarihi : {this.state.dateOfBirth}</label>
       
	</div>
	</FormGroup>
		</Form.Row>
						
				</Form>	
				</CardContent>

      </CardActionArea>	  
	  <CardActions>
	  <Button  type="submit" className="btn btn-outline-secondary btn-light btn-lg btn-block"><Link to="/editProfile" className="btn btn-light btn-lg btn-block">PROFİL DÜZENLE</Link></Button>
	<Button  type="submit" className="btn btn-outline-secondary btn-light btn-lg btn-block"><Link to="/archive" className="btn btn-light btn-lg btn-block">ARŞİVİM</Link></Button>

      </CardActions>
     </Card>
	 </div>
	)
  }
}

export default Profile;