function getGithubInfo(user) {
    //1. Create an instance of XMLHttpRequest class and send a GET request using it.
    // The function should finally return the object(it now contains the response!)
    let xhr = new XMLHttpRequest();
    xhr.open('GET',"https://api.github.com/users/"+user,false);
    xhr.send();
    return xhr;
}

function showUser(user) {
    //2. set the contents of the h2 and the two div elements in the div '#profile' with the user content
    $("h2").html(user.name);
    $(".avatar").html('<img src='+user.avatar_url+' />');
    $(".information").html('<a href='+user.html_url+'>'+user.html_url+'</a>');
}

function noSuchUser(username) {
    //3. set the elements such that a suitable message is displayed
}

$(document).ready(function () {
    $(document).on('keypress', '#username', function (e) {
        //check if the enter(i.e return) key is pressed
        let response;
        if (e.which == 13) {
            //get what the user enters
            username = $(this).val();
            //reset the text typed in the input
            $(this).val("");
            //get the user's information and store the response.
            response = getGithubInfo(username);
            //if the response is successful show the user's details
            if (response.status == 200) {
                console.log(JSON.parse(response.responseText));
                showUser(JSON.parse(response.responseText));
                //else display suitable message
            } else {
                noSuchUser(username);
            }
        }
    })
});