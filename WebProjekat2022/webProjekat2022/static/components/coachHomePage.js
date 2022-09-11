Vue.component("coachHomePage",{
    data : function(){
        return{
            coachDTO : null,
            trainingsToDisplay : null
        }
    },
    template:`
    <div>
        <p>Trener prikeroni {{coachDTO.name}}</p>
        <button v-on:click="logout" style="padding: 7px 20px;
                    background-color: aqua;">Izloguj se</button><br>
        <button v-on:click="changeInfoCoach" style="padding: 7px 20px;
                        background-color: aqua;">Izmeni</button>
        <div>
            <table v-for="(p,index) in trainingsToDisplay">
                <tr><td>Name:</td><td>{{p.name}}</td></tr>
                <tr><td><button v-on:click="viewTraining(p)" style="padding: 7px 20px;
                background-color: aqua;">Pregled</button></td></tr>
            </table>
        </div>
    </div>
    `,mounted(){
        axios.get('/rest/coachHomePage/coach').then(response => (this.coachDTO=response.data));
        axios.get('rest/coachHomePage/getTrainings').then(response => (this.trainingsToDisplay=response.data))
    },
    methods : {
        changeInfoCoach : function(event){
            router.push('/coachHomePage/changeInfoCoach/');
        },
        viewTraining : function(p,event){
            axios.post('rest/coachHomePage/viewTraining',p).then(response => (router.push('/coachHomePage/view')))
        },
        logout : function(event){
            axios.post('rest/coachHomePage/logout').then(response => {
                alert("Izlogovan si")
                router.push('/')
            })
        }
    }
    
}) 