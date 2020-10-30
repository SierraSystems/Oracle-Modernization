import React, { useState, useEffect } from 'react';
import {
  Switch,
  Route,
  useHistory
} from "react-router-dom";
import Keycloak from "keycloak-js";
import Home from './components/page/home/Home';
import { Header, Footer } from 'shared-components';
import Countries from './components/page/countries/Countries';
import Orders from './components/page/orders/Orders';
import Contacts from './components/page/contacts/Contacts';

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

function App() {
  const [isAuthed, setIsAuthed] = useState(false);

  useEffect(() => {
    if (sessionStorage.getItem("clicked")) login();
  }, []);

  const header = {
    name: "Oracle Modernization",
    history: useHistory(),
  };

  async function login() {
    sessionStorage.setItem("clicked", true);

    await keycloak
      .init({
        checkLoginIframe: false,
      })
      .then((authenticated) => {
        if (authenticated) {
          keycloak.loadUserInfo();
          localStorage.setItem("jwt", keycloak.token);
          setIsAuthed(true);
        } else {
          keycloak.login();
        }
      });
  }

  return (
    <main>
      <Header header={header} />
      <Switch>
        <Route exact path="/">
          <Home onLogin={() => login()} isAuthed={isAuthed} />
        </Route>
        <Route exact path="/contacts">
          <Contacts isAuthed={isAuthed} />
        </Route>
        <Route exact path="/orders">
          <Orders isAuthed={isAuthed} />
        </Route>
        <Route exact path="/countries">
          <Countries isAuthed={isAuthed} />
        </Route>
      </Switch>
      <Footer className="bcgov-footer" />
    </main>
  );
}

export default App;
