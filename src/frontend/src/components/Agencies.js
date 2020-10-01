import React from 'react';
import { MdModeEdit, MdDeleteForever } from 'react-icons/md';
import './Agencies.css';

const editAgency = (agency) => {

};

const deleteAgency = (agency) => {

};

export default function Agencies({ agencies }) {
  return (
    <table className="table table-striped table-hover table-bordered table-sm">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Address</th>
          <th>Phone Number</th>
          <th>Email</th>
          <th>Contact Person</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {agencies.map(agency => (
          <tr>
            <td>{agency.id}</td>
            <td>{agency.name}</td>
            <td>{agency.address}</td>
            <td>{agency.phoneNumber}</td>
            <td>{agency.email}</td>
            <td>{agency.contactPerson}</td>
            <td>
              <div className="icon-spacing">
                <MdModeEdit className="pointer" size={32} onClick={() => editAgency(agency)} />
                <MdDeleteForever className="pointer" size={32} onClick={() => deleteAgency(agency)} />
              </div>
            </td>
          </tr>
        ))}
      </tbody>
    </table> 
  );
}
