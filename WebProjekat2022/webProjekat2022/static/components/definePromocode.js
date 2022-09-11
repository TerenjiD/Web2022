Vue.component("definePromocode",{
    data : function(){
        return{
            promocode : {name:null,startTime:null,endTime:null,numberOfCode:null,percent:null}
        }
    },
    template:
    `
    <div>
        <table>
            <tr><td>Ime</td><td><input type="text" id="name" v-model="promocode.name"/></td><tr>
            <tr><td>Pocetak vazenja:</td><td><input type="date" id="startTime" v-model="promocode.startTime"/></td><tr>
            <tr><td>Kraj vazenja</td><td><input type="date" id="endTime" v-model="promocode.endTime"/></td><tr>
            <tr><td>Broj koriscenja:</td><td><input type="number" id="numberOfCode" v-model="promocode.numberOfCode"/></td><tr>
            <tr><td>Procenat popusta:</td><td><input type="number" id="percent" v-model="promocode.percent"/></td><tr>
            <tr><button v-on:click="addPromocode" style="padding: 7px 20px;
            background-color: aqua;">Napravi</button></tr>
        </table>
    </div>
    `,
    mounted(){

    },
    methods : {
        addPromocode : function(event){
            axios.post('rest/adminHomePage/definePromocode/addPromocode',this.promocode)
            .then(response => {
                alert("Promo kod napravljen")
                router.push('/adminHomePage')
            })
            .catch(error => {
                alert("Postoji promo kod sa istim imenom!")
            })
            event.preventDefault();
        }
    }
})