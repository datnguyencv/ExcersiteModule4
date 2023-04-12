$(document).ready(() => {
    loadFootball();
})

const loadFootball = () => {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/football`,
        heads: {
            "Content-Type": "application/json",
        }, success: (data) => {
            showList(data.content)
        }, error: (error) => {
            alert("Error loading list player")
            console.log(error)
        }
    })
}

const showList = (content) => {
    showListTeams();
    let elements = ``;
    for (let player in content) {
        elements += `
        <div class="d-flex justify-content-center container mt-5">
        <div class="card p-3 bg-white"><i class="fa fa-user-o"></i>
            <div class="about-player text-center mt-2"><img src="${player.img}" width="100">
                <div>
                    <h6> ${player.name}</h6>
                    <h6 class="mt-0 text-black-50"></h6>
                </div>
            </div>
            <div class="stats mt-2">
                <div class="d-flex justify-content-between p-price"> Date Of Birth : ${player.dateOfBirth}</div>
                <div class="d-flex justify-content-between p-price"> Experience : ${player.experience}</div>
                <div class="d-flex justify-content-between p-price"> Position : ${player.position}</div>
                <div class="d-flex justify-content-between p-price"> Register Player: ${player.status}</div>
                <div class="d-flex justify-content-between p-price"> Teams: ${player.teams.name}</div>
            </div>
        </div>
    </div>
        `
            $("#listPlayer").html(elements)
    }
}


const teamsList = () => {
    $.ajax({
        type: "GET",
        url: `/localhost:8080/api/football/teams`,
        heads: {
            'Content-Type': "application/json"
        },
        success: (data) => {
            showListTeams(data)
        },
        error: (error) => {
            alert("Not found")
            console.log(error)
        }
    })
}

const showListTeams = (data) => {
    let element = `<label class="form-label"> Teams: </label>
<select class="form-control" id="list-team">`;
    for (let team of data) {
        element += `<option value="${team.id}">${team.name}</option>`
    }
    element += `</select>`
    $("#listTeam").html(element);
}


const addNew = () => {
    let name = $('#playerName').val();
    let dateOfBirth = $('#dateofbirth').val();
    let experience = $('#experience').val();
    let position = $('#position').val();
    let img = $('#img').val();
    let status = $('#status').val();
    let teams = $('#list-team').val();
    addNewPlayer(name, dateOfBirth, experience, position, img, status, teams)
}

const addNewPlayer = (name, dateOfBirth, experience, position, img, status, teams) => {
    $.ajax({
        type: "POST",
        url: `http://localhost:8080/api/football`,
        headers: {
            "Content-Type": "application/JSON"
        },
        data: JSON.stringify({
            name: name,
            dateOfBirth: dateOfBirth,
            experience: experience,
            position: position,
            img: img,
            status: status,
            teams: {id: teams}
        }),
        success: (data) => {
            alert("Register success")
        },
        error: (error) => {
            console.log(error);
        }
    })
}



