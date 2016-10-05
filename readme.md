#Public Transport Departure Time Station

Diese Services bieten einen systematischen Zugriff auf die Strassenbahn Haltestellen in Köln. Sie basieren auf dem Datensatz [Haltestellen Stadtbahn U-Bahn Koeln](https://www.offenedaten-koeln.de/dataset/haltestellen-stadtbahn-u-bahn-koeln) der [Offenen Daten der Stadt Köln](https://www.offenedaten-koeln.de/). Dafür werden die Daten in einer [PostgreSQL-Datenbank](https://www.postgresql.org/) mit [PostGis](http://www.postgis.net/) persistiert.

# Entwicklungsstand

Dieses Projekt befindet sich in der Entwicklung

# Schnittstellen

## /publicTransportDepatureTimeStation/service/nextstations/<lat,lng>?geojson

Diese Schnittstelle gibt alle nächsten Haltestellen im Umkreis von ca. 500 m um die übergebene Position zurück.

# Datenbank

## DB User auf Postgres einrichten

    sudo -u postgres createuser -P publictransport
    
## Datenbank wahlergebnis anlegen

    sudo -u postgres createdb -O publictransport publictransport

## Tabellen anlegen

	CREATE TABLE publictransport (
	    id           varchar(256),
		-- tbd
	    modtime      timestamp DEFAULT current_timestamp
	);
	SELECT AddGeometryColumn ('public','publictransport','geom',4326,'MULTIPOLYGON',2);
	
## DB-Tabellen initial einrichten

    psql -h localhost -U publictransport -d publictransport -a -f src/main/sql/publictransport.init.sql


## Verbindungsparameter

Die Datenbankverbindungsparameter werden per JNDI zur Verfügung gestellt. Dies bedeutet, dass sie im Container definiert sein müssen. Für den Online-Betrieb mit
Tomcat sind folgende Parameter zu setzen:

context.xml

    <Context>
        <ResourceLink 
             name="jdbc/publictransport" 
             global="jdbc/publictransport"
             type="javax.sql.DataSource" />
    </Context> 

server.xml

    <GlobalNamingResources>
        <Resource 
            name="jdbc/publictransport"
            auth="Container"
            driverClassName="org.postgresql.Driver"
            maxTotal="25" 
            maxIdle="10"
            username="username"
            password="password"
            type="javax.sql.DataSource"
            url="jdbc:postgresql://localhost:5432/publictransport"
            validationQuery="select 1"/>

Zu Testzwecken muss die Datei _src/test/resources/jndi.properties.template_ in _jndi.properties_ umbenannt und die Verbindungsparameter angepasst werden.

# License

<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/"><img alt="Creative Commons Lizenzvertrag" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" /></a><br />Dieses Werk ist lizenziert unter einer <a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">Creative Commons Namensnennung - Weitergabe unter gleichen Bedingungen 4.0 International Lizenz</a>.
