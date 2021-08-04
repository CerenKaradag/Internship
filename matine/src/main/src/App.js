import React from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { CookiesProvider } from 'react-cookie';
import Login from "./components/User/Login";
import SignUp from "./components/User/Register";
import Home from './components/HomePage';
import AppNavbar from "./components/AppNavbar";
import {Nav} from "react-bootstrap";
import Genre from './components/Genre/AddGenre';
import GenreList from './components/Genre/GenreList';
import ContentList from './components/Content/ContentList';
import Profile from './components/Profile/Profile';
import EditProfile from './components/Profile/EditProfile';
import AddReport  from './components/Reports/AddReport';
import ReportList from './components/Reports/ReportList';
import WarnedList from './components/Reports/DeleteWarnedUsers';
import Search from "./components/Search";
import Archive from './components/Profile/Archive';
import ContentPage from './components/Content/ContentPage';
import AddContent from './components/Content/AddContent';
import Actor from './components/Content/Actor';
import NewActors from './components/Content/NewActors';

export default class App extends React.Component {
  constructor(props){
		super(props);
		this.state = {isLoggedin:""};
    this.state.isLoggedin = localStorage.getItem("isLoggedin")
	}

  render(){
    let buttons;
    
    if(this.state.isLoggedin){
      buttons = (
        <Nav className="navbar-right">
          <link to={"/homepage"} className="nav-link">Homepage</link>
          <link to={"/genreList"} className="nav-link">Tür Listesi</link>
        </Nav>
      )
    }else{
      buttons = (
        <Nav className="navbar-right">
          <link to={"/sign-in"} className="nav-link">Login</link>
          <link to={"/sign-up"} className="nav-link">Register</link>
        </Nav>
      )
    }
  
    return (
      <CookiesProvider>
        <Router>
            <div className="App">
            
              <AppNavbar buttons={buttons}/>


                <div className="outer">
                  <Switch>
                    <div className="inner">
                    {localStorage.clear}

                      <Route exact path='/' component={GenreList} />
                      <Route exact path="/sign-in" component={Login} state={this.state.isLoggedin} />
                      <Route exact path="/sign-up" component={SignUp} />
                      <Route exact path="/managementPage" component={Home} />
                      <Route exact path="/newGenre" component={Genre} />
                      <Route exact path="/genreList" component={GenreList} />
                      <Route exact path="/genre/:name" component={ContentList} />
                      <Route exact path="/userProfile" component={Profile} />
                      <Route exact path="/editProfile" component={EditProfile} />
                      <Route exact path="/newUserReport" component={AddReport} />
                      <Route exact path="/reportList" component={ReportList} />
                      <Route exact path="/warnList" component={WarnedList} />
                      <Route path="/Search" component={Search}/>
                      <Route path="/archive" component={Archive}/>
                      <Route path="/content/:name" component={ContentPage}/>
                      <Route path="/newContent" component={AddContent}/>
                      <Route path="/actors" component={Actor}/>
                      <Route path="/addActors" component={NewActors}/>











                  </div>

                </Switch>
              </div>
            </div>
        </Router>
      </CookiesProvider>
    );
  }

}