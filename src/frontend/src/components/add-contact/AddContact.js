import React, { useState } from 'react';
import axios from 'axios';
import { Button, Input } from 'shared-components';
import Contacts from '../contacts/Contacts';

const input = {
  isReadOnly: false,
  isRequired: true
};

const generateInput = (id, label, setAddedContact, addedContact) => {
  return (
    <div className="half-width">
      <Input
        input={{
          ...input,
          id,
          label,
          styling: "editable-white"
        }}
        onChange={(val) => {
          setAddedContact({
            ...addedContact,
            [id]: val,
          });
        }}
      />
      <br />
    </div>
  );
};

const addNewContact = (addedContact, setShowContacts) => {
  axios
    .post('/management/contact/add', addedContact)
    .then((res) => {
      if (res.status === 201) setShowContacts(true);
    })
    .catch((err) => {
      console.log('Error adding a new contact: ', err);
    });
};

export default function AddContact({ contact }) {
  const [addedContact, setAddedContact] = useState({});
  const [showContacts, setShowContacts] = useState(false);

  if (showContacts) return <Contacts />

  return (
    <div>
      <h2>Add New Contact</h2>
      <br />
      {generateInput("firstName", "First Name", setAddedContact, addedContact)}
      {generateInput("lastName", "Last Name", setAddedContact, addedContact)}
      {generateInput("email", "Email", setAddedContact, addedContact)}
      {generateInput("phoneNumber", "Phone Number", setAddedContact, addedContact)}
      <br />
      <section className="buttons pt-2">
        <Button
          label="Add New Contact"
          styling="normal-blue btn space-right"
          onClick={() => addNewContact(addedContact, setShowContacts)}
        />
        <Button
          label="Cancel"
          styling="normal-white btn"
          onClick={() => setShowContacts(true)}
        />
      </section>
    </div>
  )
}
