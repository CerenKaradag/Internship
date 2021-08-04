import React, {Component} from 'react';
import {Card, Table, ButtonGroup, Button,Form} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faTrash,faSave} from "@fortawesome/free-solid-svg-icons";
import Rating from '@material-ui/lab/Rating';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import { Link } from 'react-router-dom';
import NewToast from "../NewToast"

export default class ContentPage extends Component {

	constructor(props) {
	    super(props);
	    this.state = {content: [], isLoading: true, reviews:[],rates:[], review:"", rate:"", rateAvg: 0,errorMessage : false, error:""};
        this.state.content = this.props.location.state.content;

        console.log(this.state.content)
	  }	  
    refreshPage() {
      window.location.reload(false);
    }
    genrePage = () => {
		return this.props.history.push({pathname: "/genre/" + this.props.location.state.genre.name, state: this.props.location.state.genre,});
	};

    componentDidMount() {
    this.findAllReviews(this.state.content.contentId)
    this.findAllRates(this.state.content.contentId)
};


      findAllReviews(){
        fetch("http://localhost:8080/matine/comment/" + (this.state.content.contentId) , {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
          },
        }).then((response) => {
          if(response.status === 200){
            response.json().then((data) => {
              this.setState({ reviews: data, isLoading: false });
            });
            }else{
                response.json().then((responseN) => {
                    console.log(responseN);
                })
            }
          
        });	
      }

      findAllRates(){
        fetch("http://localhost:8080/matine/rate/" + (this.state.content.contentId) , {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
          },
          params : {contentId: this.state.content.contentId},
        }).then((response) => {
          if(response.status === 200){
            response.json().then((data) => {
              this.setState({ rates: data, isLoading: false });
              this.state.rates.map((rate) =>(
                this.setState({rateAvg: (this.state.rateAvg + rate.rate / this.state.rates.length)})
                ))
                console.log(this.state.rateAvg)
            });
            }else{
                response.json().then((responseN) => {
                    console.log(responseN);
                })
            }
          
        });	
      }
    
      handleReviewSubmit = e => {
        e.preventDefault();
        this.sendReview(e);
        };

      sendReview(){
        fetch("http://localhost:8080/matine/comment/" + (this.state.content.contentId) +  "/" + (localStorage.getItem("id")), {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
          },

          params: { contentId: this.state.content.contentId,user_id : localStorage.getItem("id")}, 
            body: JSON.stringify({

              commentBody: this.state.review,
              
          })
          }).then((response) => {
            if(response.status === 200){

              window.location.reload(false);
            }else{
              response.json().then((responseN) => {
                this.setState({
                  errorMessage : responseN.message
                })
            })
            }
        });
    }
        handleArchive = (content) => {
            this.addArchive(content);
            };
    
          addArchive(content){
            fetch("http://localhost:8080/matine/profile/archive/" +  (localStorage.getItem("id")), {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
                "Accept": "application/json",
              },
    
              params: { contentId: this.state.content.contentId,user_id : localStorage.getItem("id")}, 
                body: JSON.stringify({
    
                  contentId: content.contentId,
                  contentName: content.contentName,
                  contentDescription: content.contentDescription,
                  genre: content.genre
                  
              })
              }).then((response) => {
                if(response.status === 200){
                  this.setState({
                    errorMessage : true,
                    error : "İçerik arşive eklendi!"
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
              });
}

rateChange = event =>{
  this.setState({
    [event.target.name] : event.target.value
  })
};
    
      reviewChange = event =>{
        this.setState({
          [event.target.name] : event.target.value
        })
      };
  
      handleReviewRemove = (review) => {
        review.preventDefault();
        this.reviewRemove(review);
        };
    
      reviewRemove = (review) => {
        fetch("http://localhost:8080/matine/comment/"+ (review.commentId) , {
          method: "DELETE",
          headers: {
              
            "Content-Type": "application/json",
            "Accept": "application/json",      
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Credentials" : true     
          },
          params: { commentId : review.commentId}, 

            body: JSON.stringify({
            commentId : review.commentId
          })
        }).then((response) => {
          if(response.status === 200){
            this.setState({
              reviews: this.state.reviews.filter(reviewN => reviewN !== review )
            });
            
          }else{
            response.json().then((responseN) => {
              this.setState({
                errorMessage : responseN.message
              })
          })
          }
      });
    }
    handleReport = comment => {
        this.reportComment(comment);
        };

      reportComment(comment){
        fetch("http://localhost:8080/matine/report/" + (this.state.content.contentId) , {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
          },

          params: { contentId: this.state.content.contentId,user_id : localStorage.getItem("id")}, 
            body: JSON.stringify({

                commentId : comment.commentId,
                commentBody: comment.commentBody,
                contentId: comment.contentId
              
          })
          }).then((response) => {
            if(response.status === 200){
              this.setState({
                errorMessage : true,
                error : "Yorum bildirildi!"
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
          });
    }
    handleRateSubmit = e => {
      e.preventDefault();
      this.sendRate(e);
      };

    sendRate(){
      fetch("http://localhost:8080/matine/rate/" + ( this.state.content.contentId) +  "/"+  (localStorage.getItem("id")), {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Accept": "application/json",
        },

        params: {contentId: this.state.content.contentId, user_id : localStorage.getItem("id")}, 
          body: JSON.stringify({

            
            rate: this.state.rate,
            contentId: this.state.content.contentId,
            userId: localStorage.getItem("id")
        })
        }).then((response) => {
          if(response.status === 200){
            window.location.reload(false);
          }else{
            response.json().then((responseN) => {
              this.setState({
                errorMessage : responseN.message
              })
          })
          }
      });
    }
    render() {
        const {isLoading , review, rate } = this.state;

        return (
            <div style={{
              paddingTop:50,
              width: 1000 
            }}>
                			<NewToast show = {this.state.errorMessage} message={this.state.error}  type= {"danger"} />

                    <Card className ={"border border-ligth bg-light text-black"}>
                        <Card.Header>
                          {console.log(this.state.content)}
                            <h2 style={{textAlign: 'center'}} >
                            {this.state.content.contentName}
                            </h2>
                            <Card.Body>
                                <Table bordered hover striped variant="light">
                                      <thead>
                                          <tr>
                                             
                                          </tr>
                                      </thead>
                                      <tbody align = "center">

                                      
        
        <Rating 
        name="rate"
        value={this.state.rateAvg}
        max={10}
        readOnly 
      />
                                          
                                      </tbody>
                                  </Table>
                                  </Card.Body>

                                  {localStorage.getItem("isLoggedin") === "true" ?  
              localStorage.getItem("userRole") === "SİSTEM_YÖNETİCİSİ" ?
              <div  className='wrapper text-center'>

              <ButtonGroup >
              <Button variant="outline-primary" ><Link to={{pathname:"/actors", state: {content :this.state.content}}} >Oyuncular </Link></Button>
              <Button variant="outline-primary" onClick={() => this.handleArchive(this.state.content)}>Arşive Ekle</Button>

              <Button variant="outline-primary" ><Link to={{pathname:"/addActors", state: {content :this.state.content}}} >Oyuncu Ekle </Link></Button>

          </ButtonGroup>
          </div> :

              
              <div  className='wrapper text-center'>
                <ButtonGroup >
<Button variant="outline-primary" ><Link to={{pathname:"/actors", state: {content :this.state.content}}} >Oyuncular </Link></Button>
<Button variant="outline-primary" onClick={() => this.handleArchive(this.state.content)}>Arşive Ekle </Button>

</ButtonGroup>
              </div>


              
              : 
              <div className='wrapper text-center'>

              <ButtonGroup >
                                <Button variant="outline-primary"><Link to={{pathname:"/actors", state: {content :this.state.content}}} className="btn btn-sm btn-outline-primary">Oyuncular </Link></Button>

                            </ButtonGroup>
                            </div>
          }
                            
                        </Card.Header>
                            <Card.Body>
                                <Table bordered hover striped variant="light">
                                    <thead>
                                        <tr>
                                            <th>Açıklama</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <tr key={this.state.contentId}>
                                        
                                                <td>{this.state.content.contentDescription}</td>
                                            </tr>   
                                    </tbody>
                                </Table>
                            </Card.Body>
                            {localStorage.getItem("isLoggedin") === "true" ?  
              
              <Form onSubmit={this.handleRateSubmit} >
                                  <Card.Body align = "center">
                                  <Box component="fieldset" mb={3} borderColor="transparent">
                                            <Typography component="legend">Oy</Typography>
                                            <Rating
                                              name="rate"
                                              value={rate}
                                              onChange={this.reviewChange}
                                              max={10}
                                            />   <Button variant="success" type="submit">
                                            <FontAwesomeIcon icon={faSave} /> Gönder
                                          </Button>
                                          </Box>	
                                    </Card.Body>
                                    <Card.Footer style={{"textAlign":"right"}}>
                                      
                                    </Card.Footer>
                                </Form>:
<div></div>
              
                                        }
                            
                                  
                                <Card.Body>
                                <Table bordered hover striped variant="light">
                                      <thead>
                                          <tr>
                                              <th>Yorumlar</th>
                                              
                                          </tr>
                                      </thead>
                                      <tbody>
                                      {this.state.reviews.length === 0 ?

                                          <tr align="center">
                                            <td colSpan="2"> Henüz yorum yapılmamış.</td>
                                          </tr> : 
                                          this.state.reviews.map((review) =>(
                                              <tr key={review.commentId}>
                                                <td >{review.commentedUserName}</td>

                                                  <td >{review.commentBody}</td>
                                                  <td>
                                                    
                                                  {localStorage.getItem("isLoggedin") === "true" ?  
              (localStorage.getItem("userRole") === "SİSTEM_YÖNETİCİSİ" ) ? 
              
              <ButtonGroup>
                                                      
              <Button onClick={() => this.reviewRemove(review)} size="sm" variant="outline-danger"><FontAwesomeIcon icon={faTrash}/>Yorumu Sil</Button>
            </ButtonGroup> :
(  localStorage.getItem("id").match(review.userId)) ?

<ButtonGroup>
                                                      
                                                      <Button onClick={() => this.reviewRemove(review)} size="sm" variant="outline-danger"><FontAwesomeIcon icon={faTrash}/>Yorumu Sil</Button>
                                                    </ButtonGroup> :
            <ButtonGroup >
<Button variant="outline-primary" onClick={() => this.handleReport(review)}>Bildir</Button>

</ButtonGroup>

              

                                          
              
              : 
              <ButtonGroup >
                               
                            </ButtonGroup>
          }
                                                   
                                                </td>

                                              </tr>

                                              ))
                                          }
                                      </tbody>
                                  </Table>
                              </Card.Body>
                              {localStorage.getItem("isLoggedin") === "true" ?  
              
              <Form onSubmit={this.handleReviewSubmit} >
              <Card.Body>
                    <Form.Group controlId="formGridReview">
                        <Form.Control required autoComplete="off"
                          as= "textarea"
                          className = {"bg-light text-black"}
                          value ={review}
                          onChange={this.reviewChange}
                          placeholder="Yorum Yap.."
                          name ="review" />
                    </Form.Group>
                   
                </Card.Body>
                <Card.Footer style={{"textAlign":"right"}}>
                  <Button variant="success" type="submit">
                    <FontAwesomeIcon icon={faSave} /> Gönder
                  </Button>
                </Card.Footer>
            </Form>:
<div></div>
              
                                        }

                           
                    </Card>
                    
			</div>
        )
    }
}
