import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { MdCancel } from 'react-icons/md';
import { Loader, Alert } from 'shared-components';

const getCountries = (setCountries, setAlertMessage) => {
  axios
    .get("/countries")
    .then(({ data }) => {
      setCountries(data);
    })
    .catch(() => {
      setAlertMessage('Error getting countries');
    });
};

export default function Countries() {
  const [countries, setCountries] = useState([]);
  const [alertMessage, setAlertMessage] = useState("");

  useEffect(() => {
    getCountries(setCountries, setAlertMessage);
  }, []);

  return (
    <div className="page">
      <div className="content col-md-12">
        <div className="heading-spacing">
          <h2 className="v-centerify">Countries</h2>
        </div>
        <br />
        {alertMessage === "Error getting countries" && (
          <>
            <Alert icon={<MdCancel size={32} />} type="error" styling="bcgov-error-background" element={alertMessage} />
            <br />
          </>
        )}
        {alertMessage !== "Error getting countries" && (
          <>
            {countries.length === 0 && (
              <Loader page />
            )}
            {countries.length > 0 && (
              <table className="table table-hover table-bordered table-sm">
                <thead>
                  <tr>
                    <th>Country Code</th>
                    <th>Country Name</th>
                    <th>Region</th>
                  </tr>
                </thead>
                {countries.map(country => (
                  <tbody>
                    <tr key={country.countryId}>
                      <td>{country.countryId}</td>
                      <td>{country.countryName}</td>
                      <td>{country.regionId}</td>
                    </tr>
                  </tbody>
                ))}
              </table>
            )}
          </>
        )}
      </div>
    </div>
  );
}
