# Justin-POC

Justin-POC


## Install Oracle on Azure

follow [this tutorial](https://docs.microsoft.com/en-us/azure/virtual-machines/workloads/oracle/oracle-database-quick-create#code-try-1)

> when restarting the vm, ip will change and nic might need to be [reconfigured](https://docs.microsoft.com/en-us/azure/virtual-machines/troubleshooting/reset-network-interface)
> if you still can't connect to the vm enable the oracle listener `$ sudo -su oracle` && `lsnrctl start` and set the SID `ORACLE_SID=cdb1; export ORACLE_SID`


connect to the database using sql developer and create the [oracle-sample-database](https://www.oracletutorial.com/getting-started/oracle-sample-database/)

fix the form builder here:
https://devio.wordpress.com/2010/09/22/forms_builder_classpath-not-set-after-installation-of-oracle-forms-builder-11-1-1-2-0/
