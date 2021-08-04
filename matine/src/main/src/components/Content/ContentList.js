
import React, {Component} from 'react';
import {Card , Table, ButtonGroup, Button, Form} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faEdit,faTrash,faList, faPlus, faInfo, faSave} from "@fortawesome/free-solid-svg-icons";
import { Link } from 'react-router-dom';
import Typography from '@material-ui/core/Typography';
export default class ContentList extends Component {

	constructor(props) {
	    super(props);
	    this.state = {genre:[],contents: [], isLoading: true};  
      this.state.genre = this.props.location.state;
	  }	  
    
    componentDidMount() {
		this.findAllContents(this.state.genre.id)
	};

	findAllContents(genreId){
	  fetch("http://localhost:8080/matine/" + (genreId) + "/contents", {
	    method: "GET",
	    headers: {
	      "Content-Type": "application/json",
	      "Accept": "application/json",
	    },
	  }).then((response) => {
	    response.json().then((data) => {
	      this.setState({ contents: data, isLoading: false });
          console.log(data);
	    });
	  });	
	}

  

	genreList = () => {
		return this.props.history.push("/genreList");
	};

	
  handleClick = () => {
    this.setState({open: true});
    };

  


    render() {
        const {isLoading } = this.state;

	    if (isLoading) {
	      return <p>Loading...</p>;
	    }

        return (
            <div style={{
              paddingTop:50,
              width: 1000 
            }} >      
                    <Card className ={"border border-light bg-light text-black"}>
                        <Card.Header >
                        <Typography component="h4" variant="h4" align="center" color="Black" gutterBottom>
                          {this.state.genre.name} İçerikler
                            </Typography>
                          
                        </Card.Header>
                            <Card.Body>
                                <Table bordered hover striped variant="light">
                                    <thead>
                                        <tr>
                                            <th>İçerik Adı</th>
                                            <th>İçerik Açıklaması</th>
                                            <th>İçerik Türü</th>
                                            <th></th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                    {this.state.contents.length === 0 ?
                                        
                                        <tr align="center">
                                            <td colSpan="2"> İçerik Yok</td>
                                        </tr> : 
                                        this.state.contents.map((content) =>(
                                            <tr key={content.contentId}>
                                                <td style={{textTransform: "capitalize"}}>{content.contentName}</td>
                                                <td>{content.contentDescription}</td>
                                                <td>{content.contentType}</td>
                                                <td>
                                                <ButtonGroup>
                                                <Link to={{pathname:"/content/" + content.contentName, state: {genre: this.state.genre,content :content}}} className="btn btn-sm btn-outline-secondary"> <FontAwesomeIcon icon={faInfo}/></Link>

                                                </ButtonGroup></td>
                                            </tr>
                                            ))
                                        }
                                    </tbody>
                                </Table>                                  
                            </Card.Body>
                            
                    </Card>                    
                    
			        </div>
        )
    }
}
