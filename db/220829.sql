#한줄주석

/*
	DML
	C = insert
	R = select
	U = update
	D = delete
*/

# insert
INSERT INTO 
	user_mst(
		user_code,
		user_id, 
		user_password) 
	VALUES(
		0, 
		'youri4',
		'1234');
		

# 컬럼명 생략가능		
INSERT INTO 
	user_mst
	VALUES(
		0, 
		'youri5',
		'1234');
		
# 데이터 여러개를 한 번에 insert 하는 방법
INSERT INTO 
	user_mst
	VALUES(
		0, 
		'youri6',
		'1234'),
		(
		0, 
		'youri7',
		'1234'),
		(
		0, 
		'youri8',
		'1234');
		
		
# select
SELECT
	*
FROM
	user_mst
WHERE
#	user_code > 3 
#	and user_code < 6;

#	user_id = 'youri';

#	user_id LIKE '%ri%'; #ri가 포함된 단어를 모두 조회하라.
#	user_id LIKE 'you%'; 앞에 you로 시작하는 단어를  모두 조회하라.

#	user_id IN('youri3', 'youri4'); #아래의 OR과 동일
#	user_id = 'youri3'
#	OR user_id = 'youri4';

# where 서브쿼리
	user_id IN(
		SELECT
			user_id
		From
			user_mst
		Where
			user_code > 2
			AND user_code < 6
	);
	
# update
UPDATE
	user_mst
SET
	user_password = '2222',
	user_id = 'youri22'
WHERE
	user_code = (
		select
			user_code
		from
			user_mst
		where
			user_id = 'youri6'
	);
	
# delete
DELETE
FROM
	user_mst
WHERE
	user_code = 6;



