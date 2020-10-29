import React, { useState }  from 'react';
import { Link } from 'react-router-dom';
import { Button } from 'shared-components';
import Feedback from '../../composite/feedback/Feedback';
import Keycloak from "keycloak-js";
import { useEffect } from 'react';

const url = window.REACT_APP_KEYCLOAK_URL
  ? window.REACT_APP_KEYCLOAK_URL
  : process.env.REACT_APP_KEYCLOAK_URL;
const realm = window.REACT_APP_KEYCLOAK_REALM
  ? window.REACT_APP_KEYCLOAK_REALM
  : process.env.REACT_APP_KEYCLOAK_REALM;
const clientId = window.REACT_APP_KEYCLOAK_CLIENT_ID
  ? window.REACT_APP_KEYCLOAK_CLIENT_ID
  : process.env.REACT_APP_KEYCLOAK_CLIENT_ID;

const KEYCLOAK = {
  realm,
  url,
  clientId,
};

// Initialize client
const keycloak = Keycloak(KEYCLOAK);



export default function Home() {

  const [authedKeycloak, setAuthedKeycloak] = useState(null);

  async function login() {

    sessionStorage.setItem("clicked", true);

    await keycloak
      .init({
        checkLoginIframe: false,
      })
      .then((authenticated) => {
        
        console.log(keycloak);
        console.log(authenticated);
        
        if (authenticated) {
          keycloak.loadUserInfo();
          setAuthedKeycloak(true);
          localStorage.setItem("jwt", keycloak.token);
        } else {
          keycloak.login();
        }
      });

  }

  useEffect(() => {
    if(sessionStorage.getItem("clicked")) {
      login();
    }
  }, []);

  return (
    <div className="page">
      <div className="content col-md-12">
        <h1>Oracle Modernization</h1>
        <div>
          <span>
            Welcome to Oracle Modernization. A proof-of-concept to migrate legacy Oracle Forms to a modern software architecture.
          </span>
          <br />
          <span>
            This application will allow users to interact and perform CRUD operations on entities residing within an Oracle Database.
          </span>
          <br />
          <br />
          {!authedKeycloak && (
             <Button label="Login" styling="bcgov-normal-blue btn" onClick={() => login()} />
          )}
          {authedKeycloak && (
          <>
            <br />
            <br />
            <span>
              <Link to='/contacts'>View Contacts</Link>
            </span>
            <br />
            <br />
            <span>
              <Link to='/orders'>View Orders</Link>
            </span>
            <br />
            <br />
            <span>
              <Link to='/countries'>View Countries</Link>
            </span>
            <br />
            <br />
          </>)}
          <br />
          <Feedback />
        </div>
      </div>
    </div>
  );
}
