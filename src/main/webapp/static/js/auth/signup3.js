/*
	AJAX를 통한 데이터 통신
 */
 
 const signupButton = document.querySelector(".signup-button");
 
 signupButton.onclick = () => {
	const user = {
		userId: document.querySelector(".user-id").value,
		userPassword: document.querySelector(".user-password").value,
		userName: document.querySelector(".user-name").value,
		userEmail: document.querySelector(".user-email").value
	}
	
	send(user);	
}

function send(user){
	$.ajax({
		async: false,
		type: "post",
		url: "/api/v1/auth/addition",
		data: user,
		dataType: "json",
		success: (response) => {
			alert("회원가입 성공");
			document.querySelector("body").innerHTML = `
				<h1>아이디: ${response.userId}</h1>
				<h1>비밀번호: ${response.userPassword}</h1>
				<h1>이름: ${response.userName}</h1>
				<h1>이메일: ${response.userEmail}</h1>
			`
		},
		error: (error) => {				//요청 또는 응답이 실패하였을 때 오류처리
			console.log(error);
			alert("회원가입 실패");
	}
	})
}


