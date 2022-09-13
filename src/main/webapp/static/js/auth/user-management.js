

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
				<td class="usercode-text">${user.user_code}</td>
				<td class="userid-text">${user.user_id}</td>
				<td>${user.user_password}</td>
				<td>${user.user_name}</td>
				<td>${user.user_email}</td>
				<td>
					<span class="phone-text">${user.user_phone}</span>
					<input type="text" class="phone-update-input visible" value="${user.user_phone}">
				</td>
				<td>
					<span class="address-text">${user.user_address}</span>
					<input type="text" class="address-update-input visible" value="${user.user_address}">
				</td>
				<td>
					<button type="button" class="update-button">수정</button>
					<button type="button" class="update-ok-button visible">확인</button>
				</td>
				<td><button type="button" class="delete-button">삭제</button></td>
			</tr>
		`;
	}
	
	const updateButtons = document.querySelectorAll(".update-button");
		
	for(let i = 0; i < userList.length; i++){
		updateButtons[i].onclick = () => {
			const phoneText = document.querySelectorAll(".phone-text")[i];
			const addressText = document.querySelectorAll(".address-text")[i];
			const phoneUpdateInput = document.querySelectorAll(".phone-update-input")[i];
			const addressUpdateInput = document.querySelectorAll(".address-update-input")[i];
			const updateOkButton = document.querySelectorAll(".update-ok-button")[i];
			
			const userCodeText = document.querySelectorAll(".usercode-text")[i].textContent;
			
			//toggle: 클래스의 유무를 체크해서 없으면 add, 있으면 remove
			updateButtons[i].classList.toggle("visible");
			phoneText.classList.toggle("visible");
			addressText.classList.toggle("visible");
			phoneUpdateInput.classList.toggle("visible");
			addressUpdateInput.classList.toggle("visible");
			updateOkButton.classList.toggle("visible");
			
			updateOkButton.onclick = () => {
				$.ajax({
					async: false,
					type: "post",
					url: "/api/v1/user/update",
					data: {
						userCode: userCodeText,
						phone: phoneUpdateInput.value,
						address: addressUpdateInput.value						
					},
					dataType: "json",
					success: (response) => {
						alert("수정완료");
						load();
					},
					error: (error) => {
						console.log(error);
					}
				})
			}
		};
	}
	
	const deleteButtons = document.querySelectorAll(".delete-button");
	
	for(let i = 0; i < userList.length; i++){
		deleteButtons[i].onclick = () => {
			
			const userIdText = document.querySelectorAll(".userid-text")[i].textContent;
			const userCodeText = document.querySelectorAll(".usercode-text")[i].textContent;
			
			if(confirm(userIdText + "을(를) 정말로 지우시겠습니까?")){				
				$.ajax({
					async: false,
					type: "post",
					url: "/api/v1/user/delete",
					data: {
						userCode: userCodeText					
					},
					dataType: "json",
					success: (response) => {
						alert("삭제완료");
						load();
					},
					error: (error) => {
						console.log(error);
					}
					})
			}else{
				//false
			}
		};
	};
	
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


