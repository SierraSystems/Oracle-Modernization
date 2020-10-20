
# Public IP address of your ingress controller
IP="IP"

# Name to associate with public IP address
DNSNAME="ntt-om"

# Get the resource-id of the public ip
PUBLICIPID=$(az network public-ip list --query "[?ipAddress!=null]|[?contains(ipAddress, '$IP')].[id]" --output tsv)

echo 'PUBLIC IP ID: ' $PUBLICIPID

# Update public ip address with DNS name
MSYS_NO_PATHCONV=1 az network public-ip update --ids $PUBLICIPID --dns-name $DNSNAME

# Display the FQDN
MSYS_NO_PATHCONV=1 az network public-ip show --ids $PUBLICIPID --query "[dnsSettings.fqdn]" --output tsv