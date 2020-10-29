import React from 'react';
import { Link } from 'react-router-dom'

export default function Home() {
  

  return (
    <main>
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
            <span>
              <Link to='/contacts'>See contacts</Link>
            </span>
            <br />
            <span>
              <Link to='/orders'>See orders</Link>
            </span>
          </div>
        </div>
      </div>
    </main>
  );
}
