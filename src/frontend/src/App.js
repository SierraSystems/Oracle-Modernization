import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { Header } from 'shared-components';
import Agencies from './components/Agencies';

const getAgencies = (setAgencies) => {
  const agency1 = {
    id: 1,
    name: "My Agency",
    address: "123 Best Street",
    phoneNumber: "123-456-7890",
    email: "myemail@example.com",
    contactPerson: "Dan Wolfe"
  };
  const agency2 = { ...agency1, id: 2, contactPerson: "Phillip Price" };
  const agencies = [agency1, agency2];

  setAgencies(agencies);
};

function App() {
  const [agencies, setAgencies] = useState([]);

  useEffect(() => {
    getAgencies(setAgencies);
  }, []);

  const header = {
    name: "Justin POC",
    history: useHistory(),
  };

  return (
    <main>
      <Header header={header} />
      <div className="page">
        <div className="content col-md-12">
          <h1>Justin POC</h1>
          <br />
          <Agencies agencies={agencies} />
        </div>
      </div>
    </main>
  );
}

export default App;
