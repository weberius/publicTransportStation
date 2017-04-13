select 
  id, name, lat, lon,
  ST_Distance(stop.geom, ST_MakePoint(?, ?)::geography) as distance
from 
  stop
ORDER BY 
  distance 
LIMIT ?