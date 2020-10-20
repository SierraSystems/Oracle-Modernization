#!/bin/bash

NAMESPACE=ingress-controller

echo 'Create a namespace for your ingress resources'
kubectl create namespace $NAMESPACE

echo 'Add the ingress-nginx repository'
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx

echo 'Use Helm to deploy an NGINX ingress controller'
helm install nginx-ingress ingress-nginx/ingress-nginx --namespace $NAMESPACE \
    --set controller.replicaCount=2 \
    --set controller.nodeSelector."beta\.kubernetes\.io/os"=linux \
    --set defaultBackend.nodeSelector."beta\.kubernetes\.io/os"=linux
