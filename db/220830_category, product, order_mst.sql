# 데이터 여러개를 한 번에 insert 하는 방법
INSERT INTO
	product_mst
	VALUES(
		0,
		'스타벅스 블루 텀블러',
		1,
		28000
		),
		(
		0,
		'스타벅스 화이트 머그컵',
		2,
		15000
		),
		(
		0,
		'스타벅스 아이스 아메리카노',
		3,
		4000
		),
		(
		0,
		'스타벅스 블랙 머그컵',
		1,
		17000
		),
		(
		0,
		'스타벅스 핑크 텀블러',
		1,
		20000
		);
		
	# update
	update
		product_mst
	set
		product_price = product_price + 10000
	where
		product_name LIKE '%텀블러';
		
		
	
	# 카테고리 테이블	
	INSERT INTO
		category_mst
	VALUES(
		1,
		'텀블러'
		),
		(
		2,
		'머그컵'
		),(
		3,
		'음료'
		);
		
		
#join
	SELECT
		pm.product_code, 
		pm.product_name, 
		pm.product_category, 
		cm.category_name,
		pm.product_price
	FROM
		product_mst pm #pm은 product_mst의 별명. cm은 category_mst의 별명
		LEFT OUTER JOIN category_mst cm ON(cm.category_code = pm.product_category) 
		#cm이 먼저 와야한다. 조건이므로
	;
	
	INSERT Into
		order_mst
		VALUES(
			0,
			1,
			1,
			NOW()
		),
		(
			0,
			2,
			4,
			NOW()
		),
		(
			0,
			3,
			2,
			NOW()
		),
		(
			0,
			1,
			3,
			NOW()
		),		
		(
			0,
			2,
			5,
			NOW()
		),
		(
			0,
			1,
			5,
			NOW()
		);
		
		
	INSERT Into
		order_mst
		VALUES(
			0,
			4,
			4,
			NOW()
		);		
		
		
#join
	SELECT
		om.order_code As '코드', 
		om.order_user, 
		um.user_id, 
		om.order_product, 
		# um.user_code, 
		# um.user_password,
		# pm.product_code, 
		pm.product_name, 
		pm.product_category, 
		# cm.category_code, 
		cm.category_name,
		pm.product_price,
		om.order_datetime
	From
		order_mst om
		# LEFT OUTER JOIN user_mst um ON (um.user_code = om.order_user)
		# LEFT OUTER JOIN product_mst pm ON (pm.product_code = om.order_product)
		# LEFT OUTER JOIN category_mst cm ON (cm.category_code = pm.product_category)
		INNER JOIN user_mst um ON (um.user_code = om.order_user)
		INNER JOIN product_mst pm ON (pm.product_code = om.order_product)
		INNER JOIN category_mst cm ON (cm.category_code = pm.product_category)
		# INNER은 교집합
	;
	Where
		om.order_code > 1;
		AND pm.product_price > 20000;
	
	DELETE
	From
		user_mst
	Where
		user_code = 4;
		
		
		
	
		