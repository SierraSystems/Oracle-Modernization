import React from 'react';
import { useHistory } from 'react-router-dom';
import { Header, Footer } from 'shared-components';
import Contacts from '../../contacts/Contacts';

export default function ContactList() {
  const header = {
    name: "Oracle Modernization",
    history: useHistory(),
  };

  return (
    <main>
      <Header header={header} />
      <div className="page">
        <div className="content col-md-12">
          <Contacts />
        </div>
      </div>
      <Footer className="bcgov-footer" />
    </main>
  );
}
