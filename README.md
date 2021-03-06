# Oracle Modernization

Oracle Modernization is a proof-of-concept to migrate legacy Oracle Forms to modern software architecture.

Frontend Web App: https://oracle-modernization-fe.azurewebsites.net/

## Deploy backend to Azure

```bash
cd src\backend\management-api
mvn clean package azure-webapp:deploy
```

## Configure Data Starter

Copy `src\backend\libs\justin-data-starter\src\main\resources\hibernate.properties.template` to `src\backend\libs\justin-data-starter\src\main\resources\hibernate.properties`

Edit the following properties:

```config
hibernate.dialect=org.hibernate.dialect.OracleDialect
hibernate.default_schema=OT
hibernate.connection.driver_class=oracle.jdbc.driver.OracleDriver
hibernate.connection.url=jdbc:oracle:thin:@<yourip>:1521:cdb1
hibernate.connection.username=ot
hibernate.connection.password=<yourpassword>
```

## Install Oracle Form Builder

Follow  this: [https://simpleoracle.com/tag/forms-reports-12c/](https://simpleoracle.com/tag/forms-reports-12c/)

Prerequisites:

- Java jdk 8
- Visual C++ [install](https://www.microsoft.com/en-us/download/details.aspx?id=40784)

Follow these [instructions](https://oracle-base.com/articles/12c/standalone-forms-builder-12c-installation-on-windows-1221)

Open `<your installation dir>\Middleware\forms_builder\forms\templates\registry\formsbuilder.reg` and look for the following entry:

```
[HKEY_LOCAL_MACHINE\%ORACLE_HOME_REG_KEY%]
"FORMS_BUILDER_CLASSPATH"="%ORACLE_HOME%\\..."
```

Create a new entry in the windows registry under `HKEY_LOCAL_MACHINE\SOFTWARE\Oracle\KEY_OracleHome1`

**Value name**: FORMS_BUILDER_CLASSPATH

**Value data**: The value from the formsbuilder.reg: `%ORACLE_HOME%\\jlib\\frmbld.jar;%ORACLE_HOME%\\jlib\\importer.jar;%ORACLE_HOME%\\jlib\\debugger.jar;%ORACLE_HOME%\\jlib\\utj.jar;%ORACLE_HOME%\\oracle_common\\jlib\\ewt3.jar;%ORACLE_HOME%\\oracle_common\\modules\\oracle.bali.share\\share.jar;%ORACLE_HOME%\\jlib\\dfc.jar;%ORACLE_HOME%\\oracle_common\\modules\\oracle.help\\ohj.jar;%ORACLE_HOME%\\oracle_common\\modules\\oracle.help\\help-share.jar;%ORACLE_HOME%\\oracle_common\\modules\\oracle.help\\oracle_ice.jar;%ORACLE_HOME%\\oracle_common\\modules\\oracle.bali.jewt\\jewt4.jar;%ORACLE_HOME%\\forms\\java\\frmwebutil.jar;%ORACLE_HOME%\\forms\\java\\frmall.jar;%ORACLE_HOME%\\forms\\java\\frmwebsocketjsi.jar`

Then execute `<your installation dir>\Middleware\Oracle_Home\bin\frmbld.exe`

To connect to the database go to file > Connect... and enter the database credentials:

![Connect To Oracle](docs/forms-connect.PNG)

> Do not specifiy the PORT in the connection string (this can cost lots of time)

Install [jdeveloper-12c](https://www.oracle.com/tools/downloads/jdev-v12120-downloads.html) and choose the 32bit version as they are mixed up ;-)

Fix the form builder here:
https://devio.wordpress.com/2010/09/22/forms_builder_classpath-not-set-after-installation-of-oracle-forms-builder-11-1-1-2-0/
