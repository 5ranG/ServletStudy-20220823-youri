/*
	AJAX를 통한 데이터 통신
 */
 
 const writeButton = document.querySelector(".write-button");
 
 writeButton.onclick = () => {
	const title = document.querySelector(".board-title");
	const writer = document.querySelector(".board-writer");
	const content = document.querySelector(".board-content");
	let board = {
		"boardTitle": title.value,
		"boardWriter": writer.value,
		"boardContent": content.value
	}
	
	send(board);
}
 
 function send(board){
	const request = {
		async: false,					//async 동기(false), 비동기(true-defalt) 선택
		type: "post",					//RequestMethod (get요청 post요청 선택)
		url: "/api/v1/board/addition",	//요청 URL
		data: board,					//전송데이터
		dataType: "json",				//응답데이터 형식(Response ContentType)
		success: (response) => {		//요청에 대한 응답이 성공했으면 실행되는 function
			console.log(response);
		},
		error: (error) => {				//요청 또는 응답이 실패하였을 때 오류처리
			console.log(error)
		}
	}
	
	$.ajax(request);
}