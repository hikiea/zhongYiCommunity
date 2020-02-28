// 提交回复
function doComment() {
    var tieId = $("#tie_id").val();
    var content = $("#comment_content").val();
    var comment_id = $("#comment_id").val();
    var comment_username = $("#comment_username").val();

    if (content == "" || content == null) {
        alert("内容不能为空");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": tieId,
            "content": content,
            "comment_id": comment_id,
            "comment_username": comment_username,
            "type": 1
        }),
        success: function a() {
            function flush() {
                history.go(0);
            }
        },
        dataType: "json"
    });
}

//不许删，我也不知道那个起作用！
function flush() {
    history.go(0);
    history.go(-1);
    location.reload();
    location = location;
    location.assign(location);
    document.execCommand('Refresh');
    window.navigate(location);
    location.replace(location);
    document.URL = location.href;
}


