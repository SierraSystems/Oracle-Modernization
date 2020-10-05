import React from 'react';
import { useHistory } from 'react-router-dom';
import { Header, Footer } from 'shared-components';
import Contacts from './components/contacts/Contacts';

function App() {
  const header = {
    name: "Oracle Modernization",
    history: useHistory(),
  };

  return (
    <main>
      <Header header={header} />
      <div className="page">
        <div className="content col-md-12">
          <h1>Oracle Modernization</h1>
          <p>
            Welcome to Oracle Modernization. This proof-of-concept application will allow users
            to interact and perform CRUD operations on entities residing within an Oracle Database.
          </p>
          <br />
          <Contacts />
        </div>
      </div>
      <Footer className="footer" />
    </main>
  );
}

export default App;
