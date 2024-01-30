function allSummary() {
    $.ajax({
        url: "http://localhost:8088/google-bard/summary/all",
        type: "GET",
        dataType: "json",
    }).done((data) => {
        $("#dataContent").empty();
        var template = "";
        data.forEach(function (item, index) {
            template += "<div>";
            template += "<span>" + "제목 : " + item.title + "</span><br/>";
            template += "<span>" + item.url + "</span><br/>";
            template += "<span>" + item.summary + "</span><br/>";
            template += "</div><br/>";
        })
        $("#dataContent").append(template);
    });
}

function getNews() {
    $.ajax({
        url: "http://localhost:8088/google-bard/news",
        type: "GET",
        dataType: "json",
    }).done((data) => {
        $("#dataContent").empty();
        var template = "";
        data.forEach(function (item, index) {
            template += "<div>";
            template += "<span>" + "제목 : " + item.title + "</span><button onclick=setSummary('" + item.newsId + "')>&nbsp;</button><br/>";
            template += "<span>" + item.url + "</span><br/>";
            template += "<span>" + item.summary + "</span><br/>";
            template += "</div><br/>";
        })
        $("#dataContent").append(template);
    });
}

function searchTitle() {
    var param = {
        title: $("#title").val()
    };
    $.ajax({
        url: "http://localhost:8088/google-bard/search/title",
        type: "GET",
        data: param
    }).done((data) => {
        $("#dataContent").empty();
        var template = "";
        data.forEach(function (item, index) {
            template += "<div>";
            template += "<span>" + "제목 : " + item.title + "</span><button onclick=setSummary('" + item.newsId + "')>&nbsp;</button><br/>";
            template += "<span>" + item.url + "</span><br/>";
            template += "<span>" + item.summary + "</span><br/>";
            template += "</div><br/>";
        })
        $("#dataContent").append(template);
    });
}

function allTitle() {
    $.ajax({
        url: "http://localhost:8088/google-bard/search/all-title",
        type: "GET",
    }).done((data) => {
        $("#dataContent").empty();
        var template = "";
        data.forEach(function (item) {
            template += "<div>";
            template += "<span>" + item + "</span>";
            template += "</div><br/>";
        })
        $("#dataContent").append(template);
    });
}

function setSummary(newsId) {
    var param = {
        newsId: newsId,
        token: $("#tokenPSID").val() + ";" + $("#tokenPDIDTS").val()
    };

    $.ajax({
        url: "http://localhost:8088/google-bard/summary/one",
        type: "GET",
        data: param
    }).done((data) => {
        getNews();
    }).error((msg) => {
        window.alert(msg.responseJSON.error);
    });
}