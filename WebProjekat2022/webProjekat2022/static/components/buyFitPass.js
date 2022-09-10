Vue.component("buyFitPass",{
    data : function(){
        return {
            membership:{id : null,type : null,price : null,customer : null,
            status : null,appointmentNumber : null},
            promocode : {name : null},
            step : true,
            step1 : false,
            step2 : false
        }
    },
    template:
    `
    <div>
        <div v-show="step">
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
        </div>
        <div v-show="step1">
            <p>Unesi promo kod</p></b>
            <input type="text" id="name" v-model="promocode.name"/><br>
            <button v-on:click="usePromo" style="padding: 7px 20px;
                background-color: aqua;">Unesi promokod</button><br>
            <button v-on:click="dontUsePromo" style="padding: 7px 20px;
                background-color: aqua;">Bez promo koda</button><br>
        </div>
        <div v-show="step2">
            <p>Potvrdi kupovinu</p><br>
            <button v-on:click="buyMembership" style="padding: 7px 20px;
                background-color: aqua;">Kupi</button><br>
        </div>
    </div>
    `,
    mounted(){

    },
    methods : {
        buyMembership1 : function(event){
            this.step1 = true;
            this.membership.type = "Mesecno"
            this.membership.facility = "test"
            this.membership.price = "3000"
            this.membership.status = "Active"
            this.membership.appointmentNumber = "30"
            event.preventDefault()
            // axios.post('/rest/customerHomePage/buyMembership',{"type":"Mesecno","facility":"test","price":"3000"
            // ,"status":"Active","appointmentNumber":"30"}).then(response => alert("Kupljena clanarina"))
            // router.push('/customerHomePage')
        },
        buyMembership2 : function(){
            this.step1 = true;
            this.membership.type = "Godisnji"
            this.membership.facility = "test2"
            this.membership.price = "30000"
            this.membership.status = "Active"
            this.membership.appointmentNumber = "300"
            event.preventDefault()
            // axios.post('/rest/customerHomePage/buyMembership',{"type":"Mesecno","facility":"test","price":"3000"
            // ,"status":"Active","appointmentNumber":"30"}).then(response => alert("Kupljena clanarina"))
            // router.push('/customerHomePage')
        },
        buyMembership3 : function(){
            this.step1 = true;
            this.membership.type = "Polugodisnji"
            this.membership.facility = "test3"
            this.membership.price = "15000"
            this.membership.status = "Active"
            this.membership.appointmentNumber = "150"
            event.preventDefault()
            // axios.post('/rest/customerHomePage/buyMembership',{"type":"Mesecno","facility":"test","price":"3000"
            // ,"status":"Active","appointmentNumber":"30"}).then(response => alert("Kupljena clanarina"))
            // router.push('/customerHomePage')
        },
        usePromo : function(event){
            axios.post('rest/customerHomePage/buyFitPass/checkPromo',this.promocode)
            .then(response => {
                alert("Ispravan promo kod")
                this.step1 = false
                this.step2 = true
                
            }).catch(error => {
                alert("Promo kod sa tim imenom ne postoji")
            })
            event.preventDefault()
        },
        dontUsePromo : function(){
            axios.post('/rest/customerHomePage/buyMembership',this.membership).then(response => alert("Kupljena clanarina"))
            router.push('/customerHomePage')
        },
        buyMembership : function(){
            axios.post('/rest/customerHomePage/buyMembership',this.membership).then(response => alert("Kupljena clanarina"))
            router.push('/customerHomePage')
        }
    }
})