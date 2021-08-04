import React from 'react';
import '../App.css';
import { Link } from "react-router-dom";
import {Navbar, Nav} from "react-bootstrap";
import { Button, Container } from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSearch} from "@fortawesome/free-solid-svg-icons";

export default class AppNavbar extends React.Component {
  constructor(props){
    super(props);
    this.state = this.initialState;
    this.state.username = localStorage.getItem("userName");
  }

  initialState = {
		username:"", email:"", role:"", isLoggedIn: localStorage.getItem("isLoggedin")
	};

  handleLogout = (e) => {
		e.preventDefault();
    this.state = this.initialState;
    this.refreshPage();
    localStorage.clear();
  }
  refreshPage() {
    window.location.reload(false);
  }
  render(){

    return (
      <div>


          <Navbar bg="light" variant="light"> 
            <Link to={""} className="navbar-brand">Matine</Link>

              {localStorage.getItem("isLoggedin") === "true" ?  
              localStorage.getItem("userRole") === "SİSTEM_YÖNETİCİSİ" ?
                 <Nav className="navbar-nav ml-auto">
                 <Button variant="link" ><Link to={"/Search"} className="nav-link"><FontAwesomeIcon icon={faSearch}></FontAwesomeIcon> </Link></Button>
                 <Button variant="link" ><Link to={"/managementPage"} className="nav-link">Yönetim</Link></Button>
                 <Button variant="link" ><Link to={"/genreList"} className="nav-link">Türler</Link></Button>
                 <Button variant="link" ><Link to="/userProfile" className="nav-link">Profil</Link></Button>
                 <Button onClick={this.handleLogout}  variant="link" style={{alignment: "right"}} ><Link style={{alignment: "right"}} className="nav-link" to="">Çıkış Yap</Link></Button>

              </Nav> :

              
               <Nav className="navbar-nav ml-auto">
                 <Button variant="link" ><Link to={"/Search"} className="nav-link"><FontAwesomeIcon icon={faSearch}></FontAwesomeIcon> </Link></Button>
                 <Button variant="link" ><Link to={"/newUserReport"} className="nav-link">Bildir</Link></Button>
                 <Button variant="link" ><Link to={"/genreList"} className="nav-link">Türler</Link></Button>
                 <Button variant="link" ><Link to="/userProfile" className="nav-link">Profil</Link></Button>
                 <Button onClick={this.handleLogout}  variant="link" style={{alignment: "right"}} ><Link style={{alignment: "right"}} className="nav-link" to="">Çıkış Yap</Link></Button>

              </Nav>
              
              : 
              <Nav className="navbar-nav ml-auto">
              <Button variant="link" ><Link to={"/Search"} className="nav-link"><FontAwesomeIcon icon={faSearch}></FontAwesomeIcon> </Link></Button>
              <Button variant="link" ><Link to={"/genreList"} className="nav-link">Türler</Link></Button>
              <Button variant="link" ><Link to={"/sign-in"} className="nav-link" >Giriş Yap</Link></Button>
              <Button variant="link"  ><Link to={"/sign-up"} className="nav-link">Kaydol</Link></Button>
            </Nav>
          }
              
          </Navbar>
      </div>
    )
  }

}