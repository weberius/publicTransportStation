DROP TABLE IF EXISTS stop;
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
SELECT AddGeometryColumn ('public','stop','geom',4326,'POINT',2);

DROP TABLE IF EXISTS agency;
CREATE TABLE agency (
	id integer NOT NULL,
	name character varying(256),
	url character varying(128),
	timezone character varying(40),
	lang character varying(2),
	phone character varying(40),
	fare_url character varying(128)
)