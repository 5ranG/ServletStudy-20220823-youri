SELECT
	cl.country_id,
	c.name,
	max(c.area) AS max_area,
	c.region_id,
	r.name,
	r.continent_id,
	ct.name,
	
	cl.language_id,
	l.language
FROM
	country_languages cl
	LEFT OUTER JOIN countries c ON(c.country_id = cl.country_id)
	LEFT OUTER JOIN languages l ON(l.language_id = cl.language_id)
	LEFT OUTER JOIN regions r ON(r.region_id = c.region_id)
	LEFT OUTER JOIN continents ct ON(ct.continent_id = r.continent_id)
GROUP BY
	cl.country_id,
	c.name
	
#WHERE
# 	cl.country_id = 1
	;