import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { MdModeEdit, MdDeleteForever, MdCheckBox, MdCancel } from 'react-icons/md';
import { Button, Loader, Alert } from 'shared-components';
import { useHistory } from "react-router-dom";
import EditContact from '../edit-contact/EditContact';
import AddContact from "../add-contact/AddContact";
import Customers from '../customers/Customers';
import './Contacts.css';
import SimpleModal from '../../composite/simple-modal/SimpleModal';

const getContacts = (setContacts, setAlertMessage, setNextCursor) => {
  axios
    .get("/contacts")
    .then(({ data: { items, metadata } }) => {
      setContacts(items);
      if (metadata) {
        setNextCursor(metadata.nextCursor);
      } else {
        setNextCursor("");
      }
    })
    .catch(() => {
      setAlertMessage('Error getting contacts');
    });
};

const deleteContact = (contactToDelete, setShowModal, setAlertMessage, setContacts) => {
  axios
    .delete(`/contacts/${contactToDelete.contactId}`)
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

const loadMoreContacts = (nextCursor, setContacts, setNextCursor, setAlertMessage) => {
  if (!nextCursor) return;

  axios
    .get(`/contacts?fromcursor=${nextCursor}`)
    .then(({ data: { items, metadata } }) => {
      setContacts(contacts => contacts.concat(items));
      if (metadata) setNextCursor(metadata.nextCursor);
      else setNextCursor("");
    })
    .catch(() => {
      setAlertMessage('Error getting contacts');
    });
}

export default function Contacts({ isAuthed }) {
  const [contacts, setContacts] = useState([]);
  const [nextCursor, setNextCursor] = useState("more");
  const [customerIdToShow, setCustomerIdToShow] = useState(null);
  const [contactToEdit, setContactToEdit] = useState(null);
  const [contactToDelete, setContactToDelete] = useState(null);
  const [addContact, setAddContact] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const [alertMessage, setAlertMessage] = useState("");
  const history = useHistory();

  useEffect(() => {
    getContacts(setContacts, setAlertMessage, setNextCursor);
  }, []);

  if (!isAuthed) history.push("/");
  if (contactToEdit) return <EditContact contact={contactToEdit} />
  if (addContact) return <AddContact />
  if (customerIdToShow) return <Customers customerId={customerIdToShow} />

  const confirmButton = {
    label: "Delete contact",
    styling: "bcgov-normal-blue btn bcgov-consistent-width",
    onClick: () => deleteContact(contactToDelete, setShowModal, setAlertMessage, setContacts)
  };

  const cancelButton = {
    label: "No thank you",
    styling: "bcgov-normal-white btn bcgov-consistent-width",
    onClick: () => setShowModal(false)
  };

  return (
    <div className="page">
      <div className="content col-md-12">
        {alertMessage === "Error getting contacts" && (
          <>
            <Alert icon={<MdCancel size={32} />} type="error" styling="bcgov-error-background" element={alertMessage} />
            <br />
          </>
        )}
        {alertMessage === "Error deleting contact" && (
          <>
            <Alert icon={<MdCancel size={32} />} type="error" styling="bcgov-error-background" element={alertMessage} />
            <br />
          </>
        )}
        {alertMessage === "Successfully deleted contact" && (
          <>
            <Alert icon={<MdCheckBox size={32} />} type="success" styling="bcgov-success-background" element={alertMessage} />
            <br />
          </>
        )}
        {alertMessage !== "Error getting contacts" && (
          <>
            <div className="heading-spacing">
              <h2 className="v-centerify">Contacts</h2>
              <Button label="Add New Contact" styling="bcgov-normal-blue btn" onClick={() => setAddContact(true)} />
            </div>
            <br />
            {contacts.length === 0 && (
              <Loader page />
            )}
            {contacts.length > 0 && (
              <>
                <table className="table table-hover table-bordered table-sm">
                  <thead>
                    <tr>
                      <th>First Name</th>
                      <th>Last Name</th>
                      <th>Email</th>
                      <th>Phone Number</th>
                      <th>Customer Name</th>
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
                        <td className="pointer" onClick={() => setCustomerIdToShow(contact.customerId)}>{contact.customerName}</td>
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
                <br />
                {nextCursor && (
                  <Button onClick={() => loadMoreContacts(nextCursor, setContacts, setNextCursor, setAlertMessage)} label="Load more contacts" styling="bcgov-normal-blue btn" />
                )}
              </>
            )}
          </>
        )}
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
      </div>
    </div>
  );
}
