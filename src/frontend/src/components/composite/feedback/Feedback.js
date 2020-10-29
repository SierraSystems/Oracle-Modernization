import React from 'react';
import { Button } from 'shared-components';
import './Feedback.css';

const openLink = (link) => {
  window.open(link);
};

export default function Feedback() {
  return (
    <>
      <h3>Have questions or suggestions about the Oracle Modernization Application?</h3>
      <div className="box">
        <p>Submit and view feedback for:</p>
        <div className="flexed">
          <Button
            label="Documentation"
            styling="bcgov-normal-white btn space-right"
            onClick={() => openLink("https://github.com/SierraSystems/Oracle-Modernization")}
          />
          <Button
            label="The Product"
            styling="bcgov-normal-white btn"
            onClick={() => openLink("https://github.com/SierraSystems/Oracle-Modernization/issues/new/choose")}
          />
        </div>
      </div>
    </>
  );
}
