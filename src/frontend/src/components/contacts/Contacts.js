import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { MdModeEdit, MdDeleteForever, MdCheckBox, MdCancel } from 'react-icons/md';
import { Button, Loader, Alert } from 'shared-components';
import EditContact from '../edit-contact/EditContact';
import Feedback from '../feedback/Feedback';
import AddContact from '../add-contact/AddContact';
import './Contacts.css';
import SimpleModal from '../simple-modal/SimpleModal';

const getContacts = (setContacts, setAlertMessage) => {
  // axios
  //   .get("/management/contacts")
  //   .then((res) => {
  //     setContacts(res.data);
  //   })
  //   .catch(() => {
  //     setAlertMessage('Error getting contacts');
  //   });
  const contacts = [
    {
      firstName: "Alex",
      lastName: "Joy",
      email: "alex@joy.com",
      phoneNumber: "123-456-7890",
      customerId: 1
    },
    {
      firstName: "Dan",
      lastName: "Wolfe",
      email: "dan@wolfe.com",
      phoneNumber: "126-456-7890",
      customerId: 12
    }
  ];
  setContacts(contacts);
};

const deleteContact = (contactToDelete, setShowModal, setAlertMessage, setContacts) => {
  console.log("Contact to delete is", contactToDelete);
  axios
    .delete(`/management/contact/${contactToDelete.contactId}/delete`)
    .then((res) => {
      if (res.status === 200) {
        setShowModal(false);
        setAlertMessage('Successfully deleted contact');
        getContacts(setContacts, setAlertMessage);
      }
    })
    .catch(() => {
      setAlertMessage('Error deleting contact');
    })
};

export default function Contacts() {
  const [contacts, setContacts] = useState([]);
  const [contactToEdit, setContactToEdit] = useState(null);
  const [contactToDelete, setContactToDelete] = useState(null);
  const [addContact, setAddContact] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const [alertMessage, setAlertMessage] = useState("");

  useEffect(() => {
    getContacts(setContacts, setAlertMessage);
  }, []);

  if (contactToEdit) return <EditContact contact={contactToEdit} />
  if (addContact) return <AddContact />

  const confirmButton = {
    label: "Delete contact",
    styling: "normal-blue btn consistent-width",
    onClick: () => deleteContact(contactToDelete, setShowModal, setAlertMessage, setContacts)
  };

  const cancelButton = {
    label: "No thank you",
    styling: "normal-white btn consistent-width",
    onClick: () => setShowModal(false)
  };

  return (
    <>
      {alertMessage === "Error getting contacts" || alertMessage === "Error deleting contact" && (
        <>
          <Alert icon={<MdCancel size={32} />} type="error" styling="error-background" element={alertMessage} />
          <br />
        </>
      )}
      {alertMessage === "Successfully deleted contact" && (
        <>
          <Alert icon={<MdCheckBox size={32} />} type="success" styling="success-background" element={alertMessage} />
          <br />
        </>
      )}
      {alertMessage !== "Error getting contacts" && (
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
            <table className="table table-hover table-bordered table-sm">
              <thead>
                <tr>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Email</th>
                  <th>Phone Number</th>
                  <th>Customer ID</th>
                  <th>Actions</th>
                </tr>
              </thead>
              {contacts.map(contact => (
                <tbody>
                  <tr key={contact.contactId}>
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
                </tbody>
              ))}
            </table>
          )}
        </>
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
