import React, { useState, useEffect } from "react";
import Keycloak from "keycloak-js";
import ContactList from "../page/contacts/ContactList";

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

/**
 * @constant authenticationGuard - a higher order component that checks for user authorization and returns the wrapped component if the user is authenticated
*/

export default function AuthenticationGuard() {
  const [authedKeycloak, setAuthedKeycloak] = useState(null);

  async function keycloakInit() {
    await keycloak
      .init({
        checkLoginIframe: false,
      })
      .then((authenticated) => {
        if (authenticated) {
          keycloak.loadUserInfo();
          localStorage.setItem("jwt", keycloak.token);
          setAuthedKeycloak(keycloak);
        } else {
          keycloak.login();
        }
      });
  }

  useEffect(() => {
    keycloakInit();
  }, []);

  return (
    <>
      {authedKeycloak && <ContactList />}
      {!authedKeycloak && null }
    </>
  );
}
