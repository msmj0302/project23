/**
 * 
 */
 let index = {
    init: function () {
        // jQuery 사용
        $("#btn-save").on("click", () => {
			
            this.save(); // save함수 이벤트로 호출
        });
        
        $("#btn-cart").on("click", () => {
			
			this.cart();
		});

        $("#btn-update").on("click", () => {
            this.update();
        });

    },

    save: function () {
		var dogSize = $('input:radio[name=dogSize]:checked').val();
		var pictureSize = $('input:radio[name=pictureSize]:checked').val();
		var people = $('input:radio[name=people]:checked').val();
		
		if(people != "5인 이상"){
			people = "기본 인원";
		}
		
        let data = {
            username: $("#username").val(),
            name: $("#name").val(),
            phone: $("#phone").val(),
            btype: $("#btype").val(),
            dogSize: dogSize,
            pictureSize: pictureSize,
            people: people,
            themeName: $("#themeName").val(),
            bookingDate: $("#bookingDate").val()
            
        }

        // ajax 호출시 default가 비동기 호출이다.
        // ajax 통신을 이용해서 데이터를 json으로 변경하여 insert 요청을 한다.
        // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌.
        $.ajax({
            // 회원가입 수행 요청
            type: "POST",
            url: "/booking",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때, 기본적으로 모든 것이 문자열 (생긴게 json이라면 javascript 오브젝트로 변경해줌)
        }).done(function (result) {
            if(result == false) {
                alert("예약정원이 꽉차 예약에 실패했습니다");
            } else {
                alert("예약이 완료되었습니다.");
                location.href = "javascript:location.reload()";
            }

        }).fail(function (error) {
            alert("통신에 실패했습니다. 잠시 후 다시 시도해주세요.");
        });

    },
    
    cart: function(){
		var dogSize = $('input:radio[name=dogSize]:checked').val();
		var pictureSize = $('input:radio[name=pictureSize]:checked').val();
		var people = $('input:radio[name=people]:checked').val();
		
		if(people != "5인 이상"){
			people = "기본 인원";
		}
		
        let data = {
            username: $("#username").val(),
            name: $("#name").val(),
            phone: $("#phone").val(),
            btype: $("#btype").val(),
            dogSize: dogSize,
            pictureSize: pictureSize,
            people: people,
            themeName: $("#themeName").val(),
            bookingDate: $("#bookingDate").val()
            
        }

        // ajax 호출시 default가 비동기 호출이다.
        // ajax 통신을 이용해서 데이터를 json으로 변경하여 insert 요청을 한다.
        // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌.
        $.ajax({
            // 회원가입 수행 요청
            type: "POST",
            url: "/cart",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때, 기본적으로 모든 것이 문자열 (생긴게 json이라면 javascript 오브젝트로 변경해줌)
        }).done(function (result) {
            if(result == false) {
                alert("예약정원이 꽉차 예약에 실패했습니다");
            } else {
                alert("예약이 완료되었습니다.");
                location.href = "javascript:location.reload()";
            }

        }).fail(function (error) {
            alert("통신에 실패했습니다. 잠시 후 다시 시도해주세요.");
        });
	
	
	},






}


index.init();