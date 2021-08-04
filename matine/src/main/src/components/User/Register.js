import React from 'react';
import { Formik, Field, Form, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import {Card, Table, ButtonGroup} from 'react-bootstrap';
import NewToast from "../NewToast"
export default class Register extends React.Component {
    constructor(props){
		super(props);
        this.state = {isLoggedIn : false, errorMessage : false, error:""};
	}
    render() {
        return (
            <Formik
                initialValues={{
                    userName: '',
                    firstName: '',
                    lastName: '',
                    email: '',
                    password: '',
                }}
                validationSchema={Yup.object().shape({
                    userName: Yup.string()
                        .required("Kullanıcı adı gerekli"),
                    firstName: Yup.string()
                        .required('İsim gerekli'),
                    lastName: Yup.string()
                        .required('Soyisim gerekli'),
                        dateOfBirth: Yup.string()
                        .required('Doğum tarihi gerekli'),
                    email: Yup.string()
                        .email('Email geçersiz')
                        .required('Email gerekli'),
                    password: Yup.string()
                        .min(8, 'Şifre en az 8 karakter olmalı')
                        .required('Şifre gerekli'),
                })}
                onSubmit={values => {
                    fetch("http://localhost:8080/matine/process_register", {
                        method: "POST",
                        headers: {
                          "Content-Type": "application/json",
                          "Accept": "application/json",
                        },
                        body: JSON.stringify({
                          userName : values.userName,
                          firstName: values.firstName,
                          lastName: values.lastName,
                          dateOfBirth : values.dateOfBirth,
                          email: values.email,
                          password:values.password
                        })
                      }).then((response) => {
                        if(response.status === 200){
                            this.setState({
                                errorMessage : true,
                                error : "Kaydınız başarılı!"
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
               }}
                render={({ errors, touched }) => (
                    <div style={{
                        paddingTop:50,
                        width: 1000 
                      }}>

                    <Card >
                        <Card.Header > 
                        <h3>Kayıt </h3>
					</Card.Header>
                    <Card.Body>
                    <Form>
                    <NewToast show = {this.state.errorMessage} message={this.state.error}  type= {"danger"} />
                        
                        <div className="form-group">
                            <label htmlFor="userName">Kullanıcı Adı</label>
                            <Field name="userName" type="text" placeholder={"Kullanıcı Adı"} className={'form-control' + (errors.userName && touched.userName ? ' is-invalid' : '')} />
                            <ErrorMessage name="userName" component="div" className="invalid-feedback" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="firstName">İsim</label>
                            <Field name="firstName" type="text" placeholder={"isim"} className={'form-control' + (errors.firstName && touched.firstName ? ' is-invalid' : '')} />
                            <ErrorMessage name="firstName" component="div" className="invalid-feedback" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="lastName">Soyisim</label>
                            <Field name="lastName" type="text" placeholder={"Soyisim"} className={'form-control' + (errors.lastName && touched.lastName ? ' is-invalid' : '')} />
                            <ErrorMessage name="lastName" component="div" className="invalid-feedback" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="password">Doğum Tarihi</label>
                            <Field name="dateOfBirth" type="dateOfBirth" placeholder={"____-__-__"} className={'form-control' + (errors.password && touched.password ? ' is-invalid' : '')} />
                            <ErrorMessage name="dateOfBirth" component="div" className="invalid-feedback" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="email">Email</label>
                            <Field name="email" type="text" placeholder={"_____@____.com"} className={'form-control' + (errors.email && touched.email ? ' is-invalid' : '')} />
                            <ErrorMessage name="email" component="div" className="invalid-feedback" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="password">Şifre</label>
                            <Field name="password" type="password" placeholder={"******"} className={'form-control' + (errors.password && touched.password ? ' is-invalid' : '')} />
                            <ErrorMessage name="password" component="div" className="invalid-feedback" />
                        </div>
                        <div className="form-group">
                            <button type="submit" className="btn btn-dark btn-lg btn-block">Kaydol</button>
    
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

