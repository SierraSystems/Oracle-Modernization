# Oracle

## Deploy on Azure

follow this [instructions](https://docs.microsoft.com/en-us/azure/virtual-machines/workloads/oracle/oracle-database-quick-create)

## Run the scripts

Open your favorite editor and execute the scripts in the slq folder in the following order:

1. [ot_create_user.sql](src/oracle/sql/ot_create_user.sql)
1. [ot_schema.sql](sql/ot_schema.sql)
1. [ot_data.sql](src/oracle/sql/ot_data.sql)
1. [ot_warehouse.sql](src/oracle/sql/ot_warehouse.sql)

Your Database is ready!
