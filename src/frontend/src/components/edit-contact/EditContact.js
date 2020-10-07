import React, { useState } from 'react';
import axios from 'axios';
import { Button, Input } from 'shared-components';
import Contacts from '../contacts/Contacts';
import './EditContact.css';

const input = {
  isReadOnly: false,
  isRequired: false
};

const generateInput = (id, label, value, setEditedContact, editedContact) => {
  return (
    <div className="half-width">
      <Input
        input={{
          ...input,
          id,
          label,
          styling: "editable-white",
          value
        }}
        onChange={(val) => {
          setEditedContact({
            ...editedContact,
            [id]: val,
          });
        }}
      />
      <br />
    </div>
  );
};

const editContact = (editedContact, setShowContacts) => {
  axios
    .put('/management/contact/update', editedContact)
    .then((res) => {
      if (res.status === 200) setShowContacts(true);
    })
    .catch((err) => {
      console.log('Error editing contact: ', err);
    })
};

export default function EditContact({ contact }) {
  const [editedContact, setEditedContact] = useState(contact);
  const [showContacts, setShowContacts] = useState(false);

  const { firstName, lastName, email, phoneNumber } = contact;

  if (showContacts) return <Contacts />

  return (
    <div>
      <h2>Edit Contact</h2>
      <br />
      {generateInput("firstName", "First Name", firstName, setEditedContact, editedContact)}
      {generateInput("lastName", "Last Name", lastName, setEditedContact, editedContact)}
      {generateInput("email", "Email", email, setEditedContact, editedContact)}
      {generateInput("phoneNumber", "Phone Number", phoneNumber, setEditedContact, editedContact)}
      <br />
      <section className="buttons pt-2">
        <Button
          label="Edit Contact"
          styling="normal-blue btn space-right"
          onClick={() => editContact(editedContact, setShowContacts)}
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
