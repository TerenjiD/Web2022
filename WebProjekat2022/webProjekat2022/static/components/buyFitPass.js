Vue.component("buyFitPass",{
    data : function(){
        return {
            membership:{id : null,type : null,price : null,customer : null,
            status : null,appointmentNumber : null}
        }
    },
    template:
    `
    <table>
        <tr><td>Naziv:</td><td>Full body</td><td>Hungarian boy</td><td>Spric</td></tr>
        <tr><td>Objekti:</td><td>test</td><td>test2</td><td>test3</td></tr>
        <tr><td>Tip:</td><td>Mesecni</td><td>Godisnji</td><td>Polugodisnji</td></tr>
        <tr><td>Cena:</td><td>3000</td><td>30000</td><td>15000</td></tr>
        <tr><td>Dozvoljeni ulazak:</td><td>Jednom dnevno</td><td>Jednom dnevno</td><td>Jednom dnevno</td></tr>
        <tr><td></td><td><button v-on:click="buyMembership1" style="padding: 7px 20px;
            background-color: aqua;">Kupi</button></td>
            <td><button v-on:click="buyMembership2" style="padding: 7px 20px;
            background-color: aqua;">Kupi</button></td>
            <td><button v-on:click="buyMembership3" style="padding: 7px 20px;
            background-color: aqua;">Kupi</button></td></tr>
    </table>
    `,
    mounted(){

    },
    methods : {
        buyMembership1 : function(){
            axios.post('/rest/customerHomePage/buyMembership',{"type":"Mesecno","facility":"test","price":"3000"
            ,"status":"Active","appointmentNumber":"30"}).then(response => alert("Kupljena clanarina"))
            router.push('/customerHomePage')
        },
        buyMembership2 : function(){
            axios.post('/rest/customerHomePage/buyMembership',{"type":"Mesecno","facility":"test","price":"3000"
            ,"status":"Active","appointmentNumber":"30"}).then(response => alert("Kupljena clanarina"))
            router.push('/customerHomePage')
        },
        buyMembership3 : function(){
            axios.post('/rest/customerHomePage/buyMembership',{"type":"Mesecno","facility":"test","price":"3000"
            ,"status":"Active","appointmentNumber":"30"}).then(response => alert("Kupljena clanarina"))
            router.push('/customerHomePage')
        }
    }
})