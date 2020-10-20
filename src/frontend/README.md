# Oracle Modernization - Frontend

Electron Desktop / React Web Frontend for the Oracle Modernization proof-of-concept application.

## Run Locally

In order to run the application frontend on your local machine, follow the steps below.

#### 1. Install dependencies

From the `frontend` directory of the project, run `yarn install`.

#### 2. Start the development server and navigate to `localhost:3000` for web app
# Public IP address of your ingress controller
IP="MY_EXTERNAL_IP"

# Name to associate with public IP address
DNSNAME="demo-aks-ingress"

# Get the resource-id of the public ip
PUBLICIPID=$(az network public-ip list --query "[?ipAddress!=null]|[?contains(ipAddress, '$IP')].[id]" --output tsv)

# Update public ip address with DNS name
az network public-ip update --ids $PUBLICIPID --dns-name $DNSNAME

# Display the FQDN
az network public-ip show --ids $PUBLICIPID --query "[dnsSettings.fqdn]" --output tsv
Run `yarn start`.

#### 3. Start the electron app

Run `yarn run electron`.
