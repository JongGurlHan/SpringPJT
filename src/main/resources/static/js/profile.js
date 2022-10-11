/**
  1. 유저 프로파일 페이지
  (1) 유저 프로파일 페이지 팔로우하기, 팔로우취소
  (2) 팔로우자 정보 모달 보기
  (3) 팔로우자 정보 모달에서 팔로우하기, 팔로우취소
  (4) 유저 프로필 사진 변경
  (5) 사용자 정보 메뉴 열기 닫기
  (6) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
  (7) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달 
  (8) 팔로우자 정보 모달 닫기
 */

// (1) 유저 프로파일 페이지 팔로우하기, 팔로우취소

function toggleSubscribe(toUserId, obj) {
	if ($(obj).text() === "팔로우취소") {

	    $.ajax({
	     type:"delete",
	     url: "/api/follow/"+toUserId,
	     dataType: "json"
	    }).done(res=>{
       		$(obj).text("팔로우");
       		$(obj).toggleClass("blue");
	    }).fail(error=>{
            console.log("팔로우취소실패", error);
	    });
	} else {

        $.ajax({
             type:"post",
             url: "/api/follow/"+toUserId,
             dataType: "json"
            }).done(res=>{
                $(obj).text("팔로우취소");
                $(obj).toggleClass("blue");
            }).fail(error=>{
                console.log("팔로우하기실패", error);
            });
//		toggle: 클래스의 css이름을 넣었다 뺐대 함
	}
}

// (2) 팔로우정보  모달 보기
function subscribeInfoModalOpen(pageUserId) {
//modal-subscribe라는 클래스를 찾아서 화면에 보여줌
	$(".modal-subscribe").css("display", "flex");

	$.ajax({
	    url:`/api/user/${pageUserId}/follow`,
	    dataType:"json"
	}).done(res=>{
	    console.log(res.data);

	     res.data.forEach((u) => {
            let item = getSubscribeModalItem(u);
            $("#subscribeModalList").append(item);
        	});

	}).fail(error=>{
	    console.log("구독정보 불러오기 오류", error);
	});
}

function getSubscribeModalItem(u) {
    let item =`<div class="subscribe__item" id="subscribeModalItem-${u.id}">
                 <div class="subscribe__img">
<!--                  <img src="/image/${u.profileImageUrl}" onerror="this.src='/images/person.png'"/> -->
                   <img src="https://instaclonepjt-bucket.s3.ap-northeast-2.amazonaws.com/${u.profileImageUrl}" onerror="this.src='/images/person.png'"/>
                 </div>
                 <div class="subscribe__text">
                   <h2>${u.username}</h2>
                 </div>
                 <div class="subscribe__btn">`;

                 if(!u.equalUserState){ // 동일유저가 아닐때 버튼이 만들어져야함
                    if(u.followState){ //구독한 상태
                     item += `<button class="cta blue" onclick="toggleSubscribe(${u.id}, this)">팔로우취소</button>`
                    }else{ //구독 안한상태
                     item += `<button class="cta" onclick="toggleSubscribe(${u.id},this)">팔로우</button>`
                    }
                 }

                 item += `
                 </div>
               </div>`;

    return item;


}



function profileImageUpload(pageUserId, principalId) {
	if(pageUserId != principalId){
		alert("프로필 사진을 수정할 수 없는 유저입니다.");
		return;
	}
	$("#userProfileImageInput").click();

	$("#userProfileImageInput").on("change", (e) => {
		let f = e.target.files[0];

		if (!f.type.match("image.*")) {
			alert("이미지를 등록해야 합니다.");
			return;
		}
		// 서버에 이미지를 전송
		let profileImageForm = $("#userProfileImageForm")[0];
		console.log(profileImageForm);

		// FormData 객체를 이용하면 form 태그의 필드와 그 값을 나타내는 일련의 key/value 쌍을 담을 수 있다.
		let formData = new FormData(profileImageForm);

		$.ajax({
			type: "put",
			url: `/api/user/${principalId}/profileImageUrl`,
			data: formData,
			contentType: false, // 필수 : x-www-form-urlencoded로 파싱되는 것을 방지
			processData: false,  // 필수: contentType을 false로 줬을 때 QueryString 자동 설정됨. 해제
			enctype: "multipart/form-data",
			dataType: "json"
		}).done(res=>{
			// 사진 전송 성공시 이미지 변경
			let reader = new FileReader();
			reader.onload = (e) => {
				$("#userProfileImage").attr("src", e.target.result);
			}
			reader.readAsDataURL(f); // 이 코드 실행시 reader.onload 실행됨.
		}).fail(error=>{
			console.log("오류", error);
		});
	});
}


// (4) 사용자 정보 메뉴 열기 닫기
function popup(obj) {
	$(obj).css("display", "flex");
}

function closePopup(obj) {
	$(obj).css("display", "none");
}


// (5) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
function modalInfo() {
	$(".modal-info").css("display", "none");
}

// (6) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달
function modalImage() {
	$(".modal-image").css("display", "none");
}

// (7) 팔로우자 정보 모달 닫기
function modalClose() {
	$(".modal-subscribe").css("display", "none");
	location.reload();
}

// (8) 사진삭제
function imageDelete(imageId) {

    var confirmAlert = confirm('정말로 삭제하시겠습니까?');
    if(confirmAlert){

        alert(imageId);

//       	$.ajax({
//            type:"DELETE",
//            url : "/api/image/delete/"+imageId,
//       	    dataType:"json"
//       	}).done(res=>{
//       	    alert("해당사진이 정상적으로 삭제되었습니다.");
//
//       	}).fail(error=>{
//           error: function(request, status, error) {
//
//           }});



    }


}


