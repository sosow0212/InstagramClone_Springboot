// (1) 회원정보 수정
function update(userId, event) {

    event.preventDefault(); // 폼 태그 액션을 막기

    let data = $("#profileUpdate").serialize();

    $.ajax({
        type: "put",
        url: `/api/user/${userId}`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json"
    }).done(res => { // http status 상태코드 200
        console.log("성공", res);
        location.href = `/user/${userId}`;
    }).fail(error => { // http status 상태코드가 200이 아닐 때
        alert(JSON.stringify(error.responseJSON.data));
    });
}