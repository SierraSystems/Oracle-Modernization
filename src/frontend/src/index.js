import React from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import './index.css';
import "@bcgov/bootstrap-theme/dist/css/bootstrap-theme.min.css";
import App from './App';
import * as serviceWorker from './serviceWorker';

axios.defaults.baseURL = window.REACT_APP_API_BASE_URL
  ? window.REACT_APP_API_BASE_URL
  : process.env.REACT_APP_API_BASE_URL;

ReactDOM.render(<App />, document.getElementById('root'));
serviceWorker.unregister();
