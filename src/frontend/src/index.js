import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import "@bcgov/bootstrap-theme/dist/css/bootstrap-theme.min.css";
import App from './App';
import * as serviceWorker from './serviceWorker';

ReactDOM.render(<App />, document.getElementById('root'));
serviceWorker.unregister();
