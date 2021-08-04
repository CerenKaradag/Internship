import React from 'react';
import {Card, Table, ButtonGroup} from 'react-bootstrap';

import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faList, faInfo} from "@fortawesome/free-solid-svg-icons";
import { Formik, Field, Form, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import NewToast from "../NewToast"
export default class Login extends React.Component {
    constructor(props){
		super(props);
        this.state = {isLoggedIn : false, errorMessage : false, error:""};
	}
    refreshPage() {
        window.location.reload(false);
      }
    render() {
      const {history} = this.props;
      console.log(this.props.location)
        return (
            <Formik
                initialValues={{
                    email: '',
                    password: '',
                }}
                validationSchema={Yup.object().shape({
                    email: Yup.string()
                        .email('Email geçersiz')
                        .required('Email gerekli'),
                    password: Yup.string()
                        .min(8, 'Şifre en az 8 karakter olmalı')
                        .required('Şifre gerekli'),
                })}
                onSubmit={values => {
                    fetch("http://localhost:8080/matine/process_login", {
                        method: "POST",
                        mode: "cors",
                        dataType: 'json',
                        headers: {
                          "Content-Type": "application/json",
                          "Accept": "application/json",
                          "Access-Control-Allow-Origin" : "*", 
                          "Access-Control-Allow-Credentials" : true 
                        }
                        ,
                        body: JSON.stringify({
                          email: values.email,
                          password: values.password
                        })
                      }).then(async response => {
                           if (response.status === 200) {
                            await response.json().then((user) => {
                                localStorage.setItem("email", user.email);
                                localStorage.setItem("id", user.id);
                                localStorage.setItem("userRole", user.userRole);
                                localStorage.setItem("username", user.userName);
                                localStorage.setItem("isLoggedin", true);
                                })
                                history.push('/genreList');
                                this.refreshPage();
                              }
                        else{
                            await response.json().then((responseN) => {
                                this.setState({isLoggedIn : false, errorMessage: true, error: responseN.message });
                            })
                        }
                      })
               }
            
            }
                render={({ errors, status, touched }) => (

                    <div style={{
                        paddingTop:50,
                        width: 1000 
                      }}>
                    <Card >
                        <Card.Header > 
                        <h3>Giriş</h3>
					</Card.Header>
                    <Card.Body>
							
                    <Form>
                    <NewToast show = {this.state.errorMessage} message={this.state.error}  type= {"danger"} />
                        
                        <div className="form-group">
                            <label htmlFor="email">Email</label>
                            <Field name="email" type="text" placeholder={"Enter email"} className={'form-control' + (errors.email && touched.email ? ' is-invalid' : '')} />
                            <ErrorMessage name="email" component="div" className="invalid-feedback" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="password">Şifre</label>
                            <Field name="password" type="password" placeholder={"Enter password"} className={'form-control' + (errors.password && touched.password ? ' is-invalid' : '')} />
                            <ErrorMessage name="password" component="div" className="invalid-feedback" />
                        </div>
                        <div className="form-group">
                            <button type="submit" className="btn btn-dark btn-lg btn-block">Giriş</button>
                                 
                        </div>
                    </Form>
                   
						</Card.Body>
                    </Card>
                    </div>
                )}
            />
        )
    }
}
