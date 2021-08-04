import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Button, Container } from 'react-bootstrap';
import Grid from '@material-ui/core/Grid';

export default class Home extends Component {
  constructor(props){
    super(props);
    this.state = this.initialState;
    this.state.username = localStorage.getItem("username");
  }

  initialState = {
		username:"", email:"", role:""
	};

  handleLogout = (e) => {
		e.preventDefault();
    this.state = this.initialState;
    localStorage.clear();
    this.props.history.push("");
  }


  render() {

    return (
      <div style={{
				paddingTop:200,
				width: 1000
			}}>
        <Container fluid>

          <Grid  container
  direction="row"
  justifyContent="space-between"
  alignItems="center">
            
          <Button  variant="link"><Link to="/newGenre" className="btn btn-light btn-lg btn-block"><h3>Yeni Tür</h3></Link></Button>
          <Button  variant="link"><Link to="/newContent" className="btn btn-light btn-lg btn-block"><h3>Yeni İçerik</h3></Link></Button>
          <Button  variant="link"><Link to="/reportList" className="btn btn-light btn-lg btn-block"><h3>Bildiriler</h3></Link></Button>
           <Button  variant="link"><Link to="/warnList"  className="btn btn-light btn-lg btn-block"><h3>Uyarılar</h3></Link></Button></Grid>




        </Container>
      </div>
    );
  }
}


