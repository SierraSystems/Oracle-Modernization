import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { MdCancel } from 'react-icons/md';
import { useHistory } from "react-router-dom";
import { Button, Loader, Alert } from 'shared-components';
import { validateAuthStatus } from '../../../modules/validateAuthStatus';

const loadOrders = (setOrders, setAlertMessage, setNextCursor) => {
  axios
    .get(`/orders?limit=50`)
    .then(({ data: { items, metadata } }) => {
      setOrders(orders => orders.concat(items));
      if (metadata) setNextCursor(metadata.nextCursor);
      else setNextCursor("");
    })
    .catch(() => {
      setAlertMessage('Error getting contacts');
    });
}

const loadMoreOrders = (fromCursor, setOrders, setNextCursor, setAlertMessage) => {
  if (!fromCursor) return;

  axios
    .get(`/orders?fromcursor=${fromCursor}&limit=100`)
    .then(({ data: { items, metadata } }) => {
      setOrders(orders => orders.concat(items));
      if (metadata) setNextCursor(metadata.nextCursor);
      else setNextCursor("");
    })
    .catch(() => {
      setAlertMessage('Error getting orders');
    });
}

export default function Orders({ isAuthed }) {
  const [orders, setOrders] = useState([]);
  const [nextCursor, setNextCursor] = useState("more");
  const [alertMessage, setAlertMessage] = useState("");
  const history = useHistory();

  useEffect(() => {
    loadOrders(setOrders, setAlertMessage, setNextCursor);
  }, []);

  if (!validateAuthStatus(isAuthed)) history.push("/");

  return (
    <div className="page">
      <div className="content col-md-12">
        <div className="heading-spacing">
          <h2 className="v-centerify">Orders</h2>
        </div>
        {alertMessage === "Error getting orders" && (
          <>
            <Alert icon={<MdCancel size={32} />} type="error" styling="bcgov-error-background" element={alertMessage} />
            <br />
          </>
        )}
        {alertMessage !== "Error getting orders" && (
          <>
            {orders.length === 0 && (
              <>
                <br />
                <Loader page />
              </>
            )}
            {orders.length > 0 && (
              <>
                <br />
                <table className="table table-hover table-bordered table-sm">
                  <thead>
                    <tr>
                      <th>OrderId</th>
                      <th>Customer Name</th>
                      <th>Assigned To</th>
                      <th>Status</th>
                      <th>Date</th>
                      <th>Total Items</th>
                      <th>Total Amount</th>
                    </tr>
                  </thead>
                  {orders.map(order => (
                    <tbody>
                      <tr key={order.orderId}>
                        <td>{order.orderId}</td>
                        <td>{order.customerName}</td>
                        <td>{order.employeeName}</td>
                        <td>{order.status}</td>
                        <td>{order.date}</td>
                        <td>{order.totalItems}</td>
                        <td>${order.totalAmount}</td>
                      </tr>
                    </tbody>
                  ))}
                </table>
                <br />
                {nextCursor && (
                  <Button onClick={() => loadMoreOrders(nextCursor, setOrders, setNextCursor, setAlertMessage)} label="Load more orders" styling="bcgov-normal-blue btn" />
                )}
              </>
            )}
          </>
        )}
      </div>
    </div>
  )
}
