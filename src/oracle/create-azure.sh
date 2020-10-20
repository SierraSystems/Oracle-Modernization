#!/bin/bash

# Create new azure group for db
az group create --name oracle-db --location canadacentral

# create anew vm to host the db
az vm create \
    --resource-group oracle-db \
    --name oracle-vm \
    --image Oracle:Oracle-Database-Ee:12.1.0.2:latest \
    --size Standard_DS2_v2 \
    --admin-username azureuser \
    --generate-ssh-keys


