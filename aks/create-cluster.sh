#!/bin/bash

RESOURCE_GROUP=oracle-modernization

# Create azure resource group
az group create --name $RESOURCE_GROUP --location canadacentral

# create kubernetes cluster
az aks create --resource-group $RESOURCE_GROUP --name om-k8 --node-count 1 --enable-addons monitoring --generate-ssh-keys
