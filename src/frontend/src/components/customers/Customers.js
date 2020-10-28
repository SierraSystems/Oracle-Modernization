import React, { useState, useEffect } from "react";
import axios from "axios";
import { MdCancel } from 'react-icons/md';
import { Loader, Button, Alert } from "shared-components";
import Card from 'react-bootstrap/Card';
import Contacts from '../contacts/Contacts';

const getCustomerInfo = (customerId, setCustomerInfo, setAlertMessage, setImageAvatarUrl) => {
  axios
    .get(`/customers/${customerId}`)
    .then((res) => {
      setCustomerInfo(res.data);
      setImageAvatarUrl(`https://ui-avatars.com/api/?name=${res.data.fullName.replace(/ /g,"+")}&background=036&color=fff`);
    })
    .catch(() => {
      setAlertMessage(`Error getting customer for id ${customerId}`);
    });
};

export default function Customers({ customerId }) {
  const [customerInfo, setCustomerInfo] = useState(null);
  const [alertMessage, setAlertMessage] = useState("");
  const [showContacts, setShowContacts] = useState(false);
  const [imageAvatarUrl, setImageAvatarUrl] = useState("");

  useEffect(() => {
    getCustomerInfo(customerId, setCustomerInfo, setAlertMessage, setImageAvatarUrl);
  }, [customerId]);

  if (showContacts) return <Contacts />

  return (
    <>
      <h2>Customer Details</h2>
      <br />
      {customerInfo && (
        <div className="limit-width">
          <Card style={{ padding: '10px' }}>
            <img style={{ padding: 'inherit' }} src={imageAvatarUrl} alt="avatar" width="80" height="80"/>
            <div className="pad-it">
              <h3>{customerInfo.fullName}</h3>
              <br />
              <p>
                <b>Address:</b> {customerInfo.address}
              </p>
              <p>
                <b>Credit Limit:</b> {customerInfo.creditLimit}
              </p>
              <Button
                onClick={() => window.open(customerInfo.website)}
                label="Visit Website"
                styling="normal-blue btn"
              />
            </div>
          </Card>
        </div>
      )}

      {!customerInfo && !alertMessage && (
        <Loader page />
      )}

      {alertMessage && (
        <>
          <Alert icon={<MdCancel size={32} />} type="error" styling="error-background" element={alertMessage} />
          <br />
        </>
      )}

      <br />
      <section className="buttons pt-2">
        <Button
          label="Cancel"
          styling="normal-white btn"
          onClick={() => setShowContacts(true)}
        />
      </section>
    </>
  );
}
