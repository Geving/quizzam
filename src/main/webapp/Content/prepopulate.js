console.log(' ----== Running prepopulate.js ==----');

var cookies = document.cookie.split(";");
for (var i = 0; i < cookies.length; i++) {
    var equals = cookies[i].indexOf("=");
    var name = equals > -1 ? cookies[i].substr(0, equals) : cookies[i];
    document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
}

// Add some questions to the table

// Create a GUID for use as ID
function uuidv4() {
    return ([1e7] + -1e3 + -4e3 + -8e3 + -1e11).replace(/[018]/g, c =>
        (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
    )
};

var qCnt = 0;

function addQuestion(id,q,a1,a2,a3,a4,s) {
    
    var alternatives = []; // Stores the alternatives to the new question
    if (a1 != null) { alternatives.push(a1); }
    if (a2 != null) { alternatives.push(a2); }
    if (a3 != null) { alternatives.push(a3); }
    if (a4 != null) { alternatives.push(a4); }

    $.ajax({
        url: 'rest/questions',
        type: 'POST',
        data: JSON.stringify({
            id: id,
            questionText: q,
            alternatives: alternatives,
            solution: s,
            secondsAllotted: 10
        }),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function (result) {
            console.debug("Successfully added a question!");
        }
    });


    //$.ajax({
    //    url: 'rest/questions',
    //    type: 'POST',
    //    data: JSON.stringify({
    //        id: id,
    //        questionText: q,
    //        alt1Text: a1,
    //        alt2Text: a2,
    //        alt3Text: a3,
    //        alt4Text: a4,
    //        solution: 4
    //    }),
    //    contentType: 'application/json; charset=utf-8',
    //    dataType: 'json',
    //    success: function (result) {
    //        console.debug("Successfully added a question!");
    //    }
    //});
};

$(function () {
    console.log('Adding all of your questions:');
    addQuestion('a82f3a14-7f36-4641-a48b-08bc5afda4ef','What is the airspeed velocity of an unladen swallow?', '11mph', '22mph', '24mph', 'What do you mean? African or European swallow?', 4);
    addQuestion('d5612107-4ff0-490f-808c-c7b86f8718a9','Which console is the only one that counts?', 'PlayStation', 'Xbox', null, null, 1);
    addQuestion('739d7730-4495-477c-b4e4-df796572a757','Which is better?', 'Nintendo', 'Sega', null, null, 2);
    addQuestion('f321d7ed-80a6-4ad5-9645-27e5c4680b4c','What is the unit of length that is approximately 3.26 light-years?', 'Light-minute', 'Parsec', 'Eon', 'E=mc^2', 2);
    addQuestion('5d8d8aae-11cc-4abe-a923-a74e2e705c70','Each of a classic Rubik\'s Cube six faces is covered by how many stickers?', '3', '6', '9', '12', 3);
    addQuestion('426a42a8-d6e4-4992-82ac-036e305faa81','Star Trek: The Next Generation originally aired in what year?', '1986', '1987', '1989', '1991', 2);
    addQuestion('68ded237-0817-4300-91e6-cec9a9c8870b', 'In our solar system which two planets rotate clockwise?', 'Uranus & Mars', 'Mars & Saturn', 'Saturn & Venus', 'Venus & Uranus', 4);

/*
    console.log('Adding a game');
    var d = new Date();
    var dd = d.getDate();
    var hh = d.getHours();
    var mm = d.getMinutes();
    var ss = d.getSeconds();
    //$.ajax({
    //    url: 'rest/quizzgames',
    //    type: 'POST',
    //    data: '{"id":"' + uuidv4() + '","title":"Created by population script","timeBegin":"2017-10-' + dd + ' ' + hh + ':' + mm + ':00","minPlayers":"1","maxPlayers":"4","questions":["739d7730-4495-477c-b4e4-df796572a757","a82f3a14-7f36-4641-a48b-08bc5afda4ef"]}',
    //    contentType: 'application/json; charset=utf-8',
    //    dataType: 'json',
    //    success: function (result) {
    //        console.debug("Successfully added a game!");
    //    }
    //});
        
    mm = d.getMinutes();
    ss += 20;
    if (ss >= 60){
        ss= (ss%60);
        mm++;
    }

    if (mm >= 60) {
        mm = (mm % 60);
        hh++;
    }

    if (hh >= 24) {
        hh = (hh % 24);
        dd++;
    }


    if (ss < 10) {
        ss = "0" + ss;
    };
    if (mm < 10) {
        mm = "0" + mm;
    };
    if (hh < 10) {
        hh = "0" + hh;
    };
    
    console.debug(hh + ":" + mm + ":" + ss);

    var qID = uuidv4();
    $.ajax({
        url: 'rest/quizzgames',
        type: 'POST',
        data: '{"id":"' + qID + '","title":"Test game (from script)","timeBegin":"2017-10-' + dd + ' ' + hh + ':' + mm + ':00","minPlayers":"1","maxPlayers":"4","questions":["739d7730-4495-477c-b4e4-df796572a757","a82f3a14-7f36-4641-a48b-08bc5afda4ef","68ded237-0817-4300-91e6-cec9a9c8870b"]}',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function (result) {
            console.debug("Successfully created a test game!");
            window.setTimeout(doTheRest, 3000);
        }
    });

    function doTheRest(){
        var pID = uuidv4();
        $.ajax({
            url: 'rest/players',
            type: 'POST',
            data: '{"id":"' + pID + '","nick":"Scripted user"}',
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function (result) {
                console.debug("Successfully added a player!");
            }
        });

        $.ajax({
            url: 'rest/quizzgames?quizzGameID=' + qID + '&playerID=' + pID,
            type: 'PUT',
            data: JSON.stringify({
                quizzGameID: qID,
                playerID: pID
            }),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function (result) {
                console.debug("Successfully joined the test game!");
                setCookie("playerID", pID, 4);
                setCookie("quizz", qID, 4);
                setCookie("action", "play", 3);
                setCookie("prepopulated", "yes", 1);
                location.reload();
            }
        });
    };
            */
})
