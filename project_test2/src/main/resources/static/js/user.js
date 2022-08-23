let index = {
    init: function () {
        // jQuery 사용
        
        $("#btn-save").on("click", () => {
			
			if(this.validation() == true){
 	           this.save(); // save함수 이벤트로 호출
			}
        });
        
        $("#btn-update").on("click", () => {
            this.update();
        });
		
		$("#btn-delete").on("click", () => {
			this.delete();
		});
		
		$("#idCheck").on("click", ()=>{
			this.check();
		});
		
		$("#username").change(function() {
		    
		    $("#idCheck-data").attr('value','idUncheck');
		});
    },

    save: function () {
	
		var objEmail = document.getElementById("email");
		var objEmailAdd = document.getElementById("emailAdd");
		var objEmailCheck = objEmail.value +"@"+objEmailAdd.value;
		var objTNumber1 = document.getElementById("tNumber1");
		var objTNumber2= document.getElementById("tNumber2");
		var objTNumber3 = document.getElementById("tNumber3");
		var objPhone = objTNumber1.value+"-"+objTNumber2.value+"-"+objTNumber3.value;
		
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            name: $("#name").val(),
            email: objEmailCheck,
            userTel: objPhone,
            dogName: $("#dogName").val(),
            dogType: $("#dogType").val()
        }

        // ajax 호출시 default가 비동기 호출이다.
        // ajax 통신을 이용해서 데이터를 json으로 변경하여 insert 요청을 한다.
        // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌.
        $.ajax({
            // 회원가입 수행 요청
            type: "POST",
            url: "/join",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때, 기본적으로 모든 것이 문자열 (생긴게 json이라면 javascript 오브젝트로 변경해줌)
        }).done(function (res) {
            // 회원가입 오류 잡기 (아이디 중복일 경우) - GlobalExceptionHandler
            if(res.status === 500) {
                alert("회원가입에 실패하였습니다.");
            } else {
                alert("회원가입이 완료되었습니다.");
                location.href = "/loginForm";
            }

        }).fail(function (error) {
            alert("통신에 실패했습니다. 잠시 후 다시 시도해주세요.");
        });

    },



     update: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
            name: $("#name").val(),
            userTel: $("#userTel").val(),
            dogName: $("#dogName").val(),
            dogType: $("#dogType").val()
            
        }

        $.ajax({
            type: "PUT",
            url: "/userModify",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            alert(res.data);
            location.href = "/";
        }).fail(function (error) {
            alert("통신에 에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
        });

    },
    
    delete: function(){
		let data = {
			password: $("#password").val()
		}
	
	
		$.ajax({
			type: "POST",
			url: "/userDelete",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
	        dataType: "json"
		}).done(function (result) {
			if(result == true){
	            alert("회원탈퇴가 완료되었습니다.");
	            location.href = "/loginForm";
			}else{
				alert("비밀번호가 일치하지 않습니다.")
			}
	    }).fail(function (error) {
            alert("통신에 실패했습니다. 잠시 후 다시 시도해주세요.");
	    });
	},
	
	check: function(){
		let username = $("#username").val();
		
		$.ajax({
            // 회원가입 수행 요청
            type: "POST",
            url: "/confirmId",
            data: String(username), // http body데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때, 기본적으로 모든 것이 문자열 (생긴게 json이라면 javascript 오브젝트로 변경해줌)
        }).done(function (result){
			if(result == true){
				$("#idCheck-data").attr('value','idUncheck');
				$("#idCheck-message").html('사용할 수 없는 아이디입니다.');
				$("#idCheck-message").attr('color','red');
			}else{
				$("#idCheck-data").attr('value','idCheck');
				$("#idCheck-message").html('사용 가능한 아이디입니다.');
				$("#idCheck-message").attr('color','green');
				
			}
		}).fail(function (){
			alert("통신에 실패했습니다. 잠시 후 다시 시도해주세요.");
		});
	},
	
	validation: function(){
		var objName = document.getElementById("name"); 		//이름
		var objId = document.getElementById("username");	//아이디
		var objIdCheck = document.getElementById("idCheck-data");	//아이디중복체크
		var objPwd = document.getElementById("password");
		var objPwdCheck = document.getElementById("pwCheck");
		var objEmail = document.getElementById("email");
		var objEmailAdd = document.getElementById("emailAdd");
		var objEmailCheck = objEmail.value +"@"+objEmailAdd.value;
		var objTNumber1 = document.getElementById("tNumber1");
		var objTNumber2= document.getElementById("tNumber2");
		var objTNumber3 = document.getElementById("tNumber3");
		var objPhone = objTNumber1.value+"-"+objTNumber2.value+"-"+objTNumber3.value;
		var objDogName = document.getElementById("dogName");
		var objDogType = document.getElementById("dogType");
		
		var idRegex = /^[a-zA-Z0-9]{4,16}$/;
		var pwdRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,20}$/;	//8~20자, 하나의 이상의 대소문자 및 하나의 숫자, 하나의 특수문자
		var emailRegex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;	//이메일형식
		var phoneRegex = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;	//앞자리가 01이며 (0,1,6,7,8,9) 이며 중간에 3~4자리, 세번째는 4자리인 전화번호
		
		if(objName.value==''){
				$("#nameCheck-message").html('이름을 입력해주세요.');
				$("#nameCheck-message").attr('color','red');
			
			objName.focus();
			return false;
		}else{
			$("#nameCheck-message").empty();
		}
		
		if(objId.value==''){
				$("#idCheck-message").html('아이디를 입력해주세요.');
				$("#idCheck-message").attr('color','red');
			
			objId.focus();
			return false;
		}
		
		if(!idRegex.test(objId.value)){
			$("#idCheck-message").html('아이디를 형식에 맞게 입력해주세요.');
			$("#idCheck-message").attr('color','red');
			
			objId.focus();
			return false;
		}
		
		if(objIdCheck.value == 'idUncheck'){
			$("#idCheck-message").html('아이디 중복확인을 해주세요.');
			$("#idCheck-message").attr('color','red');
			
			objIdCheck.focus();
			return false;
		}
		
		if(objPwd.value == ''){
			$("#pwdCheck1-message").html('비밀번호를 입력 해주세요.');
			$("#pwdCheck1-message").attr('color','red');
			
			
			objPwd.focus();
			return false;
		}else{
			$("#pwdCheck1-message").empty();
			
		}
		
		if(!pwdRegex.test(objPwd.value)){
			$("#pwdCheck1-message").html('비밀번호를 형식에 맞게 입력해주세요.');
			$("#pwdCheck1-message").attr('color','red');
			
			objPwd.focus();
			return false;
		}
		
		if(objPwdCheck.value == ''){
			$("#pwdCheck2-message").html('비밀번호 확인을 입력해주세요.');
			$("#pwdCheck2-message").attr('color','red');
			
			
			objPwdCheck.focus();
			return false;
		}else{
			$("#pwdCheck2-message").empty();
			
		}
		
		if(objPwd.value != objPwdCheck.value){
			$("#pwdCheck2-message").html('비밀번호가 일치하지 않았습니다. 다시 시도해 보세요.');
			$("#pwdCheck2-message").attr('color','red');
			
			objPwdCheck.focus();
			return false;
		}else{
			$("#pwdCheck2-message").empty();
			
		}
		
		if(objEmail.value == '' || objEmailAdd.value == ''){
			$("#emailCheck-message").html('이메일을 입력해주세요');
			$("#emailCheck-message").attr('color','red');
			
			if(objEmail.value == ''){
				objEmail.focus();
			}else{
				objEmailAdd.focus();
				
			}
			
			return false;
		}else{
			$("#emailCheck-message").empty();
			
		}
		
		if(!emailRegex.test(objEmailCheck)){
			$("#emailCheck-message").html('이메일 형식에 맞게 입력해주세요.');
			$("#emailCheck-message").attr('color','red');
			
			objEmailAdd.focus();
			return false;
		}
		
		
		if(objTNumber1.value == '' || objTNumber2.value == '' || objTNumber3.value == ''){
			$("#phoneCheck-message").html('전화번호를 입력해주세요');
			$("#phoneCheck-message").attr('color','red');
			
			if(objTNumber1.value == ''){
				objTNumber1.focus();
			}else if(objTNumber2.value == ''){
				objTNumber2.focus();
				
			}else{
				
				objTNumber3.focus();
			}
			
			return false;
		}else{
			$("#phoneCheck-message").empty();
			
		}
		
		if(!phoneRegex.test(objPhone)){
			$("#phoneCheck-message").html('전화번호 형식에 맞게 입력해주세요.');
			$("#phoneCheck-message").attr('color','red');
			
			objTNumber3.focus();
			return false;
		}
		
		return true;
		
		
		
	}

}


index.init();