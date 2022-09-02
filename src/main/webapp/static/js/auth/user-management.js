

const addButton = document.querySelector(".add-button");



load(); //페이지가 열리자마자 실행

function load(){
	$.ajax({
		async: false,
		type: "get",
		url: "/api/v1/user/management/users", //해당 url로 get요청함
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


addButton.onclick = () => {
	if(checkSpaceUserInput()){
		if(checkUserId()){
			alert("추가가능");
			saveUser();
		}else{
			alert("아이디 중복으로 인해 추가불가능");
		}
	}else{
		alert("공백 때문에 추가 불가능")
	}
}

function saveUser(){
	const userInputs = document.querySelectorAll(".user-input");
	
	const user = {
		userId: userInputs[0].value,
		userPassword: userInputs[1].value,
		userName: userInputs[2].value,
		userEmail: userInputs[3].value,
	}
	
	$.ajax({
		asyns: false,
		type: "post",
		url: "/api/v1/user",
		data: user,
		dataType: "json",
		success: (response) => {
			if(response.status){
				alert("추가성공");
				load();
			}else{
				alert("추가 실패");
			}
		},
		error: (error) => {
			console.log(error);
		}
	})
}


function checkUserId(){
	const userId = document.querySelectorAll(".user-input")[0].value;
	let result = false;
	
	$.ajax({
		async: false,
		type: "get",
		url: "/api/v1/user/overlap/user-Id",
		data: {
			"userId": userId //"userId"는 getParameter에서 사용된 key값
			
		},
		dataType: "json",
		success: (response) => {
			result = response.checkFlag;
		},
		error: (error) => {
			console.log(error);
		}
	})
	
	return result;
}

function checkSpaceUserInput(){
	const userInputs = document.querySelectorAll(".user-input");
	let result = true;

	for(let input of userInputs){
		if(isEmpty(input.value)){
			alert("모든 값을 입력해주세요.");
			return false;
		}		
	}
	return result;
}


function isEmpty(str){
	return str == null || 
	typeof str == undefined || 
	str == "" || 
	str.replace(" ","") == "" || 
	str.length == 0;
}


