# AKS Configuration

## Before you begin

You need the `Azure CLI` version 2.6.0 or later installed and configured. Run az --version to find the version. If you need to install or upgrade, see [Install Azure CLI](https://docs.microsoft.com/en-us/cli/azure/install-azure-cli).

You need kubernetes cli, you can install the cli using the following az command:

```bash
az aks install-cli
```

You need the [HELM 3](https://helm.sh/) or later installed and configured.

## Install aks on azure

### Add AKS on azure

RUN

```bash
./create-cluster.sh
```

add credentials to your kubeconfg

```bash
az aks get-credentials --resource-group oracle-modernization --name om-k8
```

access kubernetes dashboard

set up the dashboard role for the cluster user

```bash
kubectl delete clusterrolebinding kubernetes-dashboard
kubectl create clusterrolebinding kubernetes-dashboard --clusterrole=cluster-admin --serviceaccount=kube-system:kubernetes-dashboard --user=clusterUser
```

Access the dashboard
```bash
az aks browse --resource-group oracle-modernization --name om-k8
```

 access the dasboard here: [http://127.0.0.1:8001/api/v1/namespaces/kube-system/services/https:kubernetes-dashboard:/proxy/#/clusterrole?namespace=default](http://127.0.0.1:8001/api/v1/namespaces/kube-system/services/https:kubernetes-dashboard:/proxy/#/clusterrole?namespace=default)

## Install nginx ingress

[ingress-tls azure tutorial](https://docs.microsoft.com/en-us/azure/aks/ingress-tls)

add nginx ingress controller

```bash
./ingress.sh
```

get the external ip of your nginx ingress controller

```bash
kubectl --namespace ingress-controller get services -o wide -w nginx-ingress-ingress-nginx-controller
```

output

```
NAME                                     TYPE           CLUSTER-IP     EXTERNAL-IP    PORT(S)                      AGE    SELECTOR
nginx-ingress-ingress-nginx-controller   LoadBalancer   0.0.0.0        0.0.0.0        80:32707/TCP,443:31425/TCP   2m6s   app.kubernetes.io/component=controller,app.kubernetes.io/instance=nginx-ingress,app.kubernetes.io/name=ingress-nginx
```

if you browse to the EXTERNAL IP you should get the default nginx 404 not found page

Configure an FQDN for the ingress controller IP address instead of a custom domain.

```bash
./azure-fqdn.sh
```

Create a ca cluster issuer

```bash
kubectl apply -f cluster-issuer.yaml
```

Create a new namespace

```bash
kubectl create namespace oracle-modernization
```

Create keycloak server

```bash
kubectl apply -f ./keycloak/keycloak.yaml
```

Adding docker registry

```bash
az acr create --resource-group oracle-modernization --name nttomregistry --sku Basic
```
