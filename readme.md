#Public Transport Departure Time Station

Diese Services bieten einen systematischen Zugriff auf die Haltestellen im Verkehrsverbund Rhein-Sieg. Sie basieren auf dem Datensatz [VRS Verkehrsdaten GTFS](https://www.offenedaten-koeln.de/dataset/vrs-verkehrsdaten-gtfs) der [Offenen Daten der Stadt Köln](https://www.offenedaten-koeln.de/). Die Daten liegen im [GTFS-Format](https://developers.google.com/transit/gtfs/) vor. Sie werden werden in einer [PostgreSQL-Datenbank](https://www.postgresql.org/) mit [PostGis](http://www.postgis.net/) persistiert.

# Entwicklungsstand

Dieses Projekt befindet sich in der Entwicklung

# Verwendete Technologien/ Bibliotheken

- Java
- Postgres/ Postgis
- onebusaway-gtfs
- Apache Commons
- Opendatalab GeoJson
- Google Gson

# Schnittstellen

## /publicTransportStation/service/stops

Diese Schnittstelle gibt alle Haltestellen in der Datenbank zurück.

## /publicTransportStation/service/stops?geojson

Diese Schnittstelle gibt alle Haltestellen in der Datenbank im geojson Format zurück.

## /publicTransportStation/service/stops?latlng={lat,lng}

Diese Schnittstelle gibt die nächsten fünf Haltestellen zu einer übergebenen geolocation zurück.

## /publicTransportStation/service/stops?latlng={lat,lng}&geojson

Diese Schnittstelle gibt die nächsten fünf Haltestellen zu einer übergebenen geolocation im geojson Format zurück.

## /publicTransportStation/service/stops?bbox={lat,lng,lat,lng}

Diese Schnittstelle gibt alle Haltestellen zu einer übergebenen boundingbox zurück.

## /publicTransportStation/service/stops?bbox={lat,lng,lat,lng}&geojson

Diese Schnittstelle gibt alle Haltestellen zu einer übergebenen boundingbox im geojson Format zurück.

## /publicTransportStation/service/put/<verbund>

Der Import der Daten kann über eine Schnittstelle initiiert werden. Voraussetzung ist, dass die Datei bereits heruntergeladen wurde und dass der Ort in der config.properties angegeben wurde. Die Daten stehen unter folgenden Adressen zum Download bereit:
- http://download.vrsinfo.de/gtfs/google_transit.zip
- http://download.vrsinfo.de/gtfs/google_transit_DB.zip

Z.B. kann mit folgendem Aufruf die Datei heruntergeladen werden:

    wget http://download.vrsinfo.de/gtfs/google_transit.zip /var/cache/publictransport
    
Der Import erfolgt dann über folgende Schnittstelle:

    curl -X PUT http://localhost:8080/publicTransportStation/service/put/vrs
    
Liegt keine Datei zum Download vor, wird die Aktion mit einer Meldung abgebrochen.

# Datenbank

Um für unterschiedliche Verkehrsverbünde unterschiedliche Mandaten zu ermöglichen, wird eine Datenbank für vrs angelegt.

## DB User auf Postgres einrichten

    sudo -u postgres createuser -P publictransport
    
## Datenbank wahlergebnis anlegen

    sudo -u postgres createdb -O publictransport vrs

## Postgis topology

    sudo -u postgres psql -c "CREATE EXTENSION postgis; CREATE EXTENSION postgis_topology;" vrs
    
## Tabellen anlegen

### stop

Die Tabelle stop enthält alle Informationen zu den Haltestellen. Die Struktur basiert auf der [General Transport Feed Specification](https://de.wikipedia.org/wiki/General_Transit_Feed_Specification). Nicht alle Spalten enthalten 

	CREATE TABLE stop (
	    id integer NOT NULL,
	    code character varying(40),
	    name character varying(256),
	    descrition character varying(256),
	    lat double precision,
	    lon double precision,
	    zoneid character varying(40),
	    url character varying(128),
	    locationtype integer,
	    parentstation character varying(40),
	    timezone character varying(40)
	);	
	SELECT AddGeometryColumn ('public','stops','geom',4326,'POINT',2);
	
## DB-Tabellen initial einrichten

    psql -h localhost -U publictransport -d vrs -a -f src/main/sql/publicTransport.init.sql
    
## Auf Kommandozeile mit der Datenbank verbinden

    psql -h localhost -U publictransport vrs


## Verbindungsparameter

Die Datenbankverbindungsparameter werden per JNDI zur Verfügung gestellt. Dies bedeutet, dass sie im Container definiert sein müssen. Für den Online-Betrieb mit
Tomcat sind folgende Parameter zu setzen:

context.xml

    <Context>
        <ResourceLink 
             name="jdbc/publictransport/vrs" 
             global="jdbc/publictransport/vrs"
             type="javax.sql.DataSource" />
    </Context> 

server.xml

    <GlobalNamingResources>
        <Resource 
            name="jdbc/publictransport/vrs"
            auth="Container"
            driverClassName="org.postgresql.Driver"
            maxTotal="25" 
            maxIdle="10"
            username="username"
            password="password"
            type="javax.sql.DataSource"
            url="jdbc:postgresql://localhost:5432/vrs"
            validationQuery="select 1"/>

Zu Testzwecken muss die Datei _src/test/resources/jndi.properties.template_ in _jndi.properties_ umbenannt und die Verbindungsparameter angepasst werden.

# Installation

Folgende Schritte müssen zur Installation durchgeführt werden:

1. Checkout Sources
2. Prepare Database
3. Prepare Tomcat
4. Build Application
5. Download data
6. Import Data

# License

<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/"><img alt="Creative Commons Lizenzvertrag" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" /></a><br />Dieses Werk ist lizenziert unter einer <a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">Creative Commons Namensnennung - Weitergabe unter gleichen Bedingungen 4.0 International Lizenz</a>.
