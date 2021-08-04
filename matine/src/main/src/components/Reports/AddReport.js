import React, {Component} from 'react';
import {Card, Button,Form} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import { faSave} from "@fortawesome/free-solid-svg-icons";

export default class UserReport extends Component {
    
	constructor(props) {
	    super(props);
	    this.state = { userName:"", reason:""};  
        
        
	  }	 
refreshPage() {
        window.location.reload(false);
      }
      handleSend = e => {
        e.preventDefault();
        this.sendReport(e);
        };

        sendReport(){
          fetch("http://localhost:8080/matine/add_report/" +  (localStorage.getItem("id")), {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              "Accept": "application/json",
            },
            
            params: {user_id : localStorage.getItem("id")}, 
              body: JSON.stringify({
                reportedUserName: this.state.userName,
                reportDescription: this.state.reason
            
            })
            }).then(() => {
                
                this.refreshPage();

          });
        }

        reportChange = event =>{
            this.setState({
              [event.target.name] : event.target.value
            })
          }; 
          

    render() {
        const {  userName, reason} = this.state;

        return (
            <div style={{
			width: 1000,
			paddingTop: 50
		  }}>
              <Card className ={"border border-light bg-light text-black"}>
                            <Form onSubmit={this.handleSend} >
                                                                
                                  <Card.Body>						
                                        <Form.Group controlId="formGridReview">
                                        <Form.Label>Bildirilecek Kullanıcı </Form.Label>

                                            <Form.Control required autoComplete="off"
                                              as= "textarea"
                                              className = {"bg-light text-black"}
                                              value ={userName}
                                              onChange={this.reportChange}
                                              placeholder="Bildirilecek Kullanıcı"
                                              name ="userName" />
                                        </Form.Group>	
                                        <Form.Group controlId="formGridReview">
                                        <Form.Label>Bildiri Sebebi</Form.Label>

                                            <Form.Control required autoComplete="off"
                                              as= "textarea"
                                              className = {"bg-light text-black"}
                                              value ={reason}
                                              onChange={this.reportChange}
                                              placeholder="Bildiri Sebebi"
                                              name ="reason" />
                                        </Form.Group>	
                                    </Card.Body>
                                    <Card.Footer style={{"textAlign":"right"}}>

                                    
                                      <Button variant="success" type="submit">
                                        <FontAwesomeIcon icon={faSave} /> Bildir
                                      </Button>
                                    </Card.Footer>
                                </Form> 
                                </Card>
            </div>
        )
    }
}