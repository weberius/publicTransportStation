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

