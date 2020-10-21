#!/bin/bash

# set this to the name of your Azure Container Registry.  It must be globally unique
MYACR=nttOracleModernizationR

# Run the following line to create an Azure Container Registry if you do not already have one
az acr create -n $MYACR -g oracle-modernization --sku basic