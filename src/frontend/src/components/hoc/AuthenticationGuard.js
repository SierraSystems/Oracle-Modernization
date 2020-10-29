import React from "react";
import Contacts from "../page/contacts/Contacts";
import Countries from "../page/countries/Countries";
import Orders from "../page/orders/Orders";
import Home from "../page/home/Home";

/**
 * @constant authenticationGuard - a higher order component that checks for user authorization and returns the wrapped component if the user is authenticated
*/

export default function AuthenticationGuard({ page, isAuthed }) {
  const getComponent = () => {
    if(page === "Contacts") return <Contacts />
    if(page === "Countries") return <Countries />
    if(page === "Orders") return <Orders />
    return <Home />
  }

  return (
    <>
      {isAuthed && getComponent()}
      {!isAuthed && <Home /> }
    </>
  );
}
