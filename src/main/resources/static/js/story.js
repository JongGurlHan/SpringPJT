/**
	2. 스토리 페이지
	(1) 스토리 로드하기
	(2) 스토리 스크롤 페이징하기
	(3) 좋아요, 안좋아요
	(4) 댓글쓰기
	(5) 댓글삭제
 */

//(0) 현재 로긴한 사용자 아이디
let principalId = $("#principalId").val();
//alert(principalId);

// (1) 스토리 로드하기
function storyLoad() {
    $.ajax({
        url:`/api/image`,
        ataType : "json"
    }).done(res=>{
        console.log(res);
        res.data.forEach((image) => {

              console.log(image);
            let storyItem = getStoryItem(image)
            $("#storyList").append(storyItem);

        })
    }).fail(error => {
        console.log("오류", error);

    });
}

storyLoad();

function getStoryItem(image) {
    let item =
    `

    <div class="story-list__item">
         <div class="sl__item__header">
             <div>
//                 <img class="profile-image" src="https://instaclonepjt-bucket.s3.ap-northeast-2.amazonaws.com/${image.user.profileImageUrl}"
//                      onerror="this.src='/images/person.png'" />

             </div>
             <div>${image.user.username}</div>

         </div>

         <div class="sl__item__img">
<!--            <img src="/image/${image.postImageUrl}" /> -->
             <img src="https://instaclonepjt-bucket.s3.ap-northeast-2.amazonaws.com/${image.postImageUrl}" />
         </div>

         <div class="sl__item__contents">
             <div class="sl__item__contents__icon">

                 <button>`;
                      if(image.likeState){
                        item += `<i class="fas fa-heart active" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`;

                      }else{
                        item += `<i class="far fa-heart" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`;

                      }

                 item += `
                 </button>
             </div>

             <span class="like"><b id="storyLikeCount-${image.id}">${image.likeCount} </b>likes</span>

             <div class="sl__item__contents__content">
                 <p>${image.caption}</p>
             </div>


             <div id="storyCommentList-${image.id}">`;

                image.comments.forEach((comment)=>{

                    item +=
                    `
                    <div class="sl__item__contents__comment" id="storyCommentItem-${comment.id}">
                    <p>
                    	<b>${comment.user.username} :</b> ${comment.content}
                    </p>`;

                    if(principalId == comment.user.id){
                        item += `<button onclick="deleteComment(${comment.id})">
                                  <i class="fas fa-times"></i>
                                 </button>`;
                    }
                    item +=`
                </div>`;

                });
            item += `
         </div>

         <div class="sl__item__input">
			<input type="text" placeholder="댓글 달기..." id="storyCommentInput-${image.id}" />
             <button type="button" onClick="addComment(${image.id})">게시</button>
         </div>

     </div>
     </div>`;


    return item;

}

// (2) 스토리 스크롤 페이징하기
$(window).scroll(() => {

});


// (3) 좋아요, 안좋아요
function toggleLike(imageId) {
	let likeIcon = $(`#storyLikeIcon-${imageId}`);

	if (likeIcon.hasClass("far")) { //빈하트-> LIKE하겠다
	    $.ajax({
        			type: "post",
        			url: `/api/image/${imageId}/likes`,
        			dataType: "json"
        		}).done(res=>{

        			let likeCountStr = $(`#storyLikeCount-${imageId}`).text(); //b태그 내용의 text부분을 가져온다
        			let likeCount = Number(likeCountStr) + 1;
        			$(`#storyLikeCount-${imageId}`).text(likeCount);

        			likeIcon.addClass("fas");
        			likeIcon.addClass("active");
        			likeIcon.removeClass("far");
        		}).fail(error=>{
        			console.log("오류", error);
        		});

	} else {  //빨간하트 ->UNLIKE 하겠다.
	     $.ajax({
         			type: "delete",
         			url: `/api/image/${imageId}/likes`,
         			dataType: "json"
         		}).done(res=>{

         			let likeCountStr = $(`#storyLikeCount-${imageId}`).text();
         			let likeCount = Number(likeCountStr) - 1;
         			$(`#storyLikeCount-${imageId}`).text(likeCount);

         			likeIcon.removeClass("fas");
         			likeIcon.removeClass("active");
         			likeIcon.addClass("far");
         		}).fail(error=>{
         			console.log("오류", error);
         		});
	}
}

// (4) 댓글쓰기
function addComment(imageId) {

	let commentInput = $(`#storyCommentInput-${imageId}`);

   	let commentList = $(`#storyCommentList-${imageId}`);

   	let data = {
   	    imageId: imageId,
   		content: commentInput.val()
    };


	if (data.content === "") {
		alert("댓글을 작성해주세요!");
		return;
	}

	$.ajax({
	    type:"post",
	    url:"/api/comment",
	    data:JSON.stringify(data), //js데이터를 json으로 변환
        contentType: "application/json; charset=utf-8",
        dataType: "json" //응답받을 data타입
	}).done(res=>{
	    console.log("성공", res);

        let comment = res.data;

        let content = `
          <div class="sl__item__contents__comment" id="storyCommentItem-${comment.id}">
            <p>
              <b>${comment.user.username} :</b>
              ${comment.content}
            </p>
            <button onclick="deleteComment(${comment.id})"><i class="fas fa-times"></i></button>
          </div>
        `;

        commentList.prepend(content);
	}).fail(error=>{
	    console.log("오류", error.responseJSON.data.content);
	    alert(error.responseJSON.data.content);
	});

	commentInput.val(""); //인풋필드를 깨끗하게 비워준다.
}

// (5) 댓글 삭제
function deleteComment(commentId) {

    $.ajax({
        type:"delete",
        url:`/api/comment/${commentId}`,
        dataType:"json"
    }).done(res=>{
        console.log("성공", res);
        $(`#storyCommentItem-${commentId}`).remove();
    }).fail(error=>{
        console.log("오류", error);
    });

}







