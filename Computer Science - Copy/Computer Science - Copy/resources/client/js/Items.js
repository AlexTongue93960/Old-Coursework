"use strict";
function getItemsList() {
    //debugger;
    console.log("Invoked getItemsList");     //console.log your BFF for debugging client side - also use debugger statement
    const url = "/items/list";    		// API method on web server will be in Users class, method list
    fetch(url, {
        method: "GET",				//Get method
    }).then(response => {
        return response.json();                 //return response as JSON
}).then(response => {
        if (response.hasOwnProperty("Error")) { //checks if response from the web server has an "Error"
        alert(JSON.stringify(response));    // if it does, convert JSON object to string and alert (pop up window)
    } else {
        formatItemsList(response);          //this function will create an HTML table of the data (as per previous lesson)
    }
});
}


function formatItemsList(myJSONArray){
    let dataHTML = "";
    for (let item of myJSONArray) {
        dataHTML += "<tr><td>" + "<a href='itempage.html'>" + item.ItemName +"</a>" + "<tr><td>";
    }
    document.getElementById("ItemsTable").innerHTML = dataHTML;
}



function getArmourList() {
    //debugger;
    console.log("Invoked getArmourList");     //console.log your BFF for debugging client side - also use debugger statement
    const url = "/items/specificlist/Armour";    		// API method on web server will be in Users class, method list
    fetch(url, {
        method: "GET",				//Get method
    }).then(response => {
        return response.json();                 //return response as JSON
    }).then(response => {
        if (response.hasOwnProperty("Error")) { //checks if response from the web server has an "Error"
            alert(JSON.stringify(response));    // if it does, convert JSON object to string and alert (pop up window)
        } else {
            formatArmourList(response);
        }
    });
}

function formatArmourList(myJSONArray){
    let dataHTML = "";
    for (let item of myJSONArray) {
        dataHTML += "<tr><td>" + "<a href='itempage.html'>" + item.ItemName +"</a>" + "<tr><td>";
    }
    document.getElementById("ItemsTable").innerHTML = dataHTML;

}




function getWeaponsList() {
    //debugger;
    String
    let searchValue;
    searchValue = 'Weapon';
    console.log("Invoked getWeaponsList");     //console.log your BFF for debugging client side - also use debugger statement
    const url = "/items/specificlist/Weapon";    		// API method on web server will be in Users class, method list
    fetch(url, {
        method: "GET",				//Get method
    }).then(response => {
        return response.json();                 //return response as JSON
    }).then(response => {
        if (response.hasOwnProperty("Error")) { //checks if response from the web server has an "Error"
            alert(JSON.stringify(response));    // if it does, convert JSON object to string and alert (pop up window)
        } else {
            formatWeaponsList(response);          //this function will create an HTML table of the data (as per previous lesson)
        }
    });
}

function formatWeaponsList(myJSONArray){
    let dataHTML = "";
    for (let item of myJSONArray) {
        dataHTML += "<tr><td>" + "<button onclick='clearTable(); setItemStats(" + JSON.stringify(item.ItemName) + ")'>" + item.ItemName + "</button>" + "<tr><td>";
    }
    document.getElementById("ItemsTable").innerHTML = dataHTML;
}

function setItemStats(strung){
    let dataHTML = "";
    let Table = document.getElementById("StatName");
    dataHTML = "<tr><td>" + strung +"<tr><td>";
    Table.innerHTML = dataHTML;
}


function printItemName(){
    let currentItem = localStorage.getItem("currentItem");
    let dataHTML = "";
    dataHTML += "<tr><td>" + currentItem + "<tr><td>";
    document.getElementById("title").innerHTML = dataHTML;
}

function clearTable(){
    let Table = document.getElementById("ItemsTable");
    Table.innerHTML = "";
}