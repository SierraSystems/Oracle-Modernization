import React from 'react';
import AuthenticationGuard from './components/hoc/AuthenticationGuard';
import Home from './components/page/home/Home';
import {
  Switch,
  Route
} from "react-router-dom";

function App() {
  return (
    <Switch>
      <Route exact path="/">
        <Home />
      </Route>
      <Route exact path="/contacts">
        <AuthenticationGuard />
      </Route>
    </Switch>
  );
}

export default App;
