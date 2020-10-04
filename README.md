# Justin-POC

Justin-POC


## Install Oracle on Azure

follow [this tutorial](https://docs.microsoft.com/en-us/azure/virtual-machines/workloads/oracle/oracle-database-quick-create#code-try-1)

> when restarting the vm, ip will change and nic might need to be [reconfigured](https://docs.microsoft.com/en-us/azure/virtual-machines/troubleshooting/reset-network-interface)
> if you still can't connect to the vm enable the oracle listener `$ sudo -su oracle` && `lsnrctl start` and set the SID `ORACLE_SID=cdb1; export ORACLE_SID`


connect to the database using sql developer and create the [oracle-sample-database](https://www.oracletutorial.com/getting-started/oracle-sample-database/)

fix the form builder here:
https://devio.wordpress.com/2010/09/22/forms_builder_classpath-not-set-after-installation-of-oracle-forms-builder-11-1-1-2-0/

Configure Justin data starter

Copy `src\backend\libs\justin-data-starter\src\main\resources\hibernate.properties.template` to `src\backend\libs\justin-data-starter\src\main\resources\hibernate.properties`

edit the following properties

```config
hibernate.dialect=org.hibernate.dialect.OracleDialect
hibernate.default_schema=OT
hibernate.connection.driver_class=oracle.jdbc.driver.OracleDriver
hibernate.connection.url=jdbc:oracle:thin:@<yourip>:1521:cdb1
hibernate.connection.username=ot
hibernate.connection.password=<yourpassword>
```
