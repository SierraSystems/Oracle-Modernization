import React from 'react';
import { useHistory } from 'react-router-dom';
import { Header } from 'shared-components';

function App() {
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
        </div>
      </div>
    </main>
  );
}

export default App;
