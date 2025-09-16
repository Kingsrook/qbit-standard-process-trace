# QBit: Standard Process Trace

[![Version](https://img.shields.io/badge/version-0.1.1--SNAPSHOT-blue.svg)](https://github.com/Kingsrook/qbit-standard-process-trace)
[![License](https://img.shields.io/badge/license-GNU%20Affero%20GPL%20v3-green.svg)](https://www.gnu.org/licenses/agpl-3.0.en.html)
[![Java](https://img.shields.io/badge/java-17+-blue.svg)](https://adoptium.net/)

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

## 📚 Documentation

**📖 [Complete Documentation Wiki](https://github.com/Kingsrook/qqq/wiki)** - Start here for comprehensive guides

- **[🏠 Home](https://github.com/Kingsrook/qqq/wiki/Home)** - Project overview and quick start
- **[🏗️ Architecture](https://github.com/Kingsrook/qqq/wiki/High-Level-Architecture)** - System design and principles
- **[🔧 Development](https://github.com/Kingsrook/qqq/wiki/Developer-Onboarding)** - Setup and contribution guide
- **[📦 QBits](https://github.com/Kingsrook/qqq/wiki/QBit-Development)** - QBit development guide
- **[🚀 Building](https://github.com/Kingsrook/qqq/wiki/Building-Locally)** - Local development workflow

## 🤝 Contributing

**Important**: This repository is a component of the QQQ framework. All contributions, issues, and discussions should go through the main QQQ repository.

### Development Workflow

1. **Fork the main QQQ repository**: https://github.com/Kingsrook/qqq
2. **Create a feature branch**: `git checkout -b feature/amazing-feature`
3. **Make your changes** (including QBit changes if applicable)
4. **Run tests**: `mvn test`
5. **Commit your changes**: `git commit -m 'Add amazing feature'`
6. **Push to the branch**: `git push origin feature/amazing-feature`
7. **Open a Pull Request** to the main QQQ repository

### Code Standards

- **Java**: Follow Java best practices and QQQ coding standards
- **Documentation**: Update relevant documentation
- **Versioning**: Follow semantic versioning
- **Testing**: Maintain comprehensive test coverage

## 🏢 About Kingsrook

QQQ is built by **[Kingsrook](https://qrun.io)** - making engineers more productive through intelligent automation and developer tools.

- **Website**: [https://qrun.io](https://qrun.io)
- **Contact**: [contact@kingsrook.com](mailto:contact@kingsrook.com)
- **GitHub**: [https://github.com/Kingsrook](https://github.com/Kingsrook)

## 📄 License

This project is licensed under the **GNU Affero General Public License v3.0** - see the [LICENSE.txt](LICENSE.txt) file for details.

```
QBit Standard Process Trace
Copyright (C) 2021-2024 Kingsrook, LLC
651 N Broad St Ste 205 # 6917 | Middletown DE 19709 | United States
contact@kingsrook.com | https://github.com/Kingsrook/
```

**Note**: This is a component of the QQQ framework. For the complete license and more information, see the main QQQ repository: https://github.com/Kingsrook/qqq

## 🆘 Support & Community

### ⚠️ Important: Use Main QQQ Repository

**All support, issues, discussions, and community interactions should go through the main QQQ repository:**

- **Main Repository**: https://github.com/Kingsrook/qqq
- **Issues**: https://github.com/Kingsrook/qqq/issues
- **Discussions**: https://github.com/Kingsrook/qqq/discussions
- **Wiki**: https://github.com/Kingsrook/qqq/wiki

### Getting Help

- **Documentation**: Check the [QQQ Wiki](https://github.com/Kingsrook/qqq/wiki)
- **Issues**: Report bugs and feature requests on [Main QQQ Issues](https://github.com/Kingsrook/qqq/issues)
- **Discussions**: Join community discussions on [Main QQQ Discussions](https://github.com/Kingsrook/qqq/discussions)
- **Questions**: Ask questions in the main QQQ repository

## 🙏 Acknowledgments

- **QQQ Framework Team**: For the underlying low-code platform
- **Process Monitoring Community**: For process tracing best practices
- **Open Source Community**: For the tools and libraries that make this possible

---

**Built with ❤️ by the Kingsrook Team**

**This is a QBit component of the QQQ framework. For complete information, support, and community, visit: https://github.com/Kingsrook/qqq**

