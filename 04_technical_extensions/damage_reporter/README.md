## Fuse Online TP3 Sample Extension

The repository contains extension that can be used in a integration to demonstrate the following flow `JMS -> Extension -> Datamapper -> Todo API`.

The extension implement XML defined route to be plugged into the flow.
Basic concept is that this route is processing XML messages received from JMS endpoint. Afterwards message is processed and only damaged `Item` objects are extracted.
For damaged items a report is generated to be stored in Todo list a task. The report provides contact information and item list.


```xml
<routes xmlns="http://camel.apache.org/schema/spring" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://camel.apache.org/schema/spring http://camel.apache.org/schema/spring/camel-spring.xsd">
  <route id="damage-report">
	<from uri="direct:damage-report"/>
	<log message="Received item list:\n${in.body}" />
	<unmarshal>
	  <jacksonxml unmarshalTypeName="io.syndesis.extension.InventoryList" />
	</unmarshal>
	<bean beanType="io.syndesis.extension.ReportService" method="generateReport" />
	<when>
	  <simple>${in.body} == null</simple>
	  <log message="No damaged item found."/>
	  <stop/>
	</when>
	<!-- Continue otherwise -->
    <log message="Generated report: ${in.body}"/>
  </route>
</routes>
```

XML format
```xml
<?xml version="1.0" encoding="UTF-8"?>
<inventoryReceived>
  <item id="XYZ123" damaged="false" vendor="Good Inc."/>
  <item id="ABC789" damaged="true" vendor="Bad Inc."/>
</inventoryReceived>
```

Todo Report
```
Task: Contact Joe Doe, 987 654 321. Damaged items: ABC789.
```

