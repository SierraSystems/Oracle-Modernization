import React, { useState, useEffect } from "react";
import Keycloak from "keycloak-js";
import Home from "../home/Home";

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
      .success((authenticated) => {
        if (authenticated) {
          keycloak.loadUserInfo().success();

          localStorage.setItem("jwt", keycloak.token);
          setAuthedKeycloak(keycloak);
        }
      });
  }

  useEffect(() => {
    keycloakInit();
  }, []);

  return (
    <>
      {authedKeycloak && <Home />}
      {!authedKeycloak && null}
    </>
  );
}
