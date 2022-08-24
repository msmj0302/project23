let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
	},

	save: function() {
		let data = {
			content: $("#content").val(),
			scope: $('input:radio[name=scope]:checked').val()
		};

		$.ajax({
			type: "POST",
			url: "/api/review",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("후기가 등록되었습니다.");
			location.href = "/myPage/myReview";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	reviewDelete: function(rno) {
		var rnoValue = $("#rno").val();

		const con_check = confirm("삭제하시겠습니까?");
		if (con_check === true) {
			$.ajax({
				type: "DELETE",
				url: "/api/myreview/"+rnoValue,
				dataType: "json"
			}).done(function(resp) {
				alert("후기가 삭제되었습니다.");
				location.href = "/myPage/myReview";
			}).fail(function(error) {
				alert(JSON.stringify(error));
			});
		}
	},
}
index.init();