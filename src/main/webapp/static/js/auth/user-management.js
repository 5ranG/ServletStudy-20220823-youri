
load(); //페이지가 열리자마자 실행

function load(){
	$.ajax({
		async: false,
		type: "get",
		url: "/api/v1/auth/management/users", //해당 url로 get요청함
		dataType: "json", //응답 데이터는 json으로 올 것이다.
		success: (response) => {
			console.log(response); 
			getUserList(response); //respone 는 userList
			//받은 데이터로 getUserList를 새로 얻겠다. 하단 function실행
		},
		error: (error) => {
			console.log(error);
		}
	});
}



function getUserList(userList){ //getUserList를 새로 얻었다.
	const tbody = document.querySelector("tbody");
	
	tbody.innerHTML = ""; //매번 초기화 시킴
	for(let user of userList){ //한줄 씩 얻어온다.
		tbody.innerHTML += `
			<tr>
				<td>${user.user_code}</td>
				<td>${user.user_id}</td>
				<td>${user.user_password}</td>
				<td>${user.user_name}</td>
				<td>${user.user_email}</td>
				<td>${user.user_phone}</td>
				<td>${user.user_address}</td>
				<td><button type="button" class="delete-button">삭제</button></td>
			</tr>
		`;
	}
}