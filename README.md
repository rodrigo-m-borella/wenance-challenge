# WENANCE BITCOIN CHALLENGE

El challenge fue realizado con los siguientes componentes:

`JAVA 11`
`Spring Boot` 
`MongoDb` 
`WebFlux`
`Lombok`
`Mapstruct`



## Consideraciones para el entorno local
- Debe existir una base de datos Mongo local. El nombre de la misma está especificada en el string de conexión en el application.properties.`cryptocurrency`

- Lombok requiere de la instalación de un plugin en el IDE, si es que se quiere debuggear.  

- Tanto para Lombok como para Mapstruct, en el apartado de plugins del POM.xml, se encuentran agregadas sus correspondientes instrucciones. Dado de que se trata de código autogenerado, si es que se requiere debuggear, se debe generar el mismo con Maven mediante los goals package, install ...    
```xml
<annotationProcessorPaths>
    <path>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>${org.projectlombok.version}</version>
    </path>
    <path>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct-processor</artifactId>
        <version>${org.mapstruct.version}</version>
    </path>
</annotationProcessorPaths>
```



