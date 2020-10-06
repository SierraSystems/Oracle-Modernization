import React from 'react';
import { useHistory } from 'react-router-dom';
import { Header, Footer, Button } from 'shared-components';
import Contacts from './components/contacts/Contacts';

const openLink = (link) => {
  window.open(link);
};

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
          <span>
            Welcome to Oracle Modernization. A proof-of-concept to migrate legacy Oracle Forms to a modern software architecture.
          </span>
          <br />
          <span>
            This application will allow users to interact and perform CRUD operations on entities residing within an Oracle Database.
          </span>
          <br />
          <br />
          <br />
          <Contacts />
          <br />
          <br />
          <h3>Have questions or suggestions about the Oracle Modernization Application?</h3>
          <div className="box">
            <p>Submit and view feedback for:</p>
            <div className="flexed">
              <Button
                label="Documentation"
                styling="normal-white btn space-right"
                onClick={() => openLink("https://github.com/SierraSystems/Oracle-Modernization")}
              />
              <Button
                label="The Product"
                styling="normal-white btn"
                onClick={() => openLink("https://github.com/SierraSystems/Oracle-Modernization/issues/new/choose")}
              />
            </div>
          </div>
        </div>
      </div>
      <Footer className="footer" />
    </main>
  );
}

export default App;
