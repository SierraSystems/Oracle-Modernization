import React from 'react';
import AuthenticationGuard from './components/hoc/AuthenticationGuard';

function App() {
  return (
    <AuthenticationGuard />
  );
}

export default App;
