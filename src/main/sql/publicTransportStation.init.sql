DROP TABLE IF EXISTS station;
CREATE TABLE publictransport (
  gid integer NOT NULL,
  name character varying(40),
  knotennumm character varying(20),
  typ character varying(20),
  nr_stadtte character varying(3),
  stadtteil character varying(40),
  nr_stadtbe character varying(1),
  stadtbezir character varying(40),
  hyperlink character varying(200),
  objectid numeric(10,0)
);
SELECT AddGeometryColumn ('public','station','geom',4326,'POINT',2);