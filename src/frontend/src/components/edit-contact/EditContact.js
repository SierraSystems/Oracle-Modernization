import React, { useState } from 'react';
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

export default function EditContact({ contact }) {
  const [editedContact, setEditedContact] = useState(contact);
  const [showContacts, setShowContacts] = useState(false);

  const { firstName, lastName, email, phoneNumber } = contact;

  if (showContacts) return <Contacts />

  return (
    <div>
      {generateInput("firstName", "First Name", firstName, setEditedContact, editedContact)}
      {generateInput("lastName", "Last Name", lastName, setEditedContact, editedContact)}
      {generateInput("email", "Email", email, setEditedContact, editedContact)}
      {generateInput("phoneNumber", "Phone Number", phoneNumber, setEditedContact, editedContact)}
      <br />
      <section className="buttons pt-2">
        <Button
          label="Edit Contact"
          styling="normal-blue btn space-right"
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
