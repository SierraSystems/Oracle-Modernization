import React from 'react';
import {
  Switch,
  Route,
  useHistory
} from "react-router-dom";
import AuthenticationGuard from './components/hoc/AuthenticationGuard';
import Home from './components/page/home/Home';
import Orders from './components/page/orders/Orders';
import { Header, Footer } from 'shared-components';
import Countries from './components/page/countries/Countries';

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
          <AuthenticationGuard />
        </Route>
        <Route exact path="/orders">
          <Orders />
        </Route>
        <Route exact path="/countries">
          <Countries />
        </Route>
      </Switch>
      <Footer className="bcgov-footer" />
    </main>
  );
}

export default App;
