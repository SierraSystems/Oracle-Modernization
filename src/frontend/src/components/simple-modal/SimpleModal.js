import React from 'react';
import Modal from 'react-bootstrap/Modal';
import { Button } from 'shared-components';
import "./SimpleModal.css";

const generateButton = (onClick, label, styling, testId) => (
  <Button onClick={onClick} label={label} styling={styling} testId={testId} />
);

export default function SimpleModal({
  title,
  body,
  confirmButton,
  cancelButton,
}) {
  return (
    <Modal show onHide={cancelButton.onClick}>
      <Modal.Header className="hide-border mt-3" closeButton>
        <Modal.Title className="larger-font">{title}</Modal.Title>
      </Modal.Header>
      <div>
        <Modal.Body className="padding-left">
          <>
            {body()}
            <div className="mx-auto mb-5 table-view">
              {generateButton(
                confirmButton.onClick,
                confirmButton.label,
                confirmButton.styling,
                "modal-confirm-btn"
              )}
              <p />
              {generateButton(
                cancelButton.onClick,
                cancelButton.label,
                cancelButton.styling,
                "modal-cancel-btn"
              )}
            </div>
          </>
        </Modal.Body>
      </div>
    </Modal>
  );
}
