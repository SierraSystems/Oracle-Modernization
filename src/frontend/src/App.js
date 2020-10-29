import React from 'react';
import {
  Switch,
  Route,
  useHistory
} from "react-router-dom";
import AuthenticationGuard from './components/hoc/AuthenticationGuard';
import Home from './components/page/home/Home';
import { Header, Footer } from 'shared-components';

function App() {
  const header = {
    name: "Oracle Modernization",
    history: useHistory(),
  };

  return (
    <main>
      <Header header={header} />
      <Switch>
        <Route exact path="/">
          <Home />
        </Route>
        <Route exact path="/contacts">
          <AuthenticationGuard page="Contacts" />
        </Route>
        <Route exact path="/orders">
          <AuthenticationGuard page="Orders" />
        </Route>
        <Route exact path="/countries">
          <AuthenticationGuard page="Countries" />
        </Route>
      </Switch>
      <Footer className="bcgov-footer" />
    </main>
  );
}

export default App;
