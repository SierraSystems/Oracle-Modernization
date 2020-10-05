import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { MdModeEdit, MdDeleteForever } from 'react-icons/md';
import { Button } from 'shared-components';
import EditContact from '../edit-contact/EditContact';
import './Contacts.css';

const deleteContact = (contact) => {

};

const getContacts = (setContacts) => {
  axios
    .get("/management/contacts")
    .then((res) => {
      console.log(res);
    })
    .catch((err) => {
      console.log("Getting contacts failed: ", err);
    });

  const contact1 = {
    id: 1,
    firstName: "Bob",
    lastName: "Ross",
    email: "myemail@example.com",
    phoneNumber: "123-456-7890",
    customerId: "1"
  };
  const contact2 = { ...contact1, id: 2, customerId: "2", firstName: "Magical", lastName: "Man" };
  const contacts = [contact1, contact2];

  setContacts(contacts);
};

export default function Contacts() {
  const [contacts, setContacts] = useState([]);
  const [contactToEdit, setContactToEdit] = useState(null);

  useEffect(() => {
    getContacts(setContacts);
  }, []);

  if (contactToEdit) return <EditContact contact={contactToEdit} />

  return (
    <>
      <div className="heading-spacing">
        <h2>Contacts</h2>
        <Button label="Add Contact" styling="normal-blue btn" />
      </div>
      <br />
      <table className="table table-striped table-hover table-bordered table-sm">
        <thead>
          <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Customer ID</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {contacts.map(contact => (
            <tr>
              <td>{contact.id}</td>
              <td>{contact.firstName}</td>
              <td>{contact.lastName}</td>
              <td>{contact.email}</td>
              <td>{contact.phoneNumber}</td>
              <td>{contact.customerId}</td>
              <td>
                <div className="icon-spacing">
                  <MdModeEdit className="pointer" size={32} onClick={() => setContactToEdit(contact)} />
                  <MdDeleteForever className="pointer" size={32} onClick={() => deleteContact(contact)} />
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
}
