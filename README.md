## QBit:  Standard Process Trace

### Overview
*Note:  This is one of the original QBit implementations - so, some of the mechanics of how
it is loaded and used by an application are not exactly fully defined at the time of its
creation... Please excuse any dust or not-quite-round wheels you find here!*

This QBit provides tables to implement a fairly standard version of **Process Tracing**, 
along with an implementation of the `ProcessTracerInterface` which inserts data into
those tables.

### Usage

#### Pom dependency
```xml
<dependency>
    <groupId>com.kingsrook.qbits</groupId>
    <artifactId>qbit-standard-process-trace</artifactId>
    <version>${TODO}</version>
</dependency>
```

#### Setup
A method such as the following can be used to define the tables provided by this QBit,
along with details needed for working in your application. 

In addition, a `StandardProcessTraceQBitConfig` object is set up with meta-data references
to link this QBit to the application's `user` table.  
```java

/***************************************************************************
**
***************************************************************************/
private static void addStandardProcessTraceQBit(QInstance qInstance) throws QException
{
  StandardProcessTraceQBitConfig standardProcessTraceQBitConfig = new StandardProcessTraceQBitConfig()
     .withUserIdPossibleValueSourceName(User.TABLE_NAME)
     .withUserTableName(User.TABLE_NAME)
     .withUserIdReferenceFieldName("email");
  StandardProcessTracer.setStandardProcessTraceQBitConfig(standardProcessTraceQBitConfig);

  MetaDataCustomizerInterface<QTableMetaData> tableMetaDataCustomizer = (instance, table) ->
  {
     table.setBackendName("todoBackendName");
     table.setAuditRules(new QAuditRules().withAuditLevel(AuditLevel.NONE)); // recommended
     
     // if backend is in an RDBMS and uses under_score style names to camelCase:
     table.setBackendDetails(new RDBMSTableBackendDetails().withTableName(QInstanceEnricher.inferBackendName(table.getName())));
     QInstanceEnricher.setInferredFieldBackendNames(table);
     
     return (table);
  };

  new StandardProcessTraceQBitProducer()
     .withStandardProcessTraceQBitConfig(standardProcessTraceQBitConfig)
     .withTableMetaDataCustomizer(tableMetaDataCustomizer)
     .produce(qInstance);
}
```

A block like this can be used to add the tables provided by this QBit to an app in your instance
(namely, the `StandardProcessTraceQBitProducer.getAppSection(qInstance)` call):
```java
qInstance.addApp(new QAppMetaData()
   .withName("logs")
   .withIcon(new QIcon().withName("history_edu"))
   .withSection(StandardProcessTraceQBitProducer.getAppSection(qInstance)));
```

#### Adding to a process

To attach this QBit's `StandardProcessTracer` class to a process in your application, the following
call is required:

```java
processMetaData.setProcessTracerCodeReference(new QCodeReference(StandardProcessTracer.class));
```

#### Adding to all processes

Alternatively, to add this process tracer to all processes in your application, the following block
can be used (after all processes have been defined). *Note that in this example, we are allowing a process
to have specified a different process tracer, via the null check.`*

```java
QCodeReference processTracerCodeReference = new QCodeReference(StandardProcessTracer.class);
qInstance.getProcesses().values().forEach(p ->
{
   if(p.getProcessTracerCodeReference() == null)
   {
      p.setProcessTracerCodeReference(processTracerCodeReference);
   }
});
```



### Provides
#### Tables
* `procesTrace` - header for a process trace.  Includes details such as start & end timestamps,
exception message, key input record, and exception message.
* `procesTraceSummaryLine` - child record of `processTrace`.  Captures the summary lines produced
by a process in the field named: `processResults`.  e.g., what all `StreamedETLWithFrontendProcess`
instances do.
* `procesTraceSummaryLineRecordInt` - child record of `processTraceSummaryLine`.  Relates summary
lines to the individual records they are composed of.  

#### Classes
* `StandardProcessTracer` - implementation of `ProcessTracerInterface` that inserts records into
the provided tables.

### Dependencies
* `QQQProcess` and `QQQTable` tables
  * The `processTrace` table has a `qqqProcessId` foreign-key, as well as a `keyReccordQqqTableId`;
  and, `processTraceSummaryLineRecordInt` has a `qqqTableId`.  This means that you'll want and
  need these tables in your QQQ Instance and backend data store.  
  * Use `QQQProcessesMetaDataProvider` and `QQQTablesMetaDataProvider` to define these tables.

