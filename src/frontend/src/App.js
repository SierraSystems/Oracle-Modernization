import React from 'react';
import AuthenticationGuard from './components/hoc/AuthenticationGuard';
import Home from './components/page/home/Home';
import OrderList from './components/orders/page/OrderList'
import { Header, Footer } from 'shared-components';
import { useHistory } from 'react-router-dom';
import {
  Switch,
  Route
} from "react-router-dom";

function App() {

  const header = {
    name: "Oracle Modernization",
    history: useHistory(),
  };

  return (
    <div>
      <Header header={header} />
      <Switch>
        <Route exact path="/">
          <Home />
        </Route>
        <Route exact path="/contacts">
          <AuthenticationGuard />
        </Route>
        <Route exact path="/orders">
          <OrderList />
        </Route>
      </Switch>
      <Footer className="bcgov-footer" />
    </div>
  );
}

export default App;
