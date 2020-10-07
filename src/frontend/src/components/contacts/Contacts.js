import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { MdModeEdit, MdDeleteForever } from 'react-icons/md';
import { Button, Loader } from 'shared-components';
import EditContact from '../edit-contact/EditContact';
import Feedback from '../feedback/Feedback';
import AddContact from '../add-contact/AddContact';
import './Contacts.css';
import SimpleModal from '../simple-modal/SimpleModal';

const getContacts = (setContacts) => {
  axios
    .get("/management/contacts")
    .then((res) => {
      setContacts(res.data);
    })
    .catch((err) => {
      console.log("Getting contacts failed: ", err);
    });
};

const deleteContact = (contactToDelete) => {
  console.log("Contact to delete is", contactToDelete);
};

export default function Contacts() {
  const [contacts, setContacts] = useState([]);
  const [contactToEdit, setContactToEdit] = useState(null);
  const [contactToDelete, setContactToDelete] = useState(null);
  const [addContact, setAddContact] = useState(false);
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    getContacts(setContacts);
  }, []);

  if (contactToEdit) return <EditContact contact={contactToEdit} />
  if (addContact) return <AddContact />

  const confirmButton = {
    label: "Delete contact",
    styling: "normal-blue btn consistent-width",
    onClick: () => deleteContact(contactToDelete)
  };
  
  const cancelButton = {
    label: "No thank you",
    styling: "normal-white btn consistent-width",
    onClick: () => setShowModal(false)
  };

  return (
    <>
      <div className="heading-spacing">
        <h2 className="v-centerify">Contacts</h2>
        <Button label="Add New Contact" styling="normal-blue btn" onClick={() => setAddContact(true)} />
      </div>
      <br />
      {contacts.length === 0 && (
        <Loader page />
      )}
      {contacts.length > 0 && (
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
              <tr key={contact.contactId}>
                <td>{contact.contactId}</td>
                <td>{contact.firstName}</td>
                <td>{contact.lastName}</td>
                <td>{contact.email}</td>
                <td>{contact.phoneNumber}</td>
                <td>{contact.customerId}</td>
                <td>
                  <div className="icon-spacing">
                    <MdModeEdit className="pointer" size={32} onClick={() => setContactToEdit(contact)} />
                    <MdDeleteForever
                      className="pointer"
                      size={32}
                      onClick={() => {
                        setContactToDelete(contact);
                        setShowModal(true);
                      }}
                    />
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
      <br />
      <br />
      <Feedback />
      {showModal && (
        <SimpleModal
          title="Delete Contact"
          body={() => (
            <>
              <p className="text-center">
                {`Are you sure you want to delete ${contactToDelete.firstName} ${contactToDelete.lastName}?`}
              </p>
            </>
          )}
          cancelButton={cancelButton}
          confirmButton={confirmButton}
        />
      )}
    </>
  );
}
