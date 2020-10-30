import React from 'react';
import { Link } from 'react-router-dom';
import Feedback from '../../composite/feedback/Feedback';

export default function Home({ isAuthed }) {
  return (
    <div className="page">
      <div className="content col-md-12">
        <h1>Oracle Modernization</h1>
        <div>
          <span>
            Welcome to Oracle Modernization. A proof-of-concept to migrate legacy Oracle Forms to a modern software architecture.
          </span>
          <br />
          <span>
            This application will allow users to interact and perform CRUD operations on entities residing within an Oracle Database.
          </span>
          <br />
          <br />
          {isAuthed && (
          <>
            <span>
              <Link to='/contacts'>View Contacts</Link>
            </span>
            <br />
            <br />
            <span>
              <Link to='/orders'>View Orders</Link>
            </span>
            <br />
            <br />
            <span>
              <Link to='/countries'>View Countries</Link>
            </span>
            <br />
            <br />
          </>)}
          <br />
          <Feedback />
        </div>
      </div>
    </div>
  );
}
